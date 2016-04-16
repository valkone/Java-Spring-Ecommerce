package com.valentin.shop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.models.CartProduct;

@Controller
@SessionAttributes({"cart"})
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		// Initialize cart session
		if(!model.containsAttribute("cart")) {
			model.addAttribute("cart", new ArrayList<CartProduct>());	
		}
		
		List<ProductCategory> categories = this.productService.getAllCategories();
		model.addAttribute("categories", categories);
		// TODO: pagination
		List<Product> products = this.productService.getAllProducts();
		model.addAttribute("products", products);
		
		return "home";
	}
}
