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
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.interfaces.StatusInterface;
import com.valentin.shop.models.Status;
import com.valentin.shop.constants.PathConstants;
import com.valentin.shop.dto.ProductDto;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = PathConstants.MY_PRODUCT_VIEW, method = RequestMethod.GET)
	public String myProducts(Principal principal, Model model) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		List<Product> products = this.productService.getUserProducts(activeUser);
		model.addAttribute("products", products);
		List<ProductCategory> categories = this.productService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "myProducts";
	}
	
	@RequestMapping(value = PathConstants.ADD_PRODUCT_VIEW, method = RequestMethod.GET)
	public String addProductView(Principal principal, Model model) {
		List<ProductCategory> categories = this.productService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "addProduct";
	}
	
	
	@RequestMapping(value = PathConstants.ADD_PRODUCT_POST, method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") ProductDto product, Principal principal, Model model) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		Status status = this.productService.addProduct(product, activeUser);
		model.addAttribute("status", status);
		
		return "addProduct";
	}
	
	@RequestMapping(value = PathConstants.PRODUCT_EDIT_VIEW, method = RequestMethod.GET)
	public String productEditView(Principal principal, Model model, @RequestParam("id") int productId) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		Product product = this.productService.getUserProduct(activeUser, productId);
		model.addAttribute("product", product);
		if(product == null) {
			model.addAttribute("productError", "Invalid product or you don't have permission to edit it");
		}
		
		return "productEdit";
	}
	
	@RequestMapping(value = PathConstants.PRODUCT_EDIT_POST, method = RequestMethod.POST)
	public String productEdit(@ModelAttribute("product") ProductDto product, Principal principal, Model model) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		Status status = this.productService.editProduct(product, activeUser);
		model.addAttribute("status", status);
		
		return "addProduct";
	}
	
	@RequestMapping(value = PathConstants.PRODUCT_DELETE_POST, method = RequestMethod.POST)
	public String deleteProduct(Principal principal, @RequestParam("productId") long productId) {
		User activeUser = (User) ((Authentication) principal).getPrincipal();
		this.productService.deleteProduct(productId, activeUser);
		
		return "redirect:/myProducts";
	}
	
	@RequestMapping(value = PathConstants.PRODUCT_VIEW, method = RequestMethod.GET)
	public String productView(Model model, @RequestParam("id") long productId) {
		Product product = this.productService.getProductById(productId);
		model.addAttribute("product", product);
		
		return product != null ? "product" : "redirect:/home";
	}
	
	@RequestMapping(value = PathConstants.SEARCH_VIEW, method = RequestMethod.GET)
	public String searchView(Model model) {
		List<ProductCategory> categories = this.productService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "search";
	}
}
