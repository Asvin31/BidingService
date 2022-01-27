package com.fse.controller;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.data.ProductResponse;
import com.fse.exception.BiddingException;
import com.fse.exception.ProductException;
import com.fse.model.Bidding;
import com.fse.service.BuyerService;

@RestController
@RequestMapping("e-auction/api/v1/buyer")
public class BuyerController {

	@Autowired
	BuyerService buyerService;

	@PostMapping("/place-bid")
	public ResponseEntity<ProductResponse> placeBid(@RequestBody Bidding bidding) {
		ProductResponse productResponse = new ProductResponse("Not Able to proceed", 500);
		try {
			if (Boolean.TRUE.equals(buyerService.createBidding(bidding))) {
				productResponse.setMessage("Bid added for the given product");
				productResponse.setStatusCode(200);
			}
		} catch (ProductException | BiddingException e) {
			productResponse.setMessage(e.getMessage());
		} catch (Exception e) {
			productResponse.setMessage("Failed to add bid");
		}
		return ResponseEntity.status(productResponse.getStatusCode()).body(productResponse);
	}

	@PutMapping("/update-bid/{productId}/{email}/{newBidAmount}")
	public ResponseEntity<ProductResponse> updateBid(@PathVariable @NotEmpty String productId,
			@PathVariable @NotEmpty String email, @PathVariable @NotEmpty double newBidAmount) {
		ProductResponse productResponse = new ProductResponse("Not Able to proceed", 500);
		try {
			if (Boolean.TRUE.equals(buyerService.updateBidding(productId, email, newBidAmount))) {
				productResponse.setMessage("Bidding amount updated successfully");
				productResponse.setStatusCode(200);
			}
		} catch (ProductException | BiddingException e) {
			productResponse.setMessage(e.getMessage());
		} catch (Exception e) {
			productResponse.setMessage("Failed to add bid");
		}
		return ResponseEntity.status(productResponse.getStatusCode()).body(productResponse);
	}

}
