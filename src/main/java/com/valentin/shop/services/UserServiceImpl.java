package com.valentin.shop.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.valentin.shop.constants.MessageConstants;
import com.valentin.shop.dto.RegisterDto;
import com.valentin.shop.entities.User;
import com.valentin.shop.interfaces.UserDao;
import com.valentin.shop.interfaces.UserService;
import com.valentin.shop.models.Status;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Status register(RegisterDto model) {
		Status status = new Status();

		if (model.getUsername().length() < 5) {
			status.setError(MessageConstants.SHORT_USERNAME);
		}

		if (!model.getUsername().matches("[a-zA-Z0-9]+")) {
			status.setError(MessageConstants.INVALID_USERNAME_CHARACTERS);
		}

		if (model.getPassword().length() < 5) {
			status.setError(MessageConstants.SHORT_PASSWORD);
		}

		if (!model.getPassword().equals(model.getPassword2())) {
			status.setError(MessageConstants.PASSWORDS_MISMATCH);
		}

		// Simple email regex expression
		if (!model.getEmail().matches("[a-zA-Z0-9.-]+@([a-zA-Z0-9]+.)+[a-zA-Z]+")) {
			status.setError(MessageConstants.INVALID_EMAIL);
		}
		
		// BUG HERE
		if(this.userDao.isUsernameExists(model.getUsername())) {
			status.setError(MessageConstants.EXISTING_USERNAME);
		}
		
		if(this.userDao.isEmailExists(model.getEmail())) {
			status.setError(MessageConstants.EXISTING_EMAIL);
		}

		if (status.isSuccessful()) {
			User user = this.modelMapper.map(model, User.class);
			// TODO: make password with BCrypt
			/*
			 * BCryptPasswordEncoder passwordEncoder = new
			 * BCryptPasswordEncoder(); String hashedPassword =
			 * passwordEncoder.encode(user.getPassword());
			 */
			String hashedPassword = this.MD5(user.getPassword());
			user.setPassword(hashedPassword);

			return userDao.register(user);
		}

		return status;
	}

	private String MD5(String md5) {
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
