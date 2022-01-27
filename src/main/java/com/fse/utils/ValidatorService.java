package com.fse.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.exception.ProductException;
import com.fse.model.Bidding;
import com.fse.model.Product;
import com.fse.repository.BiddingRepo;
import com.fse.repository.SellerRepo;

@Service
public class ValidatorService {

	@Autowired
	SellerRepo sellerRepo;

	@Autowired
	BiddingRepo biddingRepo;

	private final String regrexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
			+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

	private final String phoneRegrex = "(^$|[0-9]{10})";

	private final List<String> categoryList = Arrays.asList("Painting", "Sculptor", "Ornament");

	public boolean patternMatches(String emailAddress) {
		return Pattern.compile(regrexPattern).matcher(emailAddress).matches();
	}

	public boolean phoneMatches(String phoneNumber) {
		return phoneNumber.matches(phoneRegrex);
	}

	public boolean checkCategory(String category) {
		return categoryList.stream().anyMatch(category::equalsIgnoreCase);
	}

	public Product findProduct(String prodId, String operation) throws ProductException {
		Product existingProduct = null;
		try {
			existingProduct = sellerRepo.findProductByProductId(prodId);
			if (LocalDate.now().isAfter(existingProduct.getBidEndDate()) && !operation.equalsIgnoreCase("show")) {
				if (operation.equalsIgnoreCase("delete")) {
					throw new ProductException("Cannot delete product after bid end date");
				}
				throw new ProductException(operation.equalsIgnoreCase("bid") ? "Cannot Bid for ended products"
						: "Cannot update Bid amount after the bid end date");
			}
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		} catch (Exception e) {
			throw new ProductException("Product does not exist");
		}
		return existingProduct;
	}

	public List<Bidding> getBiddingsForProduct(String productId) {
		return biddingRepo.findBiddingsByProductId(productId);
	}

}
