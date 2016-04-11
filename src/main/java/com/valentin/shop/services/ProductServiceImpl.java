package com.valentin.shop.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
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
	public Status addProduct(ProductDto productDto, User activeUser, ProductCategory category) {
		Product product = this.modelMapper.map(productDto, Product.class);
		product.setUser(activeUser);
		product.setCategory(category);
		product.setIsActive((byte)1);
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
		
		return this.productDao.editProduct(productToUpdate, user);
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
}
