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
			User user2 = new User("user2", "user2", false);
			user2.setEmail("user2@gmx.de");
			put("user2", user2);
			User user3 = new User("user3", "user3", false);
			user3.setEmail("user3@hotmail.com");
			put("user3", user3);
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
