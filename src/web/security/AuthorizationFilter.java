package web.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import web.data.User;

public class AuthorizationFilter implements Filter {
	private String[] roleNames;
	private String onErrorUrl;
	
	public void init(FilterConfig arg0) throws ServletException {
		String roles = arg0.getInitParameter("roles"); 
	    if (roles == null || "".equals(roles)) {
	      roleNames = new String[0];
	    }
	    else {
	      roles.trim();
	      roleNames = roles.split(" ");
	    }
	    onErrorUrl = arg0.getInitParameter("onError"); 
	    if (onErrorUrl == null || "".equals(onErrorUrl)) {
	      onErrorUrl = "/login.jsp";
	    }
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0; 
	    HttpServletResponse res = (HttpServletResponse) arg1;
	    HttpSession session = req.getSession();
	    User user = (User) session.getAttribute("user"); 
	    ActionErrors errors = new ActionErrors();
	    if (user == null) {
	      errors.add(ActionErrors.GLOBAL_MESSAGE,
	        new ActionMessage("error.authentication.required"));
	    }
	    else {
	      boolean hasRole = false;
	      for (int i=0; i < roleNames.length; i++) {
	        if (user.hasRole(roleNames[i])) {
	          hasRole = true;
	          break;
	        }
	      }
	      if (!hasRole) {
	        errors.add(ActionErrors.GLOBAL_MESSAGE,
	          new ActionMessage("error.authorization.nopermission", user.getUsername()));
	      }
	    }
	    if (errors.isEmpty()) {
	    	arg2.doFilter(arg0, arg1);
	    }
	    else {
	      req.setAttribute(Globals.ERROR_KEY, errors); 
	      req.getRequestDispatcher(onErrorUrl).forward(req, res);
	    }
	}	
	
	public void destroy() {	}
}
