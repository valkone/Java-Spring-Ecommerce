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
import com.valentin.shop.constants.MessageConstants;
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
		
		String successfullMessage = String.format(MessageConstants.ADD_PRODUCT_SUCCESS,
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
	public Status editProduct(Product product) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(product);
		tx.commit();
		session.close();
		
		Status status = new Status();
		status.setSuccessMessage(MessageConstants.EDIT_PRODUCT_SUCCESS);
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
		status.setSuccessMessage(MessageConstants.DELETE_PRODUCT_SUCCESS);
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
		
		ProductCategory category =  (ProductCategory) criteria.list().get(0);
		session.close();
		
		return category;
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("isActive", (byte)1));
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
	public Status updateProducts(List<Product> products) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for(Product product : products) {
			session.update(product);
		}
		tx.commit();
		session.close();
		
		Status status = new Status();
		status.setSuccessMessage(MessageConstants.BOUGHT_PRODUCT_SUCCESS);
		return status;
	}
}
