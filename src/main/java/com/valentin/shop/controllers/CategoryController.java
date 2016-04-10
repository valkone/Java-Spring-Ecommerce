package com.valentin.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.interfaces.ProductService;

@Controller
public class CategoryController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String categoryView(Model model, @RequestParam(value = "id") int id) {
		List<ProductCategory> categories = this.productService.getAllCategories();
		model.addAttribute("categories", categories);
		List<Product> product = this.productService.getProductsByCategoryId(id);
		model.addAttribute("products", product);
		
		return "category";
	}
}
