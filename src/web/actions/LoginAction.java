package web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import web.bussiness.LoginBO;
import web.forms.LoginForm;
import web.data.User;

public class LoginAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		LoginForm loginForm = (LoginForm) form;
		LoginBO loginBO = new LoginBO();
		String username = loginForm.getUsername();
		if (loginBO.checkLogin(username, loginForm.getPassword())) {
			User user = loginBO.getUser(username);
			request.getSession().setAttribute("user", user);
			if (user.hasRole("admin"))
				return findForwardAdminSuccess(mapping);
			else
				return findForwardUserSuccess(mapping);
		}
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
				"error.authorization.invalid"));
		request.setAttribute(Globals.ERROR_KEY, errors);
		return findForwardFailure(mapping);
	}
}
