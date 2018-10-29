package com.example.springboot.rest.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.springboot.rest.exception.ProductNotfoundException;
import com.example.springboot.rest.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	private static Map<String, Product> productRepo = new HashMap<>();
	static {
		Product honey = new Product();
		honey.setId("1");
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);

		Product almond = new Product();
		almond.setId("2");
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
	}
	
	@Override
	public void createProduct(Product product) {
		productRepo.put(product.getId(), product);
	}

	@Override
	public void updateProduct(String id, Product product) {
		if(!productRepo.containsKey(id)) {
			LOGGER.info("Error: product Id not found (" + id + ")");
			throw new ProductNotfoundException();
		}
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
	}

	@Override
	public void deleteProduct(String id) {
		productRepo.remove(id);
	}

	@Override
	public Collection<Product> getProducts() {
		return productRepo.values();
	}

}
