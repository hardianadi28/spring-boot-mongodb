package id.hardian.practice.springbootmongodb.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.hardian.practice.springbootmongodb.data.entity.Product;
import id.hardian.practice.springbootmongodb.network.dto.product.ProductResponse;
import id.hardian.practice.springbootmongodb.network.dto.product.SaveProductRequest;
import id.hardian.practice.springbootmongodb.service.product.IProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private IProductService productService;

	@GetMapping("/{id}")
	ResponseEntity<ProductResponse> getProductById(@PathVariable String id) {
		Optional<Product> product = productService.getById(id);
		ProductResponse response = new ProductResponse();
		product.ifPresent(prod -> {
			mapToDto(response, prod);
		});

		return ResponseEntity.ok(response);
	}

	@PostMapping
	ResponseEntity<ProductResponse> saveProduct(@RequestBody SaveProductRequest req) {
		Product prod = new Product();
		prod.setProductCode(req.getProductCode());
		prod.setProductName(req.getProductName());
		prod.setProductPrice(req.getProductPrice());
		prod.setProductStock(req.getProductStock());
		Product savedProd = productService.save(prod);
		
		ProductResponse response = new ProductResponse();
		mapToDto(response, savedProd);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping
	ResponseEntity<List<ProductResponse>> getAllProducts() {
		List<Product> list = productService.getAll();
		List<ProductResponse> listResp = list.stream().map(prod -> {
			ProductResponse response = new ProductResponse();
			mapToDto(response, prod);
			return response;
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok(listResp);
	}
	
	private void mapToDto(ProductResponse response, Product entity) {
		response.setId(entity.getId());
		response.setProductCode(entity.getProductCode());
		response.setProductName(entity.getProductName());
		response.setProductPrice(entity.getProductPrice());
		response.setProductStock(entity.getProductStock());
	}

}
