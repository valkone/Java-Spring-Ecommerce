package com.valentin.shop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductDao;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.models.Status;
import com.valetnin.shop.dto.ProductDto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Status addProduct(ProductDto productDto, User activeUser) {
		Product product = this.modelMapper.map(productDto, Product.class);
		product.setUser(activeUser);
		this.productDao.addProduct(product);
		
		return null;
	}

}
