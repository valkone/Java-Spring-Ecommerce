package com.valentin.shop.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.valentin.shop.entities.Role;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.UserDao;
import com.valentin.shop.models.Status;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public Status register(User user) {
		Status status = new Status();
		
		// TODO: if the insert has errors, put them into status object
		
		User existUsername = this.getUserByUsername(user.getUsername());
		if(existUsername != null) {
			status.setError("Username already exists.");
		}
		
		Criteria existEmailCriteria = sessionFactory.openSession().createCriteria(User.class);
		existEmailCriteria.add(Restrictions.eq("email", user.getEmail()));
		if(existEmailCriteria.list().size() > 0) {
			status.setError("Email already exists.");
		}
		
		if(status.isSuccess()) {
			Role defaultRole = (Role) sessionFactory
					.openSession()
					.createCriteria(Role.class)
					.add(Restrictions.eq("role", "ROLE_USER"))
					.list()
					.get(0);
			
			Set<Role> roles = new HashSet<>();
			roles.add(defaultRole);
			
			Session session = sessionFactory.openSession();
				// Save the user
				Transaction tx = session.beginTransaction();
					session.save(user);
				tx.commit();
				
				// TODO: find out why before updating it executes DELETE query
				// Add the role into the user and update it
				tx = session.beginTransaction();
					User dbUser = this.getUserByUsername(user.getUsername());
					dbUser.setRoles(roles);
					session.update(dbUser);
				tx.commit();
			session.close();

			status.setSuccessMessage("Successful Registration");
		}
		
		return status;
	}

	@Override
	public User getUserByUsername(String username) {
		Criteria criteria = sessionFactory.openSession().createCriteria(User.class);
		List<User> users = criteria.add(Restrictions.eq("username", username)).list();
		
		if(users.size() == 0) {
			return null;
		}
		
		User user = users.get(0);
		
		return user;
	}
}
