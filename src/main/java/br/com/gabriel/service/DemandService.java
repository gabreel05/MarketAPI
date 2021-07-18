package br.com.gabriel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gabriel.exception.NotFoundException;
import br.com.gabriel.mapper.request.DemandRequest;
import br.com.gabriel.mapper.response.DemandResponse;
import br.com.gabriel.model.Demand;
import br.com.gabriel.repository.DemandRepository;
import br.com.gabriel.repository.ProductRepository;

@Service
public class DemandService {

	private final DemandRepository demandRepository;
	private final ProductRepository productRepository;

	@Autowired
	public DemandService(DemandRepository demandRepository, ProductRepository productRepository) {
		this.demandRepository = demandRepository;
		this.productRepository = productRepository;
	}

	public Page<DemandResponse> findAll(Pageable pagination) {
		Page<Demand> demands = demandRepository.findAll(pagination);

		return DemandResponse.toDemand(demands);
	}

	public Optional<Demand> findDemand(Long id) {
		return demandRepository.findById(id);
	}

	public DemandResponse save(DemandRequest demandRequest) {
		Demand demand = demandRequest.toDemand(productRepository);

		demandRepository.save(demand);

		return new DemandResponse(demand);
	}

	public DemandResponse update(Long id, DemandRequest demandRequest) {
		Demand demand = findById(id);

		demand = demandRequest.update(id, demandRepository, productRepository);

		return new DemandResponse(demand);
	}

	public void deleteDemand(Long id) {
		demandRepository.deleteById(id);
	}

	public Demand findById(Long id) {
		return demandRepository.findById(id).orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
	}

}
