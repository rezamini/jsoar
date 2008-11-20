/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.35
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sml;

import java.util.Map;

public class Events
{

    // We keep two maps, one for each direction
    private Map<Integer, String> m_ToStringMap;

    private Map<String, Integer> m_ToEventMap;

    public synchronized void delete()
    {
    }

    public Events()
    {
        // Kernel events
        RegisterEvent(smlSystemEventId.smlEVENT_BEFORE_SHUTDOWN, "before-shutdown");
        RegisterEvent(smlSystemEventId.smlEVENT_AFTER_CONNECTION, "after-connection");
        RegisterEvent(smlSystemEventId.smlEVENT_SYSTEM_START, "system-start");
        RegisterEvent(smlSystemEventId.smlEVENT_SYSTEM_STOP, "system-stop");
        RegisterEvent(smlSystemEventId.smlEVENT_INTERRUPT_CHECK, "interrupt-check");
        RegisterEvent(smlSystemEventId.smlEVENT_SYSTEM_PROPERTY_CHANGED, "system-property-changed");

        // Run events
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_SMALLEST_STEP, "before-smallest-step");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_SMALLEST_STEP, "after-smallest-step");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_ELABORATION_CYCLE, "before-elaboration-cycle");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_ELABORATION_CYCLE, "after-elaboration-cycle");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_PHASE_EXECUTED, "before-phase-executed");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_INPUT_PHASE, "before-input-phase");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_PROPOSE_PHASE, "before-propose-phase");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_DECISION_PHASE, "before-decision-phase");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_APPLY_PHASE, "before-apply-phase");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_OUTPUT_PHASE, "before-output-phase");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_PREFERENCE_PHASE, "before-preference-phase"); // Soar-7 mode only
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_WM_PHASE, "before-wm-phase"); // Soar-7 mode only
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_INPUT_PHASE, "after-input-phase");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_PROPOSE_PHASE, "after-propose-phase");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_DECISION_PHASE, "after-decision-phase");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_APPLY_PHASE, "after-apply-phase");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_OUTPUT_PHASE, "after-output-phase");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_PREFERENCE_PHASE, "after-preference-phase"); // Soar-7 mode only
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_WM_PHASE, "after-wm-phase"); // Soar-7 mode only
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_PHASE_EXECUTED, "after-phase-executed");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_DECISION_CYCLE, "before-decision-cycle");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_DECISION_CYCLE, "after-decision-cycle");
        RegisterEvent(smlRunEventId.smlEVENT_MAX_MEMORY_USAGE_EXCEEDED, "memory-usage-exceeded");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_INTERRUPT, "after-interrupt");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_HALTED, "after-halted");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_RUN_STARTS, "before-run-starts");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_RUN_ENDS, "after-run-ends");
        RegisterEvent(smlRunEventId.smlEVENT_BEFORE_RUNNING, "before-running");
        RegisterEvent(smlRunEventId.smlEVENT_AFTER_RUNNING, "after-running");

        // Production manager
        RegisterEvent(smlProductionEventId.smlEVENT_AFTER_PRODUCTION_ADDED, "after-production-added");
        RegisterEvent(smlProductionEventId.smlEVENT_BEFORE_PRODUCTION_REMOVED, "before-production-added");
        RegisterEvent(smlProductionEventId.smlEVENT_AFTER_PRODUCTION_FIRED, "after-production-fired");
        RegisterEvent(smlProductionEventId.smlEVENT_BEFORE_PRODUCTION_RETRACTED, "before-production-retracted");

        // Agent manager
        RegisterEvent(smlAgentEventId.smlEVENT_AFTER_AGENT_CREATED, "after-agent-created");
        RegisterEvent(smlAgentEventId.smlEVENT_BEFORE_AGENT_DESTROYED, "before-agent-destroyed");
        RegisterEvent(smlSystemEventId.smlEVENT_BEFORE_AGENTS_RUN_STEP, "before-agents-run-step");
        RegisterEvent(smlAgentEventId.smlEVENT_BEFORE_AGENT_REINITIALIZED, "before-agent-reinitialized");
        RegisterEvent(smlAgentEventId.smlEVENT_AFTER_AGENT_REINITIALIZED, "after-agent-reinitialized");

        // Working memory changes
        RegisterEvent(smlWorkingMemoryEventId.smlEVENT_OUTPUT_PHASE_CALLBACK, "output-phase");

        // Raw XML messages
        RegisterEvent(smlXMLEventId.smlEVENT_XML_TRACE_OUTPUT, "xml-trace-output");
        RegisterEvent(smlXMLEventId.smlEVENT_XML_INPUT_RECEIVED, "xml-input-received");

        // Update events
        RegisterEvent(smlUpdateEventId.smlEVENT_AFTER_ALL_OUTPUT_PHASES, "after-all-output-phases");
        RegisterEvent(smlUpdateEventId.smlEVENT_AFTER_ALL_GENERATED_OUTPUT, "after-all-generated-output");

        // Error and print callbacks
        RegisterEvent(smlPrintEventId.smlEVENT_ECHO, "echo");
        RegisterEvent(smlPrintEventId.smlEVENT_PRINT, "print");

        // Untyped events
        RegisterEvent(smlStringEventId.smlEVENT_EDIT_PRODUCTION, "edit-production");
        RegisterEvent(smlStringEventId.smlEVENT_LOAD_LIBRARY, "load-library");

        // Rhs user function fired
        RegisterEvent(smlRhsEventId.smlEVENT_RHS_USER_FUNCTION, "rhs-user-function");
        RegisterEvent(smlRhsEventId.smlEVENT_FILTER, "filter");
        RegisterEvent(smlRhsEventId.smlEVENT_CLIENT_MESSAGE, "client-message");
    }

    public int ConvertToEvent(String pStr)
    {
        return InternalConvertToEvent(pStr);
    }

    public String ConvertToString(int id)
    {
        return InternalConvertToString(id);
    }

    protected void RegisterEvent(Enum<?> id, String pStr)
    {
        // Neither the id nor the name should be in use already
        assert (InternalConvertToEvent(pStr) == smlGenericEventId.smlEVENT_INVALID_EVENT.ordinal());
        assert (InternalConvertToString(id.ordinal()) == null);

        m_ToStringMap.put(id.ordinal(), pStr);
        m_ToEventMap.put(pStr, id.ordinal());
    }

    /***************************************************************************
     * @brief Convert from a string version of an event to the int (enum)
     *        version. Returns smlEVENT_INVALID_EVENT (== 0) if the string is
     *        not recognized.
     **************************************************************************/
    protected int InternalConvertToEvent(String pStr)
    {
        Integer id = m_ToEventMap.get(pStr);
        return id != null ? id.intValue() : smlGenericEventId.smlEVENT_INVALID_EVENT.ordinal();
    }

    /***************************************************************************
     * @brief Convert from int version of an event to the string form. Returns
     *        NULL if the id is not recognized.
     **************************************************************************/
    protected String InternalConvertToString(int id)
    {
        return m_ToStringMap.get(id);
    }

}
