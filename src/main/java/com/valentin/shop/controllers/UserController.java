package com.valentin.shop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;
import com.valentin.shop.constants.PathConstants;
import com.valentin.shop.dto.RegisterDto;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.interfaces.StatusInterface;
import com.valentin.shop.interfaces.UserService;
import com.valentin.shop.models.Status;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = PathConstants.LOGIN_VIEW, method = RequestMethod.GET)
	public String login(Model model) {
		List<ProductCategory> categories = this.productService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "login";
	}
	
	@RequestMapping(value = PathConstants.REGISTER_VIEW, method = RequestMethod.GET)
	public String register(Model model) {
		List<ProductCategory> categories = this.productService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "register";
	}
	
	@RequestMapping(value = PathConstants.REGISTER_POST, method = RequestMethod.POST)
	public String register(Model model, @ModelAttribute("registration") RegisterDto registrationModel) {		
		Status status = this.userService.register(registrationModel);
		
		model.addAttribute("username", HtmlUtils.htmlEscape(registrationModel.getUsername()));
		model.addAttribute("email", HtmlUtils.htmlEscape(registrationModel.getEmail()));
		model.addAttribute("status", status);
		return "register";
	}
}
