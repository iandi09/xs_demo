package de.innovas.xsdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
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