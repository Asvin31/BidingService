package com.fse.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fse.data.ProductData;
import com.fse.model.Product;

@Component
public class ProductMapper {

	public ProductData convertProductToProductData(Product product) {
		ProductData productData = new ProductData();
		productData.setShortDescription(product.getShortDescription());
		productData.setLongDescription(product.getLongDescription());
		productData.setCategory(product.getCategory());
		productData.setPrice(BigDecimal.valueOf(product.getPrice()).setScale(2).doubleValue());
		productData.setBidEndDate(product.getBidEndDate());
		return productData;
	}

}
