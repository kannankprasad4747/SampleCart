package com.example.demo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl<P, C> implements ProductService<P, C> {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	private static final String API_BASE_URL = "https://dummyjson.com/";

	private final RestTemplate restTemplate;

	public ProductServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<P> getAllProducts() {
		String url = API_BASE_URL + "products";
		logger.info("Getting all products from URL: {}", url);
		P[] products = restTemplate.getForObject(url, (Class<P[]>) Object[].class);
		return Arrays.asList(products);
	}

	@Override
	public List<P> getAllProducts(int limit, int skip, String... fields) {
		String url = API_BASE_URL + "products?limit=" + limit + "&skip=" + skip;
		if (fields.length > 0) {
			String selectedFields = String.join(",", fields);
			url += "&select=" + selectedFields;
		}
		logger.info("Getting all products with limit: {}, skip: {}, fields: {} from URL: {}", limit, skip,
				Arrays.toString(fields), url);
		P[] products = restTemplate.getForObject(url, (Class<P[]>) Object[].class);
		return Arrays.asList(products);
	}

	@Override
	public P getProduct(Integer productId) {
		String url = API_BASE_URL + "products/" + productId;
		logger.info("Getting product with ID: {} from URL: {}", productId, url);
		return restTemplate.getForObject(url, (Class<P>) Object.class);
	}

	// Implement other methods...

}
