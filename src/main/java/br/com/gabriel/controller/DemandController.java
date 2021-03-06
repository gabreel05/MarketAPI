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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gabriel.mapper.request.DemandRequest;
import br.com.gabriel.mapper.response.DemandResponse;
import br.com.gabriel.model.Demand;
import br.com.gabriel.service.DemandService;

@RestController
@RequestMapping("/protected/demands")
public class DemandController {

	private final DemandService demandService;

	@Autowired
	public DemandController(DemandService demandService) {
		this.demandService = demandService;
	}

	@GetMapping
	@Cacheable(value = "demandList")
	public ResponseEntity<Page<DemandResponse>> findAll(@PageableDefault(size = 100) Pageable pagination) {
		Page<DemandResponse> demands = demandService.findAll(pagination);

		return ResponseEntity.ok().body(demands);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DemandResponse> findDemand(@PathVariable Long id) {
		Demand demand = demandService.findDemand(id);

		return ResponseEntity.ok().body(new DemandResponse(demand));
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "demandList", allEntries = true)
	public ResponseEntity<DemandResponse> save(@RequestBody @Valid DemandRequest demandRequest,
			UriComponentsBuilder uriComponentsBuilder) {
		DemandResponse demand = demandService.save(demandRequest);

		URI uri = uriComponentsBuilder.path("/protected/demands/{id}").buildAndExpand(demand.getId()).toUri();

		return ResponseEntity.created(uri).body(demand);
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "demandList", allEntries = true)
	public ResponseEntity<DemandResponse> update(@PathVariable Long id,
			@RequestBody @Valid DemandRequest demandRequest) {
		DemandResponse demand = demandService.update(id, demandRequest);

		return ResponseEntity.ok().body(demand);
	}

	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "demandList", allEntries = true)

	public ResponseEntity<Void> deleteDemand(@PathVariable Long id) {
		demandService.deleteDemand(id);

		return ResponseEntity.noContent().build();
	}

}
