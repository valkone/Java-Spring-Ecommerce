package com.valentin.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.models.CartProduct;
import com.valentin.shop.models.Status;

@Controller
@SessionAttributes({"cart"})
public class CartController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cartView(Model model) {
		List<ProductCategory> categories = this.productService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "cart";
	}
	
	@RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
	public String buyProduct(@ModelAttribute("cart") List<CartProduct> cart, Model model) {
		Status status = this.productService.buyProducts(cart);
		model.addAttribute("status", status);
		return "cart";
	}
}
