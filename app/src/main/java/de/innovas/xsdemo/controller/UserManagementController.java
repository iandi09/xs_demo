package de.innovas.xsdemo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	    userCommand.setNewUser(true);
		model.addAttribute("userCommand", userCommand);
		return USER_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URL_EDIT_USER)
	public String showUserEditForm(Model model, @RequestParam(value="user", required=false) String username) {
		UserCommand userCommand = new UserCommand();
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    User currentUser = SignedUser.getUser(name);
	    User user;
	    
	    if (username == null || username.isEmpty()) {
	    	user = currentUser;
	    } else if (currentUser.isVip()){
	    	user = SignedUser.getUser(username);
	    } else {
	    	return "redirect:" + "/home";
	    }
	    userCommand.setUsername(user.getUsername());
	    userCommand.setEmail(user.getEmail());
	    userCommand.setInfo(user.getInfo());
	    userCommand.setVip(user.isVip());
	    userCommand.setNewUser(false);
		model.addAttribute("userCommand", userCommand);
		setPageParams(model, "user_edit", name);
		return USER_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST, value = {URL_ADD_USER, URL_EDIT_USER})
	public String editUser(Model model, UserCommand userCommand) {
		Authentication auth = getAuthentication();
	    String name = auth.getName();
		User currentUser = SignedUser.getUser(name);
		User user;
		String landingPage;
		if(userCommand.isNewUser()) {
			if (!currentUser.isVip()) {
				return "redirect:" + "/home";
			}
			user = new User(userCommand.getUsername(), userCommand.getPassword(), false);
			user.setVip(userCommand.isVip());
			landingPage = URL_ADD_USER;
		} else {
			user = SignedUser.getUser(userCommand.getUsername());
			if (currentUser.isVip()) {
				user.setVip(userCommand.isVip());
			}
			landingPage = URL_EDIT_USER;
		}
		user.setEmail(userCommand.getEmail());
		user.setInfo(userCommand.getInfo());
		if (userCommand.isNewUser()) {
			SignedUser.addUser(user);
		}
		return "redirect:" + landingPage;
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