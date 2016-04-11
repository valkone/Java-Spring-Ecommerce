package com.valentin.shop.rest;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.interfaces.ProductService;

@RestController
public class ProductRest {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productSearch")
    public List<ProductDto> productSearch(@RequestParam(value="title") String title) {
		List<Product> products = this.productService.searchProducts(title);
		List<ProductDto> productsDto = new ArrayList<>();
		
		for(Product product : products) {
			ProductDto productDto = new ProductDto();
			productDto.setDescription(product.getDescription());
			productDto.setId(product.getId());
			productDto.setName(product.getName());
			productDto.setPictureUrl(product.getPictureUrl());
			productDto.setPrice(product.getPrice());
			productDto.setQuantity(product.getQuantity());
			
			productsDto.add(productDto);
		}
		
		return productsDto;
    }
}
