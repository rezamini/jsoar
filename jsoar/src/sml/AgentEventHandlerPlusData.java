/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.35
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sml;

public class AgentEventHandlerPlusData extends EventHandlerPlusData {
    Kernel.AgentEventInterface m_Handler;
    /*
  private long swigCPtr;

  protected AgentEventHandlerPlusData(long cPtr, boolean cMemoryOwn) {
    super(smlJNI.SWIGAgentEventHandlerPlusDataUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(AgentEventHandlerPlusData obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }
*/
  public synchronized void delete() {
      /*
    if(swigCPtr != 0 && swigCMemOwn) {
      swigCMemOwn = false;
      smlJNI.delete_AgentEventHandlerPlusData(swigCPtr);
    }
    swigCPtr = 0;
    */
    super.delete();
  }

  public void setM_Handler(SWIGTYPE_p_f_enum_sml__smlAgentEventId_p_void_p_sml__Agent__void value) {
//    smlJNI.AgentEventHandlerPlusData_m_Handler_set(swigCPtr, this, SWIGTYPE_p_f_enum_sml__smlAgentEventId_p_void_p_sml__Agent__void.getCPtr(value));
    throw new UnsupportedOperationException();
  }

  public SWIGTYPE_p_f_enum_sml__smlAgentEventId_p_void_p_sml__Agent__void getM_Handler() {
//    long cPtr = smlJNI.AgentEventHandlerPlusData_m_Handler_get(swigCPtr, this);
//    return (cPtr == 0) ? null : new SWIGTYPE_p_f_enum_sml__smlAgentEventId_p_void_p_sml__Agent__void(cPtr, false);
      throw new UnsupportedOperationException();
  }

  public AgentEventHandlerPlusData() {
//    this(smlJNI.new_AgentEventHandlerPlusData__SWIG_0(), true);
  }

  public AgentEventHandlerPlusData(int eventID, SWIGTYPE_p_f_enum_sml__smlAgentEventId_p_void_p_sml__Agent__void handler, SWIGTYPE_p_void userData, int callbackID) {
//    this(smlJNI.new_AgentEventHandlerPlusData__SWIG_1(eventID, SWIGTYPE_p_f_enum_sml__smlAgentEventId_p_void_p_sml__Agent__void.getCPtr(handler), SWIGTYPE_p_void.getCPtr(userData), callbackID), true);
      throw new UnsupportedOperationException();
  }

}
