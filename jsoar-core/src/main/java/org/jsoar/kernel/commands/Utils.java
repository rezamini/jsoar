package org.jsoar.kernel.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import org.jsoar.kernel.Agent;
import org.jsoar.kernel.SoarException;

import picocli.CommandLine;
import picocli.CommandLine.IExecutionExceptionHandler;
import picocli.CommandLine.ParseResult;

public class Utils
{
    
    /**
     * Helper method intended to connect SoarCommands to the picocli parser
     * Typically called from the SoarCommand's execute method
     * Any output generated by picocli will automatically be passed to the agent's writer
     * Note that while SoarCommands is the typical use case, there was no reason to restrict this to that, so it accepts any object
     * @param agent SoarAgent executing the command
     * @param command An Object, should be annotated with picocli's @Command annotation
     * @param args The args as received by a SoarCommand's execute method (i.e., the 0th arg should be the string for the command itself)
     * @throws SoarException 
     */
    public static void parseAndRun(Agent agent, Object command, String[] args) throws SoarException {
        PrintWriter pw = agent.getPrinter().asPrintWriter();
        parseAndRun(command, args, pw);
    }
    
    
    /**
     * Executes the specified command and returns the result.
     * A command may be a user object or a pre-initialized {@code picocli.CommandLine} object.
     * For performance-sensitive commands that are invoked often,
     * it is recommended to pass a pre-initialized CommandLine object instead of the user object.
     *
     * @param command the command to execute; this may be a user object or a pre-initialized {@code picocli.CommandLine} object
     * @param args the command line arguments (the first arg will be removed from this list)
     * @param ps the PrintStream to print any command output to
     * @return the command result
     * @throws SoarException if the user input was invalid or if a runtime exception occurred
     *                      while executing the command business logic
     */
    public static String parseAndRun(Object command, String[] args, PrintWriter pw) throws SoarException {
        
        CommandLine commandLine = command instanceof CommandLine
                ? (CommandLine) command
                : new CommandLine(command);
        
        // always treat unrecognized options as params, as there are a number of commands whose params can be preceded by a dash (e.g., srand with a negative number, log with negative numbers, debug time with another command with options, etc.).  
        commandLine.setUnmatchedOptionsArePositionalParams(true);
        
        commandLine.setOut(pw);
        commandLine.setErr(pw);
        ExceptionHandler handler = new ExceptionHandler();
        commandLine.setExecutionExceptionHandler(handler);
        int exitCode = commandLine.execute(
                Arrays.copyOfRange(args, 1, args.length)); // picocli expects the first arg to be the first arg of the command, but for SoarCommands its the name of the command, so get the subarray starting at the second arg
        if(exitCode != 0) throw new SoarException("Error executing command " + String.join(" ", args));
        return commandLine.getExecutionResult();
    }
    
    public static String parseAndRun(Object command, String[] args) throws SoarException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String result = parseAndRun(command, args, pw);
        pw.print(result != null ? result : "");
        pw.close();
        return sw.toString();
    }
    
    /**
     * This throws all exceptions, so we can catch them above and re-throw as SoarExceptions
     * @author bob.marinier
     *
     */
    protected static class ExceptionHandler implements IExecutionExceptionHandler {

        /**
         * For execution exceptions, just rethrow without printing help
         * @throws Exception 
         */
        @Override
        public int handleExecutionException(Exception ex,
                CommandLine commandLine,
                ParseResult parseResult) throws Exception
        {
            throw new SoarException(ex);
        } 
    }
}
