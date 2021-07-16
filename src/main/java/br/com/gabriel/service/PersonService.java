package br.com.gabriel.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gabriel.mapper.request.PersonRequest;
import br.com.gabriel.mapper.response.PersonResponse;
import br.com.gabriel.model.Person;
import br.com.gabriel.repository.AddressRepository;
import br.com.gabriel.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;

	public Page<PersonResponse> findAll(Pageable pagination) {
		Page<Person> persons = personRepository.findAll(pagination);
		
		return PersonResponse.toPerson(persons);
	}
	
	public Optional<Person> findPerson(Long id) {
		Optional<Person> person = personRepository.findById(id);
		
		return person;
	}
	
	public PersonResponse save(PersonRequest personRequest) {
		Person person = personRequest.toPerson(personRepository, addressRepository);
				
		addressRepository.saveAll(person.getAddresses());
		
		personRepository.save(person);
		
		return new PersonResponse(person);
	}
	
	public PersonResponse update(Long id, PersonRequest personRequest) {
		Optional<Person> optional = findPerson(id);
		
		if (optional.isPresent()) {
			Person person = personRequest.update(id, personRepository, addressRepository);
			
			return new PersonResponse(person);
		}
		
		return null;
	}
	
	public void deletePerson(Long id) {
		personRepository.deleteById(id);
	}

}
