package com.valentin.shop.models;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Status {
	private ArrayList<String> errors;
	private String successMessage;
	
	{
		this.errors = new ArrayList<>();
	}
	
	public boolean isSuccessful() {
		if(errors.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	public void setError(String error) {
		this.errors.add(error);
		this.successMessage = "";
	}
	
	public ArrayList<String> getErrors() {
		return this.errors;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
}
