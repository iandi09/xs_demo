package de.innovas.xsdemo.management;

import java.util.HashMap;

public class UserList {
	
	private static final HashMap<String, User> users = new HashMap<String, User>() {

		private static final long serialVersionUID = 1L;
	{
		put("admin", new User("admin", "admin", true));
		put("fred", new User("fred", "fred", false));
		
	}};
	
	public HashMap<String, User> getUsers() {
		return users;
	}

}
