package com.valentin.shop.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.valentin.shop.constants.GeneralConstants;
import com.valentin.shop.constants.MessageConstants;
import com.valentin.shop.dto.RegisterDto;
import com.valentin.shop.entities.Role;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.StatusInterface;
import com.valentin.shop.interfaces.UserDao;
import com.valentin.shop.interfaces.UserService;
import com.valentin.shop.models.Status;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;
	
	private ApplicationContext context = new ClassPathXmlApplicationContext("/beans.xml");
	private Status status;

	@Override
	public Status register(RegisterDto model) {
		this.status = (Status) this.context.getBean(GeneralConstants.STATUS_BEAN_NAME);
		
		if (model.getUsername().length() < 5) {
			this.status.setError(MessageConstants.SHORT_USERNAME);
		}

		if (!model.getUsername().matches("[a-zA-Z0-9]+")) {
			this.status.setError(MessageConstants.INVALID_USERNAME_CHARACTERS);
		}

		if (model.getPassword().length() < 5) {
			this.status.setError(MessageConstants.SHORT_PASSWORD);
		}

		if (!model.getPassword().equals(model.getPassword2())) {
			this.status.setError(MessageConstants.PASSWORDS_MISMATCH);
		}

		// Simple email regex expression
		if (!model.getEmail().matches("[a-zA-Z0-9.-]+@([a-zA-Z0-9]+.)+[a-zA-Z]+")) {
			this.status.setError(MessageConstants.INVALID_EMAIL);
		}
		
		// BUG HERE
		if(this.userDao.isUsernameExists(model.getUsername())) {
			this.status.setError(MessageConstants.EXISTING_USERNAME);
		}
		
		if(this.userDao.isEmailExists(model.getEmail())) {
			this.status.setError(MessageConstants.EXISTING_EMAIL);
		}

		if (this.status.isSuccessful()) {
			User user = this.modelMapper.map(model, User.class);
			// TODO: make password with BCrypt
			/*
			 * BCryptPasswordEncoder passwordEncoder = new
			 * BCryptPasswordEncoder(); String hashedPassword =
			 * passwordEncoder.encode(user.getPassword());
			 */
			String hashedPassword = this.Md5Decode(user.getPassword());
			user.setPassword(hashedPassword);
			
			Set<Role> roles = new HashSet<>();
			Role defaultRole = this.userDao.getDefaultUserRole();
			roles.add(defaultRole);

			if(userDao.register(user, roles)) {
				this.status.setSuccessMessage(MessageConstants.SUCCESSFUL_REGISTRATION);
			} else {
				this.status.setError(MessageConstants.DATABASE_ERROR_MESSAGE);
			}
		}

		return this.status;
	}

	private String Md5Decode(String md5) {
		try {
			MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
