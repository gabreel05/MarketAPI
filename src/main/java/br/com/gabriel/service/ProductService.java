package br.com.gabriel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gabriel.exception.NotFoundException;
import br.com.gabriel.mapper.request.ProductRequest;
import br.com.gabriel.mapper.response.ProductResponse;
import br.com.gabriel.model.Product;
import br.com.gabriel.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Page<ProductResponse> findAll(String description, Pageable pagination) {
		Page<Product> products;

		if (!description.isEmpty()) {
			products = productRepository.findByDescription(description, pagination);
		} else {
			products = productRepository.findAll(pagination);
		}

		return ProductResponse.toProduct(products);
	}

	public Optional<Product> findProduct(Long id) {
		return productRepository.findById(id);
	}

	public ProductResponse save(ProductRequest productRequest) {
		Product product = productRequest.toProduct();

		productRepository.save(product);

		return new ProductResponse(product);
	}

	public ProductResponse update(Long id, ProductRequest productRequest) {
		Product product = findById(id);

		product = productRequest.update(id, productRepository);

		return new ProductResponse(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
	}

}
