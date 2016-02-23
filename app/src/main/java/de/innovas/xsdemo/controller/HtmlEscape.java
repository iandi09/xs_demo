package de.innovas.xsdemo.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.util.HtmlUtils;

public class HtmlEscape extends StringTrimmerEditor{

	public HtmlEscape() {
		super(true);
	}
	
	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		}
		else {
			//setValue(HtmlUtils.htmlEscape(text));
			setValue(text);
		}
	}
}
	
