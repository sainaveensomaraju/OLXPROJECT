package com.zensar.olx.advertise.bean;

import java.time.LocalDate;

public class AdvertisementPostResponse {
	
	int id;
	String title;
	double price;
	String category;
	String description;
	String userName;
	LocalDate createDate;
	LocalDate modofiedDate;
	String status;
	public AdvertisementPostResponse(int id, String title, double price, String category, String description,
			String userName, LocalDate createDate, LocalDate modofiedDate, String status) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.category = category;
		this.description = description;
		this.userName = userName;
		this.createDate = createDate;
		this.modofiedDate = modofiedDate;
		this.status = status;
	}
	public AdvertisementPostResponse(String title, double price, String category, String description, String userName,
			LocalDate createDate, LocalDate modofiedDate, String status) {
		super();
		this.title = title;
		this.price = price;
		this.category = category;
		this.description = description;
		this.userName = userName;
		this.createDate = createDate;
		this.modofiedDate = modofiedDate;
		this.status = status;
	}
	public AdvertisementPostResponse(int id) {
		super();
		this.id = id;
		this.createDate=LocalDate.now();
		this.modofiedDate=LocalDate.now();
	}
	public AdvertisementPostResponse() {
		super();
		this.createDate=LocalDate.now();
		this.modofiedDate=LocalDate.now();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public LocalDate getModofiedDate() {
		return modofiedDate;
	}
	public void setModofiedDate(LocalDate modofiedDate) {
		this.modofiedDate = modofiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "AdvertisementPostResponse [id=" + id + ", title=" + title + ", price=" + price + ", category="
				+ category + ", description=" + description + ", userName=" + userName + ", createDate=" + createDate
				+ ", modofiedDate=" + modofiedDate + ", status=" + status + "]";
	}
	

}
