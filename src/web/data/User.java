package web.data;

import java.util.Arrays;
import java.util.Collection;

public class User {
	private String username;
	private Collection<String> roles;

	public User(String username, String[] roles) {
		super();
		this.username = username;
		this.roles = Arrays.asList(roles);
	}
	
	public boolean hasRole(String role) {
		return roles.contains(role);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getRoles() {
		return (String[]) roles.toArray();
	}

	public void setRoles(String[] roles) {
		this.roles = Arrays.asList(roles);
	}

	@Override
	public String toString() {
		return "User [roles=" + Arrays.toString(roles.toArray()) + ", username="
				+ username + "]";
	}
}
