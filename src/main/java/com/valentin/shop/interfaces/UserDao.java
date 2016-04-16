package com.valentin.shop.interfaces;

import java.util.Set;

import com.valentin.shop.entities.Role;
import com.valentin.shop.entities.User;

public interface UserDao {
	boolean register(User registrationModel, Set<Role> roles);
	User getUserByUsername(String username);
	boolean isUsernameExists(String username);
	boolean isEmailExists(String email);
	Role getDefaultUserRole();
}
