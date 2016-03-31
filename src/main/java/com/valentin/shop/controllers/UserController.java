package com.valentin.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.tags.HtmlEscapeTag;
import org.springframework.web.util.HtmlUtils;

import com.valentin.shop.interfaces.UserService;
import com.valentin.shop.models.Status;
import com.valetnin.shop.dto.RegisterDto;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute("registration") RegisterDto registrationModel) throws Exception {
		Status status = this.userService.register(registrationModel);
		
		model.addAttribute("username", HtmlUtils.htmlEscape(registrationModel.getUsername()));
		model.addAttribute("email", HtmlUtils.htmlEscape(registrationModel.getEmail()));
		model.addAttribute("status", status);
		return "register";
	}
}
