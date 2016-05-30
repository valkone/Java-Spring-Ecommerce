package com.valentin.shop.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.valentin.shop.constants.GeneralConstants;
import com.valentin.shop.constants.MessageConstants;
import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductDao;
import com.valentin.shop.interfaces.ProductService;
import com.valentin.shop.interfaces.StatusInterface;
import com.valentin.shop.models.CartProduct;
import com.valentin.shop.models.Status;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ModelMapper modelMapper;
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");
	private Status status;
	
	@Override
	public Status addProduct(ProductDto productDto, User activeUser) {
		this.status = (Status) this.context.getBean(GeneralConstants.STATUS_BEAN_NAME);
		
		if(productDto.getPrice() <= 0) {
			this.status.setError("Invalid price");
		}
		
		if(productDto.getQuantity() <= 0) {
			this.status.setError("Invalid quantity");
		}
		
		if(this.status.isSuccessful()) {
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
				this.status.setSuccessMessage(successfullMessage);
			} else {
				this.status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
			}
		}
		
		return this.status;
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
		this.status = (Status) this.context.getBean(GeneralConstants.STATUS_BEAN_NAME);
		
		if(productDto.getPrice() <= 0) {
			this.status.setError("Invalid price");
		}
		
		if(productDto.getQuantity() <= 0) {
			this.status.setError("Invalid quantity");
		}
		
		Product productToUpdate = this.productDao.getUserProduct(user, productDto.getId());
		if(productToUpdate == null) {
			this.status.setError(MessageConstants.INVALID_PRODUCT);
		}
		
		if(this.status.isSuccessful()) {
			productToUpdate.setName(productDto.getName());
			productToUpdate.setPrice(productDto.getPrice());
			productToUpdate.setQuantity(productDto.getQuantity());
			productToUpdate.setName(HtmlUtils.htmlEscape(productToUpdate.getName()));
			
			if(this.productDao.editProduct(productToUpdate)) {
				this.status.setSuccessMessage(MessageConstants.EDIT_PRODUCT_SUCCESS);
			} else {
				this.status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
			}
		}
		
		return this.status;
	}

	@Override
	public Status deleteProduct(long productId, User user) {
		this.status = (Status) this.context.getBean(GeneralConstants.STATUS_BEAN_NAME);
		
		Product product = this.productDao.getUserProduct(user, productId);
		if(product == null) {
			this.status.setError(MessageConstants.INVALID_PRODUCT);
		}
		
		if(this.status.isSuccessful()) {
			product.setIsActive((byte)0); // delete the product
			if(this.productDao.deleteProduct(product)) {
				this.status.setSuccessMessage(MessageConstants.DELETE_PRODUCT_SUCCESS);
			} else {
				this.status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
			}
		}
		
		return this.status;
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
		this.status = (Status) this.context.getBean(GeneralConstants.STATUS_BEAN_NAME);
		
		Product product = this.productDao.getProductById(productId);
		if(product == null) {
			this.status.setError(MessageConstants.INVALID_PRODUCT);
			return this.status;
		}
		
		if(product.getQuantity() < quantity) {
			this.status.setError(MessageConstants.INVALID_QUANTITY);
		}
		
		if(this.status.isSuccessful()) {
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
					this.status.setError(MessageConstants.INVALID_QUANTITY);
				}
			} else {
				cartProduct.setQuantity(quantity);
				cart.add(cartProduct);
			}
		}
		
		if(this.status.isSuccessful()) {
			this.status.setSuccessMessage(MessageConstants.PRODUCT_SUCCESS);
		}
		
		return this.status;
	}

	@Override
	public Status buyProducts(List<CartProduct> cart) {
		this.status = (Status) this.context.getBean(GeneralConstants.STATUS_BEAN_NAME);
		if(cart.size() == 0) {
			this.status.setError(MessageConstants.EMPTY_CART);
		}
		
		if(this.status.isSuccessful()) {
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
					this.status.setSuccessMessage(MessageConstants.BOUGHT_PRODUCT_SUCCESS);
					cart.clear();
				} else {
					this.status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
				}
			}
		}
		
		return this.status;
	}

	@Override
	public List<Product> getProductsByPage(int page) {
		return this.productDao.getProductsByPage(page);
	}
}
