package com.fse.data;

public class ProductResponse {
	private String message;
	private int statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public ProductResponse(String message, int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}

	public ProductResponse() {
	}

}
