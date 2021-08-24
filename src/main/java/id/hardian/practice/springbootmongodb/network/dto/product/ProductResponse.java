package id.hardian.practice.springbootmongodb.network.dto.product;

import lombok.Data;

@Data
public class ProductResponse {
	private String id;
	private String productCode;
	private String productName;
	private Double productPrice;
	private Integer productStock;
}
