package com.zensar.olx.advertise.bean;

public class AdvertisementPostRequest {
int id;
String title;
int categoryId;
String description;
double price;
int statusId;
public AdvertisementPostRequest(int id, String title, int category, String description, double price, int statusId) {
	super();
	this.id = id;
	this.title = title;
	this.categoryId = category;
	this.description = description;
	this.price = price;
	this.statusId = statusId;
}
public AdvertisementPostRequest(String title, int category, String description, double price, int statusId) {
	super();
	this.title = title;
	this.categoryId = category;
	this.description = description;
	this.price = price;
	this.statusId = statusId;
}
public AdvertisementPostRequest(int id) {
	super();
	this.id = id;
}
public AdvertisementPostRequest() {
	super();
}
@Override
public String toString() {
	return "AdvertisementPostRequest [id=" + id + ", title=" + title + ", category=" + categoryId + ", description="
			+ description + ", price=" + price + ", statusId=" + statusId + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	AdvertisementPostRequest other = (AdvertisementPostRequest) obj;
	if (id != other.id)
		return false;
	return true;
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
public int getCategoryId() {
	return categoryId;
}
public void setCategoryId(int category) {
	this.categoryId = category;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public int getStatusId() {
	return statusId;
}
public void setStatusId(int statusId) {
	this.statusId = statusId;
}

	
}
