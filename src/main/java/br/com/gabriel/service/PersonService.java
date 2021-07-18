package br.com.gabriel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gabriel.exception.NotFoundException;
import br.com.gabriel.mapper.request.PersonRequest;
import br.com.gabriel.mapper.response.PersonResponse;
import br.com.gabriel.model.Person;
import br.com.gabriel.repository.AddressRepository;
import br.com.gabriel.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	private AddressRepository addressRepository;

	@Autowired
	public PersonService(PersonRepository personRepository, AddressRepository addressRepository) {
		this.personRepository = personRepository;
		this.addressRepository = addressRepository;
	}

	public Page<PersonResponse> findAll(String name, String gender, Pageable pagination) {
		Page<Person> persons;

		if (!name.isEmpty()) {
			if (!gender.isEmpty()) {
				persons = personRepository.findByNameAndGender(name, gender, pagination);
			} else {
				persons = personRepository.findByName(name, pagination);
			}
		} else if (!gender.isEmpty()) {
			persons = personRepository.findByGender(gender, pagination);
		} else {
			persons = personRepository.findAll(pagination);
		}

		return PersonResponse.toPerson(persons);
	}

	public Person findPerson(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
	}

	public PersonResponse save(PersonRequest personRequest) {
		Person person = personRequest.toPerson(personRepository, addressRepository);

		addressRepository.saveAll(person.getAddresses());

		personRepository.save(person);

		return new PersonResponse(person);
	}

	public PersonResponse update(Long id, PersonRequest personRequest) {
		Person person = findPerson(id);

		person = personRequest.update(id, personRepository, addressRepository);

		return new PersonResponse(person);
	}

	public void deletePerson(Long id) {
		Person person = findPerson(id);
		
		personRepository.delete(person);
	}
}
