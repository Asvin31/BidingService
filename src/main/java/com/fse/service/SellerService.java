package com.fse.service;

import com.fse.data.BiddingResponse;
import com.fse.exception.ProductException;
import com.fse.model.Product;

public interface SellerService {
	Boolean addProduct(Product product) throws ProductException;

	Boolean deleteProdut(String productId) throws ProductException;

	BiddingResponse getBiddingsForProduct(String productId) throws ProductException;
}
