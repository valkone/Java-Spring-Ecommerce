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
			
			// Add the role into the user and updates it
			tx = session.beginTransaction();
				User dbUser = this.getUserByUsername(user.getUsername());
				dbUser.setRoles(roles);
				session.update(dbUser);
			tx.commit();
		session.close();

		status.setSuccessMessage("Successful Registration");
		
		return status;
	}

	@Override
	public User getUserByUsername(String username) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		List<User> users = criteria.add(Restrictions.eq("username", username)).list();
		
		if(users.size() == 0) {
			return null;
		}
		
		User user = users.get(0);
		session.close();
		
		return user;
	}

	@Override
	public boolean isUsernameExists(String username) {
		if(this.getUserByUsername(username) == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean isEmailExists(String email) {
		Session session = this.sessionFactory.openSession();
		Criteria existEmailCriteria = session.createCriteria(User.class);
		existEmailCriteria.add(Restrictions.eq("email", email));
		boolean isUserExists = existEmailCriteria.list().size() > 0;
		session.close();
		
		if(isUserExists) {
			return true;
		}
		
		return false;
	}
}
