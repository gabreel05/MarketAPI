package br.com.gabriel.mapper.response;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.gabriel.model.Product;
import lombok.Getter;

@Getter
public class ProductResponse {

	public ProductResponse(Product product) {
		this.id = product.getId();
		this.description = product.getDescription();
		this.unitPrice = convertDoubleToCurrency(product.getUnitPrice());
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

	private String convertDoubleToCurrency(Double value) {
		Locale locale = new Locale("pt", "BR");

		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);

		return formatter.format(value);
	}

}
