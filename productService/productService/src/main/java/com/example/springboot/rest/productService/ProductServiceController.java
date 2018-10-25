package com.example.springboot.rest.productService;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.rest.exception.ProductNotfoundException;


@RestController
public class ProductServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceController.class);
	
	private static Map<String, Product> productRepo = new HashMap<String, Product>();
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
	
	@RequestMapping(value="/products")
	public ResponseEntity<Object> getProduct(){
		LOGGER.info("getProduct()");
		return new ResponseEntity<Object>(productRepo.values(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		LOGGER.info("createProduct: " + product);
		productRepo.put(product.getId(), product);
		return new ResponseEntity<Object>("Product is created successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
		LOGGER.info("updateProduct: " + product);
		if(!productRepo.containsKey(id)) {
			LOGGER.info("Error: product Id not found (" + id + ")");
			throw new ProductNotfoundException();
		}
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<Object>("Product is updated successsfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
		LOGGER.info("delete: " + id);
		productRepo.remove(id);
		return new ResponseEntity<Object>("Product is deleted successsfully", HttpStatus.OK);
	}
}
