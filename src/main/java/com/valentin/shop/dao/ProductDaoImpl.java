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

import com.valentin.shop.entities.Product;
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
		Criteria criteria = this.sessionFactory.openSession().createCriteria(User.class);
		Criteria c = criteria.add(Restrictions.eq("id", userId));

		if(c.list().size() == 0) {
			return false;
		}
		
		return true;
	}

	@Override
	public List<Product> getUserProducts(User user) {
		Criteria criteria = this
				.sessionFactory
				.openSession()
				.createCriteria(Product.class)
				.add(Restrictions.eq("user", user));
		
		ArrayList<Product> products = new ArrayList<>();
		for(Object product : criteria.list()) {
			products.add((Product)product);
		}
		
		return products;
	}
}
