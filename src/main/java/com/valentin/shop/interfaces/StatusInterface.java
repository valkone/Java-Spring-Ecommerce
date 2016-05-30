package com.valentin.shop.interfaces;

import java.util.ArrayList;

public interface StatusInterface {
	boolean isSuccessful();
	void setError(String error);
	ArrayList<String> getErrors();
	String getSuccessMessage();
	void setSuccessMessage(String successMessage);
}
