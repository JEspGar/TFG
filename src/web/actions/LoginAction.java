package web.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import web.forms.LoginForm;
import web.recursos.gestionUsuarios;
import web.data.User;

public class LoginAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		//Se obtiene el driver y la url de la BBDD del fichero web.xml
	    String driver = this.getServlet().getServletContext().getInitParameter("driver");
	    String url = this.getServlet().getServletContext().getInitParameter("url_bbdd");
	    String usuario = this.getServlet().getServletContext().getInitParameter("usuario");
	    String password = this.getServlet().getServletContext().getInitParameter("password");
	       
		LoginForm loginForm = (LoginForm) form;
		String username = loginForm.getUsername();
		
		gestionUsuarios gUsuarios=new gestionUsuarios(driver,url,usuario,password);
		String role=gUsuarios.comprobarUsuario(username, loginForm.getPassword());
		if(!role.isEmpty()) {
			User user = new User(username, new String[] {role});
			request.getSession().setAttribute("user", user);
			if (user.hasRole("admin"))
				return findForwardAdminSuccess(mapping);
			else if(user.hasRole("profesor"))
				return findForwardProfesorSuccess(mapping);
		}
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
				"error.authorization.invalid"));
		request.setAttribute(Globals.ERROR_KEY, errors);
		return findForwardFailure(mapping);
	}
}
