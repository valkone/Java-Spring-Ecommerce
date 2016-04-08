package com.valentin.shop.interfaces;

import java.util.List;

import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.User;
import com.valentin.shop.models.Status;
import com.valetnin.shop.dto.ProductDto;

public interface ProductService {
	Status addProduct(ProductDto product, User activeUser);
	List<Product> getUserProducts(User user);
}