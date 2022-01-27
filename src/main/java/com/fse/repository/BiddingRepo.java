package com.fse.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fse.model.Bidding;

public interface BiddingRepo extends MongoRepository<Bidding, String> {

	@Query("{email :?0 , productId: ?1}")
	Bidding findBiddingByEmailAndProductId(String email, String productId);

	@Query("{productId : ?0}")
	List<Bidding> findBiddingsByProductId(String email);
}
