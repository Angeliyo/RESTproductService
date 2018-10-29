package com.example.springboot.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.rest.model.Product;
import com.example.springboot.rest.service.ProductService;


@RestController
public class ProductServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceController.class);
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/products")
	public ResponseEntity<Object> getProduct(){
		LOGGER.info("getProduct()");
		return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		LOGGER.info("createProduct: " + product);
		productService.createProduct(product);
		return new ResponseEntity<Object>("Product is created successfully", HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
		LOGGER.info("updateProduct: " + product);
		
		productService.updateProduct(id, product);
		return new ResponseEntity<Object>("Product is updated successsfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
		LOGGER.info("delete: " + id);
		productService.deleteProduct(id);
		return new ResponseEntity<Object>("Product is deleted successsfully", HttpStatus.OK);
	}
}
