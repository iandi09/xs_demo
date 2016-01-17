package de.innovas.xsdemo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	static private final String URL_EDIT_USER = "/user_edit";

	@RequestMapping(method = RequestMethod.GET, value = URL_ADD_USER)
	public String showUserForm(Model model, HttpSession session) {
		UserCommand userCommand = new UserCommand();
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    setPageParams(model, "user_add", name);
		model.addAttribute("userCommand", userCommand);
		return USER_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST, value = {URL_ADD_USER, URL_EDIT_USER})
	public String editUser(Model model, UserCommand userCommand) {
		User user;
		if(userCommand.isNewUser()) {
			user = new User(userCommand.getUsername(), userCommand.getPassword(), false);
		} else {
			Authentication auth = getAuthentication();
		    String name = auth.getName();
			user = SignedUser.getUser(name);
		}
		user.setEmail(userCommand.getEmail());
		user.setInfo(userCommand.getInfo());
		user.setVip(userCommand.isVip());
		SignedUser.addUser(user);
		return "redirect:" + USER_LIST_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URL_EDIT_USER)
	public String showUserEditForm(Model model) {
		UserCommand userCommand = new UserCommand();
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    setPageParams(model, "user_edit", name);
	    User user = SignedUser.getUser(name);
	    userCommand.setUsername(name);
	    userCommand.setEmail(user.getEmail());
	    userCommand.setInfo(user.getInfo());
	    userCommand.setVip(user.isVip());
		model.addAttribute("userCommand", userCommand);
		return USER_VIEW;
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_USER_LIST)
	public String showUserList(Model model) {
		List<User> userList = SignedUser.getUsers();
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    setPageParams(model, "user_list", name);
		model.addAttribute("userList", userList);
		return USER_LIST_VIEW;
	}
	
	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	private void setPageParams(Model model, String currentPage, String currentUser) {
		model.addAttribute("isAdmin", SignedUser.getUser(currentUser).isVip());
	    model.addAttribute("page", currentPage);
	    model.addAttribute("username", currentUser);
	}
}