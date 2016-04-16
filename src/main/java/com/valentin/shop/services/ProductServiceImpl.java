package com.valentin.shop.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductDao;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.models.CartProduct;
import com.valentin.shop.models.Status;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Status addProduct(ProductDto productDto, User activeUser) {
		ProductCategory category = this.getCategoryById(productDto.getCategory());
		Product product = this.modelMapper.map(productDto, Product.class);
		product.setUser(activeUser);
		product.setCategory(category);
		product.setIsActive((byte)1);
		product.setDateAdded(new Date());
	
		// Escaping inputs
		product.setName(HtmlUtils.htmlEscape(product.getName()));
		product.setPictureUrl(HtmlUtils.htmlEscape(product.getPictureUrl()));
		product.setDescription(HtmlUtils.htmlEscape(product.getDescription()));
		
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
		Product productToUpdate = this.productDao.getUserProduct(user, productDto.getId());
		if(productToUpdate == null) {
			Status status = new Status();
			status.setError("Invalid product");
			
			return status;
		}
		
		productToUpdate.setName(productDto.getName());
		productToUpdate.setPrice(productDto.getPrice());
		productToUpdate.setQuantity(productDto.getQuantity());
		productToUpdate.setName(HtmlUtils.htmlEscape(productToUpdate.getName()));
		
		return this.productDao.editProduct(productToUpdate);
	}

	@Override
	public Status deleteProduct(long productId, User user) {
		Product product = this.productDao.getUserProduct(user, productId);
		if(product == null) {
			Status status = new Status();
			status.setError("Invalid product");
			
			return status;
		}
		
		product.setIsActive((byte)0); // delete the product
		return this.productDao.deleteProduct(product);
	}

	@Override
	public List<ProductCategory> getAllCategories() {
		return this.productDao.getAllCategories();
	}

	@Override
	public ProductCategory getCategoryById(int catId) {
		return this.productDao.getCategoryById(catId);
	}

	@Override
	public List<Product> getAllProducts() {
		return this.productDao.getAllProducts();
	}

	@Override
	public List<Product> getProductsByCategoryId(int catId) {
		ProductCategory category = this.productDao.getCategoryById(catId);
		return this.productDao.getProductsByCategoryId(category);
	}

	@Override
	public Product getProductById(long productId) {
		return this.productDao.getProductById(productId);
	}

	@Override
	public List<Product> searchProducts(String title, double minPrice, double maxPrice, int quantity) {
		return this.productDao.searchProducts(title, minPrice, maxPrice, quantity);
	}

	@Override
	public Status addProductToCart(int productId, int quantity, List<CartProduct> cart) {
		Status status = new Status();
		Product product = this.productDao.getProductById(productId);
		if(product == null) {
			status.setError("Invalid product id");
			return status;
		}
		
		if(product.getQuantity() < quantity) {
			status.setError("Invalid quantity");
		}
		
		if(status.isSuccessful()) {
			CartProduct cartProduct = modelMapper.map(product, CartProduct.class);
			if(cart.contains(cartProduct)) {
				int productIndex = cart.indexOf(cartProduct);
				CartProduct currentProduct = cart.get(productIndex);
				int totalQuantity = currentProduct.getQuantity() + quantity;
				if(totalQuantity <= product.getQuantity()) {
					cart.remove(productIndex);
					currentProduct.setQuantity(totalQuantity);
					cart.add(currentProduct);
				} else {
					status.setError("Invalid quantity");
				}
			} else {
				cartProduct.setQuantity(quantity);
				cart.add(cartProduct);
			}
		}
		
		if(status.isSuccessful()) {
			status.setSuccessMessage("Product successfully added to the cart");
		}
		
		return status;
	}

	@Override
	public Status buyProducts(List<CartProduct> cart) {
		Status status = new Status();
		if(cart.size() == 0) {
			status.setError("Empty cart");
		}
		
		if(status.isSuccessful()) {
			List<Product> products = new ArrayList<>();
			for(CartProduct product : cart) {
				Product dbProduct = this.productDao.getProductById(product.getId());
				if(dbProduct != null) {
					int newQuantity = dbProduct.getQuantity() - product.getQuantity();
					dbProduct.setQuantity(newQuantity);
					products.add(dbProduct);
				}
			}
			if(products.size() > 0) {
				status = this.productDao.updateProducts(products);
				cart.clear();	
			}
		}
		
		return status;
	}
}
