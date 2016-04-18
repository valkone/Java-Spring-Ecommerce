package com.valentin.shop.rest;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.valentin.shop.constants.PathConstants;
import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.models.CartProduct;
import com.valentin.shop.models.Status;

@RestController
@SessionAttributes({"cart"})
public class ProductRest {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@RequestMapping(value = PathConstants.PRODUCT_SEARCH_REST, method = RequestMethod.GET)
    public List<ProductDto> productSearch(@RequestParam(value="title") String title,
			@RequestParam(value="minPrice") double minPrice,
			@RequestParam(value="maxPrice") double maxPrice,
			@RequestParam(value="quantity") int quantity) {
		
		List<Product> products = this.productService.searchProducts(title, minPrice, maxPrice, quantity);
		List<ProductDto> productsDto = new ArrayList<>();
		
		for(Product product : products) {
			ProductDto productDto = new ProductDto();
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setPrice(product.getPrice());
			productDto.setQuantity(product.getQuantity());
			
			productsDto.add(productDto);
		}
		
		return productsDto;
    }
	
	@RequestMapping(value = PathConstants.ADD_PRODUCT_TO_CART_REST, method = RequestMethod.GET)
    public Status addProductToCart(@RequestParam(value="productId") int productId, 
    		@RequestParam(value="quantity") int quantity, @ModelAttribute("cart") List<CartProduct> cart) {
		
		return this.productService.addProductToCart(productId, quantity, cart);
	}
	
	@RequestMapping(value = PathConstants.GET_PRODUCTS_BY_PAGES, method = RequestMethod.GET)
    public List<ProductDto> getProductByPages(@RequestParam(value="page") int page) {
		page--; // first page will be 1 not 0
		List<Product> products = this.productService.getProductsByPage(page);
		List<ProductDto> productsDto = new ArrayList<>();
		
		for(Product product : products) {
			ProductDto productDto = new ProductDto();
			productDto.setName(product.getName());
			productDto.setDescription(product.getDescription());
			productDto.setPictureUrl(product.getPictureUrl());
			productDto.setPrice(product.getPrice());
			productDto.setId(product.getId());
			productDto.setQuantity(product.getQuantity());
			
			productsDto.add(productDto);
		}
		
		return productsDto;
	}
}
