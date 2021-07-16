package br.com.gabriel.mapper.request;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import br.com.gabriel.model.Demand;
import br.com.gabriel.model.Product;
import br.com.gabriel.repository.DemandRepository;
import br.com.gabriel.repository.ProductRepository;
import lombok.Getter;

@Getter
public class DemandRequest {

	@NotNull
	private List<Long> productIds;
	
	public Demand toDemand(ProductRepository productRepository) {
		List<Product> products = productRepository.findAllById(productIds);
		
		Stream<Long> streamProductIds = products.stream().map(product -> product.getId());
		
		streamProductIds.map(productId -> productIds.add(productId));
		
		return new Demand(products);
	}
	
	public Demand update(Long id, DemandRepository demandRepository, ProductRepository productRepository) {
		Demand demand = demandRepository.getById(id);
		List<Product> products = productRepository.findAllById(productIds);
		
		Stream<Long> streamProductIds = products.stream().map(product -> product.getId());
		streamProductIds.map(productId -> productIds.add(productId));
		
		demand.setProducts(products);
		
		return demand;
	}

}
