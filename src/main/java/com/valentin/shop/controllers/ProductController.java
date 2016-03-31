package com.valentin.shop.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductService;
import com.valetnin.shop.dto.ProductDto;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="myProducts", method=RequestMethod.GET)
	public String myProducts() {
		return "myProducts";
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.GET)
	public String addProductView() {
		return "addProduct";
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") ProductDto product, Principal principal) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		this.productService.addProduct(product, activeUser);
		return "addProduct";
	}
}
