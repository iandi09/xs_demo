package de.innovas.xsdemo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BufferController {
		
	static private final String URL_SAVE = "/save";
	static private final String PATH = "buffer.txt";
	
	@RequestMapping(method = RequestMethod.GET, value = URL_SAVE)
	public void saveInput(Model model, @RequestParam(value="in", required=false) String in) throws IOException {
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH, true)))) {
			Date date = new Date();
		    out.println(date + ": " + in);
		}catch (IOException e) {
		    throw e;
		}
	}
}