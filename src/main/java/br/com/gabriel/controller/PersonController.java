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

import br.com.gabriel.mapper.request.PersonRequest;
import br.com.gabriel.mapper.response.PersonResponse;
import br.com.gabriel.model.Person;
import br.com.gabriel.service.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	@Cacheable(value = "personList")
	public ResponseEntity<Page<PersonResponse>> findAll(
			@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") String gender,
			@PageableDefault Pageable pagination
	) {
		Page<PersonResponse> persons = personService.findAll(name, gender, pagination);

		return ResponseEntity.ok().body(persons);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonResponse> findPerson(@PathVariable Long id) {
		Person person = personService.findPerson(id);

		return ResponseEntity.ok().body(new PersonResponse(person));
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "personList", allEntries = true)
	public ResponseEntity<PersonResponse> save(@RequestBody @Valid PersonRequest personRequest,
			UriComponentsBuilder uriComponentsBuilder) {
		PersonResponse person = personService.save(personRequest);

		URI uri = uriComponentsBuilder.path("/persons/{id}").buildAndExpand(person.getId()).toUri();

		return ResponseEntity.created(uri).body(person);
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "personList", allEntries = true)
	public ResponseEntity<PersonResponse> update(@PathVariable Long id,
			@RequestBody @Valid PersonRequest personRequest) {
		PersonResponse person = personService.update(id, personRequest);

		return ResponseEntity.ok().body(person);
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "personList", allEntries = true)
	public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
		personService.deletePerson(id);

		return ResponseEntity.noContent().build();
	}

}
