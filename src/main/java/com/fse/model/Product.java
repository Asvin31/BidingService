package com.fse.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
public class Product {
	@Transient
	public static final String SEQUENCE_NAME = "prod_seq";
	@Transient
	static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
	@Id
	private String id;
	private String productId;
	@NotNull
	@NotEmpty
	@Size(min = 5, max = 30)
	private String prodName;
	private String shortDescription;
	private String longDescription;
	private String category;
	private double price;
	private LocalDate bidEndDate;
	private Seller seller;

	public Product(String productId, @NotNull @NotEmpty @Size(min = 5, max = 30) String prodName,
			String shortDescription, String longDescription, String category, double price, String bidEndDate,
			Seller seller) {
		this.productId = productId;
		this.prodName = prodName;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.category = category;
		this.price = price;
		this.bidEndDate = LocalDate.parse(bidEndDate, dateTimeFormatter);
		this.seller = seller;
	}

	public Product() {
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

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

	public void setBidEndDate(String bidEndDate) {
		this.bidEndDate = LocalDate.parse(bidEndDate, dateTimeFormatter);
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

}
