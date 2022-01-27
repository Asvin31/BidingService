package com.fse.service;

import com.fse.exception.BiddingException;
import com.fse.exception.ProductException;
import com.fse.model.Bidding;

public interface BuyerService {
	Boolean createBidding(Bidding bidding) throws BiddingException, ProductException;

	Boolean updateBidding(String productId, String email, double price) throws BiddingException, ProductException;
}
