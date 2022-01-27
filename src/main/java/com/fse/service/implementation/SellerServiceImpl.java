package com.fse.service.implementation;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.data.BiddingResponse;
import com.fse.data.ErrorData;
import com.fse.exception.ProductException;
import com.fse.mapper.ProductMapper;
import com.fse.model.Bidding;
import com.fse.model.Product;
import com.fse.repository.SellerRepo;
import com.fse.service.SellerService;
import com.fse.utils.SequenceGenerator;
import com.fse.utils.ValidatorService;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	SellerRepo sellerRepo;

	@Autowired
	SequenceGenerator sequenceGenerator;

	@Autowired
	ValidatorService validatorService;

	@Autowired
	ProductMapper productMapper;

	private Boolean validateProduct(Product product) throws ProductException {
		if (product.getProdName().isEmpty() || product.getProdName().length() < 5
				|| product.getProdName().length() > 30) {
			throw new ProductException("Enter a Valid Product Name");
		}
		if (product.getSeller().getFirstName().isEmpty() || product.getSeller().getFirstName().length() < 5
				|| product.getSeller().getFirstName().length() > 30) {
			throw new ProductException("Enter a Valid First Name");
		}
		if (product.getSeller().getLastName().isEmpty() || product.getSeller().getLastName().length() < 5
				|| product.getSeller().getLastName().length() > 30) {
			throw new ProductException("Enter a Valid Last Name");
		}
		if (!validatorService.patternMatches(product.getSeller().getEmail())) {
			throw new ProductException("Enter a Valid Email");
		}
		if (!validatorService.phoneMatches(product.getSeller().getPhoneNumber())) {
			throw new ProductException("Enter a Valid Phone number");
		}
		if (!validatorService.checkCategory(product.getCategory())) {
			throw new ProductException("Enter a Valid Product Category");
		}
		if (!product.getBidEndDate().isAfter(LocalDate.now())) {
			throw new ProductException("Bid Date should be in Future");
		}
		if ((product.getPrice() <= 0)) {
			throw new ProductException("Enter a Proper Price");
		}
		return true;
	}

	private Boolean checkBiddingForProdut(String productId) throws ProductException {
		if (validatorService.getBiddingsForProduct(productId).size() > 0) {
			throw new ProductException("Cannot delete product when bid is placed");
		} else {
			return true;
		}
	}

	@Override
	public Boolean addProduct(Product product) throws ProductException {
		if (Boolean.TRUE.equals(validateProduct(product))) {
			product.setProductId("PROD" + sequenceGenerator.getSequence(Product.SEQUENCE_NAME));
			sellerRepo.save(product);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteProdut(String productId) throws ProductException {
		if (!Objects.isNull(validatorService.findProduct(productId, "delete")) && checkBiddingForProdut(productId)) {
			sellerRepo.deleteByProductId(productId);
			return true;
		}
		return false;
	}

	@Override
	public BiddingResponse getBiddingsForProduct(String productId) throws ProductException {
		BiddingResponse biddingResponse = new BiddingResponse(null, null, null);
		List<Bidding> biddings = validatorService.getBiddingsForProduct(productId);
		if (!biddings.isEmpty()) {
			biddings.sort(Comparator.comparing(Bidding::getBidAmount).reversed());
			biddingResponse.setBiddings(biddings);
			biddingResponse.setProductData(
					productMapper.convertProductToProductData(validatorService.findProduct(productId, "show")));
		} else {
			ErrorData errorData = new ErrorData(500, "Biddings not available for the given product");
			biddingResponse.setErrorData(errorData);
		}
		return biddingResponse;
	}

}
