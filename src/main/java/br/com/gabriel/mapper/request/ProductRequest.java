package br.com.gabriel.mapper.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.gabriel.model.Product;
import br.com.gabriel.repository.ProductRepository;
import lombok.Getter;

@Getter
public class ProductRequest {

	@NotBlank
	private String description;
	@NotNull
	@Positive
	private Double unitPrice;

	public Product toProduct() {
		return new Product(description, unitPrice);
	}

	public Product update(Long id, ProductRepository productRepository) {
		Product product = productRepository.getById(id);

		product.setDescription(getDescription());
		product.setUnitPrice(getUnitPrice());

		return product;
	}

}
