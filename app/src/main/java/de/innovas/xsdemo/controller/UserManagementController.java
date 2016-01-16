package de.innovas.xsdemo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.innovas.xsdemo.command.UserCommand;
import de.innovas.xsdemo.management.SignedUser;
import de.innovas.xsdemo.management.User;

@Controller
public class UserManagementController {
		
	static private final String URL_ADD_USER = "/add_user";
	static private final String USER_VIEW = "user";

	static private final String URL_USER_LIST = "/user_list";
	static private final String USER_LIST_VIEW = "user_list";

	@RequestMapping(method = RequestMethod.GET, value = URL_ADD_USER)
	public String showUserForm(Model model) {
		UserCommand userCommand = new UserCommand();
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    model.addAttribute("isAdmin", SignedUser.getUser(name).isVip());
		model.addAttribute("userCommand", userCommand);
		return USER_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST, value = URL_ADD_USER)
	public String addUser(Model model, UserCommand userCommand) {
		User user = new User(userCommand.getUsername(), userCommand.getPassword(), false);
		user.setEmail(userCommand.getEmail());
		user.setVip(userCommand.isVip());
		SignedUser.addUser(user);
		return "redirect:" + USER_LIST_VIEW;
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_USER_LIST)
	public String showUserList(Model model) {
		List<User> userList = SignedUser.getUsers();
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    model.addAttribute("isAdmin", SignedUser.getUser(name).isVip());
		model.addAttribute("userList", userList);
		return USER_LIST_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String showTest(Model model) {

		return "test";
	}
	
	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}