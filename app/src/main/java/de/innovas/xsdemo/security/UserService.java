package de.innovas.xsdemo.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import de.innovas.xsdemo.management.SignedUser;
import de.innovas.xsdemo.management.User;

@Service("userService")
public class UserService {	
	
	public Map<String, Object> getUserByUsername(String username) {
		Map<String, Object> userMap = null;
		User user = SignedUser.getUser(username);
		if (user != null) {
			userMap = new HashMap<>();
			userMap.put("username", user.getUsername());
			userMap.put("password", user.getPassword());
			userMap.put("role", (user.isVip() ? "admin" : "user"));
		}
		return userMap;
	}
}