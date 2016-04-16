package com.valentin.shop.interfaces;

import com.valentin.shop.entities.User;

public interface UserDao {
	boolean register(User registrationModel);
	User getUserByUsername(String username);
	boolean isUsernameExists(String username);
	boolean isEmailExists(String email);
}
