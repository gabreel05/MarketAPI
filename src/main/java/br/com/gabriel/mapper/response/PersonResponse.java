package br.com.gabriel.mapper.response;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.gabriel.model.Person;
import lombok.Getter;

@Getter
public class PersonResponse {
	
	public PersonResponse(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.document = person.getDocument();
		this.wage = person.getWage();
		this.gender = person.getGender();
		this.addresses = AddressResponse.toAddressList(person.getAddresses());
	}
	
	private Long id;
	private String name;
	private String document;
	private Double wage;
	private String gender;
	private List<AddressResponse> addresses;
	
	public static Page<PersonResponse> toPerson(Page<Person> persons) {
		return persons.map(PersonResponse::new);
	}

}