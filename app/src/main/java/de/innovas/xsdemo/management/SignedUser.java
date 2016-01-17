package de.innovas.xsdemo.management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignedUser {

	private static HashMap<String, User> users = new HashMap<String, User>(){
		private static final long serialVersionUID = 2699412997359059987L;

		{
			User admin = new User("admin", "admin", true);
			admin.setEmail("admin@xsdemo.de");
			put("admin", admin);
			User user = new User("user", "user", false);
			user.setEmail("user@web.de");
			put("user", user);
		}
	};

	public static void addUser(User user) {
		users.put(user.getUsername(), user);
	}
	
	public static User getUser(String username) {
		return users.get(username);
	}
	
	public static List<User> getUsers() {
		List<User> userList = new ArrayList<>();
		for (User user : users.values()) {
			userList.add(user);
		}
		return userList;
	}
}
