package com.valentin.shop.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable{

	@Id
	@Column(name = "ID")
	private long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "QUANTITY")
	private int quantity;
	
	@Column(name = "PRICE")
	private double price;
	
	@Column(name = "IS_ACTIVE")
	private byte isActive;
	
	@Column(name = "PICTURE_URL")
	private String pictureUrl;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "DATE_ADDED")
	private Date dateAdded;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProductCategory category;
	
	private static final long serialVersionUID = 2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
}
