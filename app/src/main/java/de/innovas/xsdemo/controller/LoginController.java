package de.innovas.xsdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.innovas.xsdemo.management.SignedUser;

@Controller
public class LoginController {

	static private final String ROOT = "/";
	
	static private final String URL_LOGIN = "/login";
	static private final String LOGIN_VIEW = "login";
	
	static private final String URL_HOME = "/home";
	static private final String HOME_VIEW = "home";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new HtmlEscape());
	}

	@RequestMapping( method=RequestMethod.GET ,value = ROOT)
	public String redirectToLogin(Model model) {
		Authentication auth = getAuthentication();
		if (!auth.isAuthenticated()){
			return "redirect:" + URL_LOGIN;
		} else {
			return "redirect:" + URL_HOME;
		}
	}
	
	@RequestMapping( method=RequestMethod.GET ,value = URL_LOGIN)
	public String showLogin(Model model) {
		Authentication auth = getAuthentication();
		model.addAttribute("loggedIn", auth.isAuthenticated());
		return LOGIN_VIEW;
	}
	
	@RequestMapping( method=RequestMethod.GET ,value = URL_HOME)
	public String showHome(Model model, @RequestParam(value="q", required=false) String query) {
		Authentication auth = getAuthentication();
	    String name = auth.getName();
	    String q;
	    if (query == null || query.isEmpty()) {
	    	q = "";
	    } else {
	    	q = query;
	    }
	    model.addAttribute("query", query);
	    model.addAttribute("isAdmin", SignedUser.getUser(name).isVip());
		model.addAttribute("username", name);
		return HOME_VIEW;
	}
	
	private Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
}
