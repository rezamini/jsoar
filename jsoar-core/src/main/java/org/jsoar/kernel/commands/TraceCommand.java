package org.jsoar.kernel.commands;

import org.jsoar.kernel.Agent;
import org.jsoar.kernel.SoarException;
import org.jsoar.kernel.tracing.Trace;
import org.jsoar.kernel.tracing.Trace.Category;
import org.jsoar.util.commands.SoarCommand;
import org.jsoar.util.commands.SoarCommandContext;

import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * This is the implementation of the "trace" command.
 * @author austin.brehob
 */
public class TraceCommand implements SoarCommand
{
    private final Agent agent;
    private final Trace trace;
    
    public TraceCommand(Agent agent)
    {
        this.agent = agent;
        this.trace = agent.getTrace();
    }
    
    @Override
    public String execute(SoarCommandContext context, String[] args) throws SoarException
    {
        Utils.parseAndRun(agent, new TraceC(agent, trace), args);
        
        return "";
    }

    @Override
    public Object getCommand()
    {
        return new TraceC(agent,trace);
    }

    @Command(name="trace", description="Control the run-time tracing of Soar",
            subcommands={HelpCommand.class})
    static public class TraceC implements Runnable
    {
        private final Agent agent;
        private final Trace trace;
        
        public TraceC(Agent agent, Trace trace)
        {
            this.agent = agent;
            this.trace = trace;
        }
        
        @Option(names={"-N", "--none"}, defaultValue="false", description="Turns off all printing about Soar's internals")
        boolean stopPrintingInternals;
        
        @Option(names={"-l", "--level"}, description="Sets a trace level from 0 to 5")
        Integer traceLevel;
        
        @Option(names={"-L", "--learning"}, description="Controls the printing "
                + "of chunks/justifications as they are created")
        String learningPrintLevel;
        
        @Option(names={"-b", "--backtracing"}, defaultValue="false", description="Print backtracing "
                + "information when a chunk or justification is created")
        boolean printBacktracing;
        
        @Option(names={"-d", "--decisions"}, defaultValue="false", description="Controls whether state "
                + "and operator decisions are printed as they are made")
        boolean printDecisions;
        
        @Option(names={"-e", "--epmem"}, defaultValue="false", description="Print episodic retrieval "
                + "traces and IDs of newly encoded episodes")
        boolean printEpmem;
        
        @Option(names={"-g", "--gds"}, defaultValue="false", description="Controls printing of "
                + "warnings when a state is removed due to the GDS")
        boolean printGds;
        
        @Option(names={"-G", "--gds-wmes"}, defaultValue="false", description="Controls printing of "
                + "warnings about wme changes to the GDS")
        boolean printGdsWmes;
        
        @Option(names={"-i", "--indifferent-selection"}, defaultValue="false", description="Print scores "
                + "for tied operators in random indifferent selection mode")
        boolean printIndifferentSelection;
        
        @Option(names={"-p", "--phases"}, defaultValue="false", description="Controls whether decision "
                + "cycle phase names are printed as Soar executes")
        boolean printPhases;
        
        @Option(names={"-r", "--preferences"}, defaultValue="false", description="Controls whether the "
                + "preferences generated by the traced productions are printed "
                + "when those productions fire or retract")
        boolean printPreferences;
        
        @Option(names={"-P", "--productions"}, defaultValue="false", description="Controls whether the "
                + "names of productions are printed as they fire and retract")
        boolean printProductions;
        
        @Option(names={"-R", "--rl"}, defaultValue="false", description="Print RL debugging output")
        boolean printRl;
        
        @Option(names={"-s", "--smem"}, defaultValue="false", description="Print log of semantic memory storage events")
        boolean printSmem;
        
        @Option(names={"-w", "--wmes"}, defaultValue="false", description="Controls the printing of "
                + "working memory elements that are added and deleted as "
                + "productions are fired and retracted")
        boolean printWmes;
        
