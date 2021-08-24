package id.hardian.practice.springbootmongodb.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "products")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	private String id;
	private String productCode;
	private String productName;
	private Double productPrice;
	private Integer productStock;
}
