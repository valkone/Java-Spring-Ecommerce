package com.valentin.shop.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.valentin.shop.dto.ProductDto;
import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductDao;
import com.valentin.shop.models.Status;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Status addProduct(Product product) {
		Status status = new Status();

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(product);
		tx.commit();
		session.close();
		
		String successfullMessage = String.format("%s with $%s price and %s quantity successfuly added",
				product.getName(), product.getPrice(), product.getQuantity());
		status.setSuccessMessage(successfullMessage);
		return status;
	}

	@Override
	public boolean isUserExists(long userId) {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		Criteria c = criteria.add(Restrictions.eq("id", userId));

		if(c.list().size() == 0) {
			return false;
		}
		session.close();
		
		return true;
	}

	@Override
	public List<Product> getUserProducts(User user) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Product.class)
				.add(Restrictions.eq("user", user))
				.add(Restrictions.eq("isActive", (byte)1));
		
		ArrayList<Product> products = new ArrayList<>();
		for(Object product : criteria.list()) {
			products.add((Product)product);
		}
		
		session.close();
		
		return products;
	}

	@Override
	public Product getUserProduct(User user, long productId) {
		Session session  = this.sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(Product.class)
				.add(Restrictions.eq("user", user))
				.add(Restrictions.eq("id", productId));
		
		List<Object> products = criteria.list();
		session.close();
		
		return products.size() == 0 ? null : (Product) products.get(0);
	}

	@Override
	public Status editProduct(Product product, User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(product);
		tx.commit();
		session.close();
		
		Status status = new Status();
		status.setSuccessMessage("You successfuly edited your product");
		return status;
	}

	@Override
	public Status deleteProduct(Product product) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(product);
		tx.commit();
		session.close();
		
		Status status = new Status();
		status.setSuccessMessage("You successfuly deleted your product");
		return status;
	}

	@Override
	public List<ProductCategory> getAllCategories() {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ProductCategory.class);
		
		ArrayList<ProductCategory> products = new ArrayList<>();
		
		for(Object p : criteria.list()) {
			products.add((ProductCategory)p);
		}

		session.close();
		
		return products;
	}

	@Override
	public ProductCategory getCategoryById(int catId) {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ProductCategory.class);
		criteria.add(Restrictions.eq("id", catId));
		session.close();
		
		return (ProductCategory) criteria.list().get(0);
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		
		ArrayList<Product> products = new ArrayList<>();
		
		for(Object product : criteria.list()) {
			products.add((Product)product);
		}
		session.close();
		
		return products;
	}
}
