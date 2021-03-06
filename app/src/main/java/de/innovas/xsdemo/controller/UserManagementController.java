package de.innovas.xsdemo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.innovas.xsdemo.command.UserCommand;
import de.innovas.xsdemo.management.SignedUser;
import de.innovas.xsdemo.management.User;

@Controller
public class UserManagementController {
		
	static private final String URL_HOME = "/home";
	
	static private final String URL_ADD_USER = "/user_add";
	static private final String USER_VIEW = "user";

	static private final String URL_USER_LIST = "/user_list";
	static private final String USER_LIST_VIEW = "user_list";
	
	static private final String URL_EDIT_USER = "/user_edit";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new HtmlEscape());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = URL_ADD_USER)
	public String showUserForm(Model model, HttpSession session) {
		UserCommand userCommand = new UserCommand();
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    setPageParams(model, name);
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
	    	return "redirect:" + URL_HOME;
	    }
	    userCommand.setUsername(user.getUsername());
	    userCommand.setEmail(user.getEmail());
	    userCommand.setInfo(user.getInfo());
	    userCommand.setVip(user.isVip());
	    userCommand.setNewUser(false);
		model.addAttribute("userCommand", userCommand);
		setPageParams(model, name);
		return USER_VIEW;
	}

	@RequestMapping(method = RequestMethod.POST, value = {URL_ADD_USER, URL_EDIT_USER})
	public String editUser(Model model, UserCommand userCommand) {
		Authentication auth = getAuthentication();
		if (!auth.isAuthenticated()) {
			return "redirect:" + URL_HOME;
		}
	    String name = auth.getName();
		User currentUser = SignedUser.getUser(name);
		User user;
		if(userCommand.isNewUser()) {
			if (!currentUser.isVip() || (userCommand.getPassword() == null)) {
				return "redirect:" + URL_HOME;
			}
			user = new User(userCommand.getUsername(), userCommand.getPassword(), userCommand.isVip());
		} else {
			user = SignedUser.getUser(userCommand.getUsername());
//			if (currentUser.isVip()) {
				user.setVip(userCommand.isVip());
//			}
			String pwd = userCommand.getPassword();
			if (pwd != null && !pwd.isEmpty()) {
				user.setPassword(userCommand.getPassword());
			}
		}
		user.setEmail(userCommand.getEmail());
		user.setInfo(userCommand.getInfo());
		if (userCommand.isNewUser()) {
			SignedUser.addUser(user);
		}
		return "redirect:" + URL_HOME;
	}

	@RequestMapping(method = RequestMethod.GET, value = URL_USER_LIST)
	public String showUserList(Model model) {
		List<User> userList = SignedUser.getUsers();
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    setPageParams(model, name);
		model.addAttribute("userList", userList);
		return USER_LIST_VIEW;
	}
	
	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	private void setPageParams(Model model, String currentUser) {
		model.addAttribute("isAdmin", SignedUser.getUser(currentUser).isVip());
	    model.addAttribute("username", currentUser);
	}
}