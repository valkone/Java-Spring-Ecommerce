package com.valentin.shop.interfaces;

import com.valentin.shop.dto.RegisterDto;
import com.valentin.shop.models.Status;

public interface UserService {
	Status register(RegisterDto registrationModel);
}
