package com.valentin.shop.interfaces;

import java.util.List;

import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.entities.User;

public interface ProductDao {
	boolean addProduct(Product product);
	boolean isUserExists(long userId);
	List<Product> getUserProducts(User user);
	List<Product> getAllProducts();
	List<Product> getProductsByCategoryId(ProductCategory category);
	Product getUserProduct(User user, long productId);
	boolean editProduct(Product product);
	boolean deleteProduct(Product product);
	List<ProductCategory> getAllCategories();
	ProductCategory getCategoryById(int catId);
	Product getProductById(long productId);
	List<Product> searchProducts(String title, double minPrice, double maxPrice, int quantity);
	boolean updateProducts(List<Product> products);
	List<Product> getProductsByPage(int page);
}
