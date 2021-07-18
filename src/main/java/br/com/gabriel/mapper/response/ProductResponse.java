package br.com.gabriel.mapper.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.gabriel.model.Product;
import br.com.gabriel.util.MoneyConverter;
import lombok.Getter;

@Getter
public class ProductResponse {

	public ProductResponse(Product product) {
		this.id = product.getId();
		this.description = product.getDescription();
		this.unitPrice = MoneyConverter.convertDoubleToCurrency(product.getUnitPrice());
	}

	private Long id;
	private String description;
	private String unitPrice;
	
	public static Page<ProductResponse> toProduct(Page<Product> products) {
		return products.map(ProductResponse::new);
	}
	
	public static List<ProductResponse> toProductList(List<Product> products) {
		return products.stream().map(ProductResponse::new).collect(Collectors.toList());
	}

}