        @Option(names={"-W", "--waterfall"}, defaultValue="false", description="Controls printing of "
                + "firings inhibited by higher-level firings")
        boolean printWaterfall;
        
        @Option(names={"-a", "--wma"}, defaultValue="false", description="Print log of working memory activation events")
        boolean printWma;
        
        @Option(names={"-A", "--assertions"}, defaultValue="false", description="Print assertions of "
                + "rule instantiations and the preferences they generate")
        boolean printAssertions;
        
        @Option(names={"-D", "--default"}, defaultValue="false", description="Control only "
                + "default-productions as they fire and retract")
        boolean printDefaultProds;
        
        @Option(names={"-u", "--user"}, defaultValue="false", description="Control only user-productions as they fire and retract")
        boolean printUserProds;
        
        @Option(names={"-c", "--chunks"}, defaultValue="false", description="Control only chunks as they fire and retract")
        boolean printChunks;
        
        @Option(names={"-j", "--justifications"}, defaultValue="false", description="Control only "
                + "justifications as they fire and retract")
        boolean printJustify;
        
        @Option(names={"-T", "--template"}, defaultValue="false", description="Soar-RL template firing trace")
        boolean printTemplates;
        
        @Parameters(arity="0..1", description="Turns the specified setting off")
        String remove;
        
        
        @Override
        public void run()
        {
            // If no --options are provided...
            if (!stopPrintingInternals && traceLevel == null && learningPrintLevel == null &&
                    !printBacktracing && !printDecisions && !printEpmem && !printGds &&
                    !printIndifferentSelection && !printPhases && !printPreferences &&
                    !printProductions && !printRl && !printSmem && !printWmes && !printWaterfall &&
                    !printWma && !printDefaultProds && !printUserProds && !printChunks &&
                    !printJustify && !printTemplates && !printAssertions)
            {
                // If the user forgets to provide --level option to set
                // the trace level (or types an invalid command)...
                if (remove != null)
                {
                    try
                    {
                        traceLevel = Integer.valueOf(remove);
                        if (traceLevel >= 0 && traceLevel <= 5)
                        {
                            trace.setWatchLevel(traceLevel);
                            printTraceLevelDetails(traceLevel);
                        }
                        else
                        {
                            agent.getPrinter().startNewLine().print("Error: trace level must be "
                                    + "between 0 and 5 (inclusive)");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        agent.getPrinter().startNewLine().print("Invalid command. Type "
                                + "'trace help' for a list of trace commands.");
                    }
                }
                else
                {
                    printWatchSettings();
                }
                return;
            }
            
            if (remove != null && !remove.equals("remove") && !remove.equals("0"))
            {
                agent.getPrinter().startNewLine().print("Invalid argument; expected 'remove' or '0'");
                return;
            }
            
            if (stopPrintingInternals)
            {
                trace.setWatchLevel(0);
                agent.getPrinter().startNewLine().print("Will not print working memory element details.");
            }
            if (traceLevel != null)
            {
                if (traceLevel >= 0 && traceLevel <= 5)
                {
                    trace.setWatchLevel(traceLevel);
                    printTraceLevelDetails(traceLevel);
                }
                else
                {
                    agent.getPrinter().startNewLine().print("Error: trace level must be "
                            + "between 0 and 5 (inclusive)");
                }
            }
            if (learningPrintLevel != null)
            {
                processLearning(learningPrintLevel);
            }
            if (printBacktracing)
            {
                toggleAndPrint(Category.BACKTRACING, remove == null, "assertions of " +
                        "rule instantiations and the preferences they generate.");
            }
            if (printDecisions)
            {
                toggleAndPrint(Category.CONTEXT_DECISIONS, remove == null, "states created "
                        + "and operators selected.");
            }
            if (printEpmem)
            {
                toggleAndPrint(Category.EPMEM, remove == null, "when episodic memory records "
                        + "a new episode or considers an episode in a query.");
            }
            if (printGds)
            {
                toggleAndPrint(Category.GDS, remove == null, "when a state is removed "
                        + "because of a GDS violation.");
            }
            if (printGdsWmes)
            {
                agent.getPrinter().startNewLine().print("This option has not been "
                        + "implemented in JSoar");
            }
            if (printIndifferentSelection)
            {
                toggleAndPrint(Category.INDIFFERENT, remove == null, "how Soar calculates "
                        + "and resolves numeric preferences.");
            }
            if (printPhases)
            {
                toggleAndPrint(Category.PHASES, remove == null, "each individual phase.");
            }
            if (printPreferences)
            {
                toggleAndPrint(Category.FIRINGS_PREFERENCES, remove == null, 
                        "preferences as they are created.");
            }
            if (printProductions)
            {
                toggleAndPrint(Category.FIRINGS_OF_DEFAULT_PRODS, remove == null,
                        "when rules marked as :default fire.");
                toggleAndPrint(Category.FIRINGS_OF_USER_PRODS, remove == null, "when user rules fire.");
                toggleAndPrint(Category.FIRINGS_OF_CHUNKS, remove == null, "when chunks fire.");
                toggleAndPrint(Category.FIRINGS_OF_JUSTIFICATIONS, remove == null,
                        "when justifications fire.");
            }
            if (printRl)
            {
                toggleAndPrint(Category.RL, remove == null, "reinforcement learning "
                        + "value updates and gap intervals.");
            }
            if (printSmem)
            {
                toggleAndPrint(Category.SMEM, remove == null, "additions to semantic memory.");
            }
            if (printWmes)
            {
                toggleAndPrint(Category.WM_CHANGES, remove == null, "when working "
                        + "memory elements are added to or removed from memory.");
            }
            if (printWaterfall)
            {
                toggleAndPrint(Category.WATERFALL, remove == null, "when rules do not fire "
                        + "because a higher level rule matches and needs to fire first.");
            }
            if (printWma)
            {
                toggleAndPrint(Category.WMA, remove == null, "working memory activations, "
                        + "changed values, and removals caused by forgetting (if enabled).");
            }
            if (printDefaultProds)
            {
                toggleAndPrint(Category.FIRINGS_OF_DEFAULT_PRODS, remove == null,
                        "when rules marked as :default fire.");
            }
            if (printUserProds)
            {
                toggleAndPrint(Category.FIRINGS_OF_USER_PRODS, remove == null, "when user rules fire.");
            }
            if (printChunks)
            {
                toggleAndPrint(Category.FIRINGS_OF_CHUNKS, remove == null, "when chunks fire.");
            }
            if (printJustify)
            {
                toggleAndPrint(Category.FIRINGS_OF_JUSTIFICATIONS, remove == null,
                        "when justifications fire.");
            }
            if (printTemplates)
            {
                toggleAndPrint(Category.FIRINGS_OF_TEMPLATES, remove == null, "when templates match.");
            }
            if (printAssertions)
            {
                toggleAndPrint(Category.VERBOSE, remove == null, "assertions of " +
                        "rule instantiations and the preferences they generate.");
            }
        }

        private String setting(String title, Category c)
        {
            return "  " + title + ": " + (trace.isEnabled(c) ? "on" : "off") + "\n";
        }
        
        private void printTraceLevelDetails(Integer level)
        {
            if (level == 0)
            {
                agent.getPrinter().startNewLine().print("Trace level 0 enabled: All messages disabled.");
            }
            
            for (int i = level; i > 0; i--)
            {
                agent.getPrinter().startNewLine().print("Trace level " + i + " enabled: ");
                
                if (i == 1)
                {
                    agent.getPrinter().print("Decision cycles, state creation and operator selection");
                }
                else if (i == 2)
                {
                    agent.getPrinter().print("All phases, state removals caused by operator selection "
                            + "or a GDS violation, and any learning issues detected");
                }
                else if (i == 3)
                {
                    agent.getPrinter().print("All rule firings");
                }
                else if (i == 4)
                {
                    agent.getPrinter().print("Working memory element additions and removals");
                }
                else if (i == 5)
                {
                    agent.getPrinter().print("Preferences");
                }
            }
        }
        
        private void processLearning(String printLevel)
        {
            if (printLevel.equals("noprint") || printLevel.equals("0"))
            {
                trace.setEnabled(Category.CHUNK_NAMES, false);
                trace.setEnabled(Category.CHUNKS, false);
                trace.setEnabled(Category.JUSTIFICATION_NAMES, false);
                trace.setEnabled(Category.JUSTIFICATIONS, false);
                agent.getPrinter().startNewLine().print("Will not print any "
                        + "information about chunks or justifications learned.");
            }
            else if (printLevel.equals("print") || printLevel.equals("1"))
            {
                trace.setEnabled(Category.CHUNK_NAMES, true);
                trace.setEnabled(Category.CHUNKS, false);
                trace.setEnabled(Category.JUSTIFICATION_NAMES, true);
                trace.setEnabled(Category.JUSTIFICATIONS, false);
                agent.getPrinter().startNewLine().print("Now printing the names of chunks and "
                        + "justifications that are learned and any chunking issues detected.");
            }
            else if (printLevel.equals("fullprint") || printLevel.equals("2"))
            {
                trace.setEnabled(Category.CHUNK_NAMES, true);
                trace.setEnabled(Category.CHUNKS, true);
                trace.setEnabled(Category.JUSTIFICATION_NAMES, true);
                trace.setEnabled(Category.JUSTIFICATIONS, true);
                agent.getPrinter().startNewLine().print("Now printing the full chunks and "
                        + "justifications that are learned and any chunking issues detected.");
            }
            else
            {
                agent.getPrinter().startNewLine().print("Invalid learn setting, " +
                        "expected noprint, print, fullprint, or 0-2. Got: " + printLevel);
            }
        }
        
        private void toggleAndPrint(Category c, boolean enable, String tailOfMessage)
        {
            if (enable)
            {
                trace.setEnabled(c, true);
                agent.getPrinter().startNewLine().print("Now printing " + tailOfMessage);
            }
            else
            {
                trace.setEnabled(c, false);
                agent.getPrinter().startNewLine().print("Will not print " + tailOfMessage);
            }
        }
        
        private void printWatchSettings()
        {
            final StringBuilder b = new StringBuilder("Current watch settings:\n");
            b.append(setting("Decisions", Category.CONTEXT_DECISIONS));
            b.append(setting("Phases", Category.PHASES));
            b.append(setting("Default productions", Category.FIRINGS_OF_DEFAULT_PRODS));
            b.append(setting("User productions", Category.FIRINGS_OF_USER_PRODS));
            b.append(setting("Chunks", Category.FIRINGS_OF_CHUNKS));
            b.append(setting("Justifications", Category.FIRINGS_OF_JUSTIFICATIONS));
            b.append(setting("Templates", Category.FIRINGS_OF_TEMPLATES));
            b.append("    WME detail level: " + trace.getWmeTraceType() + "\n");
            b.append(setting("Working memory changes", Category.WM_CHANGES));
            b.append(setting("Preferences generated by firings/retractions", Category.FIRINGS_PREFERENCES));
            b.append("  Learning: ??\n");
            b.append(setting("Backtracing", Category.BACKTRACING));
            b.append(setting("Indifferent selection", Category.INDIFFERENT));
            b.append(setting("Epmem", Category.EPMEM));
            b.append(setting("Soar-RL", Category.RL));
            b.append(setting("Waterfall", Category.WATERFALL));

            agent.getPrinter().startNewLine().print(b.toString());
        }
    }
}
