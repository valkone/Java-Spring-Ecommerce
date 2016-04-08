package com.valentin.shop.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductDao;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.models.Status;

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
		return this.productDao.addProduct(product);
	}

	@Override
	public List<Product> getUserProducts(User user) {
		if(!this.productDao.isUserExists(user.getId())) {
			return null;
		}
		
		return this.productDao.getUserProducts(user);
	}

	@Override
	public Product getUserProduct(User user, long productId) {
		return this.productDao.getUserProduct(user, productId);
	}

	@Override
	public Status editProduct(ProductDto productDto, User user) {
		Product product = this.modelMapper.map(productDto, Product.class);
		return this.productDao.editProduct(product, user);
	}
}
