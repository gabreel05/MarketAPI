package br.com.gabriel.mapper.response;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.gabriel.model.Person;
import br.com.gabriel.util.DocumentFormatter;
import br.com.gabriel.util.MoneyConverter;
import lombok.Getter;

@Getter
public class PersonResponse {
	
	public PersonResponse(Person person) {
		this.id = person.getId();
		this.name = person.getName();
		this.document = DocumentFormatter.documentFormatter(person.getDocument());
		this.wage = MoneyConverter.convertDoubleToCurrency(person.getWage());
		this.gender = person.getGender();
		this.addresses = AddressResponse.toAddressList(person.getAddresses());
	}
	
	private Long id;
	private String name;
	private String document;
	private String wage;
	private String gender;
	private List<AddressResponse> addresses;
	
	public static Page<PersonResponse> toPerson(Page<Person> persons) {
		return persons.map(PersonResponse::new);
	}
}
