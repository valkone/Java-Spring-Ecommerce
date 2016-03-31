package com.valentin.shop.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.valentin.shop.entities.Product;
import com.valentin.shop.interfaces.ProductDao;
import com.valentin.shop.models.Status;

@Repository
public class ProductDaoImpl implements ProductDao{

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
		
		status.setSuccessMessage("Product successfuly added.");
		return status;
	}
}
