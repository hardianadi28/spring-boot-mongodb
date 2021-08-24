package id.hardian.practice.springbootmongodb.network.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	private String id;
	private String productCode;
	private String productName;
	private Double productPrice;
	private Integer productStock;
}
