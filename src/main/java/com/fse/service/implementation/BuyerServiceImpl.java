package com.fse.service.implementation;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.exception.BiddingException;
import com.fse.exception.ProductException;
import com.fse.model.Bidding;
import com.fse.repository.BiddingRepo;
import com.fse.service.BuyerService;
import com.fse.utils.ValidatorService;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	ValidatorService validatorService;

	@Autowired
	BiddingRepo biddingRepo;

	private Boolean validateBidding(Bidding bidding) throws BiddingException {
		if (bidding.getFirstName().isEmpty() || bidding.getFirstName().length() < 5
				|| bidding.getFirstName().length() > 30) {
			throw new BiddingException("Enter a Valid First Name");
		}
		if (bidding.getLastName().isEmpty() || bidding.getLastName().length() < 5
				|| bidding.getLastName().length() > 30) {
			throw new BiddingException("Enter a Valid Last Name");
		}
		if (!validatorService.patternMatches(bidding.getEmail())) {
			throw new BiddingException("Enter a Valid Email");
		}
		if (!validatorService.phoneMatches(bidding.getPhoneNumber())) {
			throw new BiddingException("Enter a Valid Phone number");
		}
		return true;
	}

	private Boolean checkIfBiddingExist(String email, String prodId) throws BiddingException {
		Bidding bidding = null;
		try {
			bidding = getBidding(email, prodId);
			if (!Objects.isNull(bidding)) {
				return true;
			}
		} catch (Exception e) {
			throw new BiddingException("Bidding already exists for product");
		}
		return false;
	}

	private Bidding getBidding(String email, String productId) {
		return biddingRepo.findBiddingByEmailAndProductId(email, productId);
	}

	@Override
	public Boolean createBidding(Bidding bidding) throws BiddingException, ProductException {
		if (Boolean.TRUE.equals(validateBidding(bidding))
				&& (!(Objects.isNull(validatorService.findProduct(bidding.getProductId(), "bid"))))) {
			if (Boolean.FALSE.equals(checkIfBiddingExist(bidding.getEmail(), bidding.getProductId()))) {
				biddingRepo.save(bidding);
				return true;
			} else {
				throw new BiddingException("Bidding already exists for product");
			}
		}
		return false;
	}

	@Override
	public Boolean updateBidding(String productId, String email, double price)
			throws BiddingException, ProductException {
		Bidding bidding = null;
		try {
			bidding = getBidding(email, productId);
			if (Objects.isNull(bidding)) {
				throw new BiddingException("No Biddings available for given email and product");
			} else {
				if (!(Objects.isNull(validatorService.findProduct(bidding.getProductId(), "update")))) {
					bidding.setBidAmount(BigDecimal.valueOf(price).setScale(2).doubleValue());
					biddingRepo.save(bidding);
					return true;
				}
				return false;
			}
		} catch (ProductException e) {
			throw new ProductException(e.getMessage());
		} catch (Exception e) {
			throw new BiddingException("No Biddings available for given email and product");
		}
	}

}
