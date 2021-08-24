package id.hardian.practice.springbootmongodb.service.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import id.hardian.practice.springbootmongodb.data.entity.Product;
import id.hardian.practice.springbootmongodb.data.repository.ProductRepository;
import lombok.val;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	@DisplayName("Should return Product if product ID is matched")
	void getProductById() {

		val product = new Product("1", "P0001", "Instant Noodle", 2000.0, 100);
		when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.of(product));

		Optional<Product> result = productService.getById("1");
		assertThat(result).isNotEmpty();
		verify(productRepository, times(1)).findById("1");
	}

	@Test
	@DisplayName("Should return same Product after it's saved")
	void saveProduct() {

		val product = new Product("1", "P0001", "Instant Noodle", 2000.0, 100);
		when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

		val productSaved = productService.save(product);
		verify(productRepository, times(1)).save(product);
		assertThat(productSaved.getId()).isEqualTo(product.getId());

	}

}
