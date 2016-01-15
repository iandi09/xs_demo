package de.innovas.xsdemo.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.innovas.xsdemo.command.LoginCommand;
import de.innovas.xsdemo.management.User;
import de.innovas.xsdemo.management.UserList;

@Controller
public class HelloWorldController {
	
	private HashMap<String, User> users;

	private static final String LOGIN_URL = "/login";
	private static final String LOGIN_VIEW = "login";

	private static final String LOGOUT_URL = "/logout";
	
	public HelloWorldController() {
		UserList userList = new UserList();
		users = userList.getUsers();
	}

//	@RequestMapping("/")
//	public String root(Model model, HttpSession session) {
//
//		Object user = session.getAttribute("user");
//		if (user == null) {
//			return "redirect:login";
//		}
//		model.addAttribute("user", user);
//		return LOGIN_VIEW;
//	}

	@RequestMapping("/test")
	public String helloWorld(Model model, HttpSession session) {

		Object user = session.getAttribute("user");
		if (user == null) {
			return "redirect:login";
		}
		model.addAttribute("user", user);
		return "test2";
	}

	@RequestMapping(method = RequestMethod.GET, value = LOGIN_URL+"sss")
	public String showLogin(Model model, HttpSession session) { // , @RequestParam(required =
												// true) String user) {
		LoginCommand loginCommand = new LoginCommand();
		model.addAttribute("loginCommand", loginCommand);
		//session.setAttribute("user", user);

		return LOGIN_VIEW;
		// return "redirect:test";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = LOGIN_URL+"s2")
	public String login(Model model, HttpSession session) { // , @RequestParam(required =
												// true) String user) {

		//session.setAttribute("user", user);

		return LOGIN_VIEW;
		// return "redirect:test";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = LOGIN_URL)
	public String test(Model model, HttpSession session, HttpServletRequest request) { // , @RequestParam(required =
												// true) String user) {
		String username = request.getParameter("username");
		String password = users.get(username).getPassword();
		System.out.println(username);
		//session.setAttribute("user", user);

		return "test";
		// return "redirect:test";
	}

	@RequestMapping(LOGOUT_URL)
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:test";
	}

}