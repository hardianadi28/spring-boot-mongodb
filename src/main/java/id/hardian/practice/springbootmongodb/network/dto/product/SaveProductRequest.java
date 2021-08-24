package id.hardian.practice.springbootmongodb.network.dto.product;

import lombok.Data;

@Data
public class SaveProductRequest {
	private String productCode;
	private String productName;
	private Double productPrice;
	private Integer productStock;
}
