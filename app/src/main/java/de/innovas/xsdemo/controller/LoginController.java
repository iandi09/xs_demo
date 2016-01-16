package de.innovas.xsdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.innovas.xsdemo.management.SignedUser;

@Controller
public class LoginController {

	static private final String ROOT = "/";
	static private final String URL_LOGIN = "/login";
	static private final String LOGIN_VIEW = "login";
	
	static private final String URL_HOME = "/home";
	static private final String HOME_VIEW = "home";
	

	@RequestMapping( method=RequestMethod.GET ,value = ROOT)
	public String redirectToLogin(Model model) {
		Authentication auth = getAuthentication();
		if (!auth.isAuthenticated()){
			return "redirect:" + LOGIN_VIEW;
		} else {
			return "redirect:" + HOME_VIEW;
		}
	}
	
	@RequestMapping( method=RequestMethod.GET ,value = URL_LOGIN)
	public String showLogin(Model model) {
		Authentication auth = getAuthentication();
		model.addAttribute("loggedIn", auth.isAuthenticated());
		return LOGIN_VIEW;
	}
	
	@RequestMapping( method=RequestMethod.GET ,value = URL_HOME)
	public String showHome(Model model) {
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    model.addAttribute("isAdmin", SignedUser.getUser(name).isVip());
		model.addAttribute("username", name);
		return HOME_VIEW;
	}
	
	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
}
