package br.com.gabriel.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gabriel.mapper.request.ProductRequest;
import br.com.gabriel.mapper.response.ProductResponse;
import br.com.gabriel.model.Product;
import br.com.gabriel.service.ProductService;

@RestController
@RequestMapping("/protected/products")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@Cacheable(value = "productList")
	public ResponseEntity<Page<ProductResponse>> findAll(
			@RequestParam(required = false, defaultValue = "") String description,
			@PageableDefault(size = 100) Pageable pagination
	) {
		Page<ProductResponse> products = productService.findAll(description, pagination);

		return ResponseEntity.ok().body(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findProduct(@PathVariable Long id) {
		Product product = productService.findProduct(id);

		return ResponseEntity.ok().body(new ProductResponse(product));
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "productList", allEntries = true)
	public ResponseEntity<ProductResponse> save(@RequestBody @Valid ProductRequest productRequest,
			UriComponentsBuilder uriComponentsBuilder) {
		ProductResponse product = productService.save(productRequest);

		URI uri = uriComponentsBuilder.path("/protected/products/{id}").buildAndExpand(product.getId()).toUri();

		return ResponseEntity.created(uri).body(product);
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "productList", allEntries = true)
	public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
		ProductResponse product = productService.update(id, productRequest);
		
		return ResponseEntity.ok().body(product);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "productList", allEntries = true)
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		
		return ResponseEntity.noContent().build();
	}

}
