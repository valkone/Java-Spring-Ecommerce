package com.valentin.shop.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.models.Status;
import com.valentin.shop.dto.ProductDto;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="myProducts", method=RequestMethod.GET)
	public String myProducts(Principal principal, Model model) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		List<Product> products = this.productService.getUserProducts(activeUser);
		
		model.addAttribute("products", products);
		
		return "myProducts";
	}
	
	@RequestMapping(value="addProduct", method=RequestMethod.GET)
	public String addProductView(Principal principal) {
		return "addProduct";
	}
	
	
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") ProductDto product, Principal principal, Model model) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		Status status = this.productService.addProduct(product, activeUser);
		model.addAttribute("status", status);
		
		return "addProduct";
	}
	
	@RequestMapping(value="productEdit", method=RequestMethod.GET)
	public String productEditView(Principal principal, Model model, @RequestParam("id") int productId) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		Product product = this.productService.getUserProduct(activeUser, productId);
		model.addAttribute("product", product);
		if(product == null) {
			model.addAttribute("productError", "Invalid product or you don't have permission to edit it");
		}
		
		return "productEdit";
	}
	
	@RequestMapping(value="productEdit", method=RequestMethod.POST)
	public String productEdit(@ModelAttribute("product") ProductDto product, Principal principal, Model model) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		Status status = this.productService.editProduct(product, activeUser);
		model.addAttribute("status", status);
		
		return "addProduct";
	}
}
