package com.valentin.shop.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.valentin.shop.entities.Product;
import com.valentin.shop.entities.ProductCategory;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.ProductDao;


@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addProduct(Product product) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(product);
			tx.commit();
			session.close();
		} catch(Exception e) {
			// TODO: log the error
			return false;
		}
		
		return true;
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
		
		Product product = criteria.list().size() > 0 ? (Product)criteria.list().get(0) : null;
		session.close();
		
		return product;
	}

	@Override
	public boolean editProduct(Product product) {
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(product);
			tx.commit();
			session.close();
		} catch (Exception e) {
			// TODO: log the error
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteProduct(Product product) {
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.update(product);
			tx.commit();
			session.close();
		} catch(Exception e) {
			// TODO: log the error
			return false;
		}
		
		return true;
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
		
		ProductCategory category =  (ProductCategory) criteria.list().get(0);
		session.close();
		
		return category;
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isActive", (byte)1));
		criteria.add(Restrictions.ge("quantity", 1));
		criteria.addOrder(Order.desc("dateAdded"));
		
		ArrayList<Product> products = new ArrayList<>();
		
		for(Object product : criteria.list()) {
			products.add((Product)product);
		}
		session.close();
		
		return products;
	}

	@Override
	public List<Product> getProductsByCategoryId(ProductCategory category) {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("category", category));
		criteria.add(Restrictions.ge("quantity", 1));
		criteria.add(Restrictions.eq("isActive", (byte)1));
		
		ArrayList<Product> products = new ArrayList<>();
		
		for(Object product : criteria.list()) {
			products.add((Product)product);
		}
		session.close();
		
		return products;
	}

	@Override
	public Product getProductById(long productId) {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("id", productId));
		criteria.add(Restrictions.ge("quantity", 1));
		criteria.add(Restrictions.eq("isActive", (byte)1));
		
		Product product;
		try {
			product = (Product) criteria.list().get(0);
		} catch(RuntimeException e) {
			product = null;
		}
		
		session.close();
		
		return product;
	}

	@Override
	public List<Product> searchProducts(String title, double minPrice, double maxPrice, int quantity) {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.ilike("name", title, MatchMode.ANYWHERE));
		criteria.add(Restrictions.ge("price", minPrice));
		if(maxPrice > 0) {
			criteria.add(Restrictions.le("price", maxPrice));
		}
		if(quantity > 0) {
			criteria.add(Restrictions.ge("quantity", quantity));
		}
		
		ArrayList<Product> products = new ArrayList<>();
		
		for(Object product : criteria.list()) {
			products.add((Product)product);
		}
		session.close();
		
		return products;
	}

	@Override
	public boolean updateProducts(List<Product> products) {
		try {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			for(Product product : products) {
				session.update(product);
			}
			tx.commit();
			session.close();
		} catch(Exception e) {
			// TODO: log the error
			return false;
		}
		
		return true;
	}
}
