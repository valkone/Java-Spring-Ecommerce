package com.valentin.shop.constants;

public class MessageConstants {

	public final static String DATABASE_ERROR_MESSAGE = "Something went wrong. Send this message to administrator.";
	
	// Product Messages
	// Error Messages
	public final static String INVALID_PRODUCT = "Invalid product";
	public final static String INVALID_QUANTITY = "Invalid quantity";
	public final static String PRODUCT_SUCCESS = "Product successfully added to the cart";
	public final static String EMPTY_CART = "Empty cart";
	
	//Success Messages
	public final static String ADD_PRODUCT_SUCCESS = "%s with $%s price and %s quantity successfuly added";
	public final static String EDIT_PRODUCT_SUCCESS = "You successfuly edited your product";
	public final static String DELETE_PRODUCT_SUCCESS = "You successfuly deleted your product";
	public final static String BOUGHT_PRODUCT_SUCCESS = "You successfully bought the products";
	
	// User Messages
	// Error Messages
	public final static String SHORT_USERNAME = "Username is too short";
	public final static String INVALID_USERNAME_CHARACTERS = "Special characters are not allowed in username";
	public final static String SHORT_PASSWORD = "Password is too short";
	public final static String PASSWORDS_MISMATCH = "Passwords didn't match";
	public final static String INVALID_EMAIL = "Invalid email";
	public final static String EXISTING_USERNAME = "Username already exists";
	public final static String EXISTING_EMAIL = "Email already exists";
	
	// Success Messages
	public final static String SUCCESSFUL_REGISTRATION = "Successful Registration";
}
