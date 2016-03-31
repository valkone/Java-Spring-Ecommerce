package com.valentin.shop.interfaces;

import com.valentin.shop.models.Status;
import com.valetnin.shop.dto.RegisterDto;

public interface UserService {
	Status register(RegisterDto registrationModel);
}
