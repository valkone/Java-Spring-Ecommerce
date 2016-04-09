package com.valentin.shop.interfaces;

import java.util.List;

import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.User;
import com.valentin.shop.models.Status;

public interface ProductService {
	Status addProduct(ProductDto product, User activeUser);
	List<Product> getUserProducts(User user);
	Product getUserProduct(User user, long productId);
	Status editProduct(ProductDto product, User user);
	Status deleteProduct(long productId, User user);
}