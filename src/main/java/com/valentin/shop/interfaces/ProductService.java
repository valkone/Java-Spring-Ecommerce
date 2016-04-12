package com.valentin.shop.interfaces;

import java.util.List;

import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.entities.User;
import com.valentin.shop.models.CartProduct;
import com.valentin.shop.models.Status;

public interface ProductService {
	Status addProduct(ProductDto product, User activeUser, ProductCategory category);
	List<Product> getUserProducts(User user);
	List<Product> getAllProducts();
	List<Product> getProductsByCategoryId(int catId);
	Product getUserProduct(User user, long productId);
	Status editProduct(ProductDto product, User user);
	Status deleteProduct(long productId, User user);
	List<ProductCategory> getAllCategories();
	ProductCategory getCategoryById(int catId);
	Product getProductById(long productId);
	List<Product> searchProducts(String title, double minPrice, double maxPrice, int quantity);
	Status addProductToCart(int productId, int quantity, List<CartProduct> cart);
}