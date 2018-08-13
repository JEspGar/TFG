package web.bussiness;

import web.data.User;

public class LoginBO {
	public boolean checkLogin(String username, String password) {
		// TODO: Now this is a stub
		return (username.equals("admin") && password.equals("admin"))
				|| (username.equals("user") && password.equals("user"));
	}

	public User getUser(String username) {
		// TODO: Now this is a stub
		if (username.equals("admin"))
			return new User(username, new String[] { "user", "admin" });
		else if (username.equals("user"))
			return new User(username, new String[] { "user" });
		else
			return null;
	}
}
