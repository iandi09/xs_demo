package de.innovas.xsdemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * author: Crunchify.com
 *
 */

@Controller
public class HelloWorldController {

  @RequestMapping( "/" )
  public String root( Model model, HttpSession session ) {

    Object user = session.getAttribute( "user" );
    if ( user == null ) {
      return "redirect:login";
    }
    model.addAttribute( "user", user );
    return "redirect:test";
  }

  @RequestMapping( "/test" )
  public String helloWorld( Model model, HttpSession session ) {

    Object user = session.getAttribute( "user" );
    if ( user == null ) {
      return "redirect:login";
    }
    model.addAttribute( "user", user );
    return "test2";
  }

  @RequestMapping( "/login" )
  public String login( HttpSession session, @RequestParam( required = true ) String user) {

    session.setAttribute( "user", user );

    return "redirect:test";
  }

  @RequestMapping( "/logout" )
  public String logout( HttpSession session ) {

    session.invalidate();

    return "redirect:test";
  }

}