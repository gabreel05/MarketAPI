package br.com.gabriel.mapper.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.gabriel.model.Address;
import br.com.gabriel.model.Person;
import br.com.gabriel.repository.AddressRepository;
import br.com.gabriel.repository.PersonRepository;
import lombok.Getter;

@Getter
public class PersonRequest {

	@NotBlank
	private String name;
	@NotBlank
	private String document;
	@NotNull
	@Positive
	private Double wage;
	@NotBlank
	private String gender;
	@NotNull
	private List<AddressRequest> addresses;

	public Person toPerson(PersonRepository personRepository, AddressRepository addressRepository) {
		List<Address> addressesList = new AddressRequest().toAddresses(this.addresses, addressRepository);

		Person person = new Person(this.name, this.document, this.wage, this.gender, addressesList);

		return person;
	}

	public Person update(Long id, PersonRepository personRepository, AddressRepository addressRepository) {
		Person person = personRepository.getById(id);

		person.setName(getName());
		person.setDocument(getDocument());
		person.setWage(getWage());
		person.setGender(getGender());
		
		List<Address> addressesList = new AddressRequest().toAddresses(this.addresses, addressRepository);

		addressRepository.saveAll(addressesList);
		
		person.setAddresses(addressesList);

		return person;
	}

}
