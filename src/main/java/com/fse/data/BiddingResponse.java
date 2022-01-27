package com.fse.data;

import java.util.List;

import com.fse.model.Bidding;

public class BiddingResponse {
	private ProductData productData;
	private List<Bidding> biddings;
	private ErrorData errorData;

	public ProductData getProductData() {
		return productData;
	}

	public void setProductData(ProductData productData) {
		this.productData = productData;
	}

	public List<Bidding> getBiddings() {
		return biddings;
	}

	public void setBiddings(List<Bidding> biddings) {
		this.biddings = biddings;
	}

	public ErrorData getErrorData() {
		return errorData;
	}

	public void setErrorData(ErrorData errorData) {
		this.errorData = errorData;
	}

	public BiddingResponse(ProductData productData, List<Bidding> biddings, ErrorData errorData) {
		this.productData = productData;
		this.biddings = biddings;
		this.errorData = errorData;
	}

}
