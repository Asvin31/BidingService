package com.fse.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fse.model.Product;

public interface SellerRepo extends MongoRepository<Product, String> {

	@DeleteQuery("{productId : ?0}")
	public Product deleteByProductId(String id);

	@Query("{productId : ?0}")
	public Product findProductByProductId(String id);

}
