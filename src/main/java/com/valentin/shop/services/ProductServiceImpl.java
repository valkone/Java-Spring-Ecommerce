package com.valentin.shop.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.valentin.shop.constants.MessageConstants;
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
		Status status = new Status();
		
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
		
		if(this.productDao.addProduct(product)) {
			String successfullMessage = String.format(MessageConstants.ADD_PRODUCT_SUCCESS,
					product.getName(), product.getPrice(), product.getQuantity());
			status.setSuccessMessage(successfullMessage);
		} else {
			status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
		}
		
		return status;
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
		Status status = new Status();
		Product productToUpdate = this.productDao.getUserProduct(user, productDto.getId());
		if(productToUpdate == null) {
			status.setError(MessageConstants.INVALID_PRODUCT);
		}
		
		if(status.isSuccessful()) {
			productToUpdate.setName(productDto.getName());
			productToUpdate.setPrice(productDto.getPrice());
			productToUpdate.setQuantity(productDto.getQuantity());
			productToUpdate.setName(HtmlUtils.htmlEscape(productToUpdate.getName()));
			
			if(this.productDao.editProduct(productToUpdate)) {
				status.setSuccessMessage(MessageConstants.EDIT_PRODUCT_SUCCESS);
			} else {
				status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
			}
		}
		
		return status;
	}

	@Override
	public Status deleteProduct(long productId, User user) {
		Status status = new Status();
		
		Product product = this.productDao.getUserProduct(user, productId);
		if(product == null) {
			status.setError(MessageConstants.INVALID_PRODUCT);
		}
		
		if(status.isSuccessful()) {
			product.setIsActive((byte)0); // delete the product
			if(this.productDao.deleteProduct(product)) {
				status.setSuccessMessage(MessageConstants.DELETE_PRODUCT_SUCCESS);
			} else {
				status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
			}
		}
		
		return status;
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
			status.setError(MessageConstants.INVALID_PRODUCT);
			return status;
		}
		
		if(product.getQuantity() < quantity) {
			status.setError(MessageConstants.INVALID_QUANTITY);
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
					status.setError(MessageConstants.INVALID_QUANTITY);
				}
			} else {
				cartProduct.setQuantity(quantity);
				cart.add(cartProduct);
			}
		}
		
		if(status.isSuccessful()) {
			status.setSuccessMessage(MessageConstants.PRODUCT_SUCCESS);
		}
		
		return status;
	}

	@Override
	public Status buyProducts(List<CartProduct> cart) {
		Status status = new Status();
		if(cart.size() == 0) {
			status.setError(MessageConstants.EMPTY_CART);
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
				if(this.productDao.updateProducts(products)) {
					status.setSuccessMessage(MessageConstants.BOUGHT_PRODUCT_SUCCESS);
					cart.clear();
				} else {
					status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
				}
			}
		}
		
		return status;
	}

	@Override
	public List<Product> getProductsByPage(int page) {
		return this.productDao.getProductsByPage(page);
	}
}
