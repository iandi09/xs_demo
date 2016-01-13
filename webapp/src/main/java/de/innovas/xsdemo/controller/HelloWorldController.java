package de.innovas.xsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 
/*
 * author: Crunchify.com
 * 
 */
 
@Controller
public class HelloWorldController {
 
	@RequestMapping("/welcome")
	public String helloWorld() {
 
		return "welcome";
	}
}