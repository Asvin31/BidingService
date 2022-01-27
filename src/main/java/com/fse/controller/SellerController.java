package com.fse.controller;

import java.util.Objects;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fse.data.BiddingResponse;
import com.fse.data.ErrorData;
import com.fse.data.ProductResponse;
import com.fse.exception.ProductException;
import com.fse.model.Product;
import com.fse.service.SellerService;

@RestController
@RequestMapping("api/v1/seller")
public class SellerController {

	@Autowired
	SellerService sellerService;

	@PostMapping("/add-product")
	public ResponseEntity<ProductResponse> addProduct(@RequestBody @Validated Product product) {
		ProductResponse productResponse = new ProductResponse("Failed to add product", 500);
		try {
			if (Boolean.TRUE.equals(sellerService.addProduct(product))) {
				productResponse.setMessage("Product added");
				productResponse.setStatusCode(200);
			}
		} catch (ProductException e) {
			productResponse.setMessage(e.getMessage());
		} catch (Exception e) {
			productResponse.setMessage("Failed to add product");
		}
		return ResponseEntity.status(productResponse.getStatusCode()).body(productResponse);
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<ProductResponse> deleteProduct(@PathVariable @NotEmpty String productId) {
		ProductResponse productResponse = new ProductResponse("Failed to perform the operation", 500);
		try {
			if (Boolean.TRUE.equals(sellerService.deleteProdut(productId))) {
				productResponse.setMessage("Product deleted");
				productResponse.setStatusCode(200);
			}
		} catch (ProductException e) {
			productResponse.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			productResponse.setMessage("Failed to perform the operation");
		}
		return ResponseEntity.status(productResponse.getStatusCode()).body(productResponse);
	}

	@GetMapping("/show-bids/{productId}")
	public ResponseEntity<BiddingResponse> getBiddingsForProduct(@PathVariable @NotEmpty String productId) {
		int statusCode = 200;
		BiddingResponse biddingResponse = new BiddingResponse(null, null, null);
		try {
			biddingResponse = sellerService.getBiddingsForProduct(productId);
			if (!Objects.isNull(biddingResponse.getErrorData())) {
				statusCode = biddingResponse.getErrorData().getCode();
			}
		} catch (ProductException e) {
			biddingResponse.setErrorData(new ErrorData(500, e.getMessage()));
		}
		return ResponseEntity.status(statusCode).body(biddingResponse);
	}
}
