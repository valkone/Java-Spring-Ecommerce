package com.valentin.shop.interfaces;

import com.valentin.shop.entities.Product;
import com.valentin.shop.models.Status;

public interface ProductDao {
	Status addProduct(Product product);
}
