package id.hardian.practice.springbootmongodb.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.hardian.practice.springbootmongodb.data.entity.Product;
import id.hardian.practice.springbootmongodb.data.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product entity) {
		return productRepository.save(entity);
	}

	@Override
	public Optional<Product> getById(String id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}


}
