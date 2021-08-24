package id.hardian.practice.springbootmongodb.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.hardian.practice.springbootmongodb.data.entity.Product;
import id.hardian.practice.springbootmongodb.network.dto.product.ProductResponse;
import id.hardian.practice.springbootmongodb.network.dto.product.SaveProductRequest;
import id.hardian.practice.springbootmongodb.service.product.ProductService;

@WebMvcTest(value = ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Autowired
	WebApplicationContext webApplicationContext;

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	@DisplayName("Should return list of products")
	public void getProductsList() throws Exception {
		List<ProductResponse> mockResponse = Arrays.asList(
				new ProductResponse("61247a9676dd0c5688a1a259", "P0001", "Product 001", 25000.0, 200),
				new ProductResponse("61247aa976dd0c5688a1a25a", "P0002", "Product 002", 15000.0, 200));

		List<Product> mockEntity = Arrays.asList(
				new Product("61247a9676dd0c5688a1a259", "P0001", "Product 001", 25000.0, 200),
				new Product("61247aa976dd0c5688a1a25a", "P0002", "Product 002", 15000.0, 200));

		String uri = "/products";
		Mockito.when(productService.getAll()).thenReturn(mockEntity);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = mapToJson(mockResponse);
		verify(productService, times(1)).getAll();
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	@DisplayName("Save product")
	public void saveProduct() throws Exception {
		Product mockProduct = new Product("61247a9676dd0c5688a1a259", "P0001", "Product 001", 25000.0, 200);
		SaveProductRequest exampleRequest = new SaveProductRequest("P0001", "Product 001", 25000.0, 200);
		String uri = "/products";
		Mockito.when(productService.save(Mockito.any(Product.class))).thenReturn(mockProduct);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
				.content(mapToJson(exampleRequest)).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertThat(HttpStatus.CREATED.value()).isEqualTo(response.getStatus());
		assertThat("http://localhost/products/61247a9676dd0c5688a1a259")
				.isEqualTo(response.getHeader(HttpHeaders.LOCATION));

	}

}
