package com.valentin.shop.interfaces;

import java.util.List;

import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.entities.User;
import com.valentin.shop.models.Status;

public interface ProductDao {
	Status addProduct(Product product);
	boolean isUserExists(long userId);
	List<Product> getUserProducts(User user);
	List<Product> getAllProducts();
	List<Product> getProductsByCategoryId(ProductCategory category);
	Product getUserProduct(User user, long productId);
	Status editProduct(Product product, User user);
	Status deleteProduct(Product product);
	List<ProductCategory> getAllCategories();
	ProductCategory getCategoryById(int catId);
	Product getProductById(long productId);
	List<Product> searchProducts(String title, double minPrice, double maxPrice, int quantity);
}
