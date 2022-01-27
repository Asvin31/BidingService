package com.fse.data;

import java.time.LocalDate;

public class ProductData {
	private String shortDescription;
	private String longDescription;
	private String category;
	private double price;
	private LocalDate bidEndDate;

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(LocalDate bidEndDate) {
		this.bidEndDate = bidEndDate;
	}

	public ProductData(String shortDescription, String longDescription, String category, double price,
			LocalDate bidEndDate) {
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.category = category;
		this.price = price;
		this.bidEndDate = bidEndDate;
	}

	public ProductData() {
	}

}
