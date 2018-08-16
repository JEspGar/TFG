package web.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BaseAction extends Action {
	protected ActionForward findForwardSuccess(ActionMapping mapping) {
		return mapping.findForward("success");
	}
	
	protected ActionForward findForwardAdminSuccess(ActionMapping mapping) {
		return mapping.findForward("adminsuccess");
	}
	
	protected ActionForward findForwardProfesorSuccess(ActionMapping mapping) {
		return mapping.findForward("profesorsuccess");
	}
	
	protected ActionForward findForwardFailure(ActionMapping mapping) {
		return mapping.findForward("failure");
	}
}