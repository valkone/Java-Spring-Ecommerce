package com.valentin.shop.interfaces;

import com.valentin.shop.entities.User;
import com.valentin.shop.models.Status;

public interface UserDao {
	Status register(User registrationModel);
	User getUserByUsername(String username);
}
