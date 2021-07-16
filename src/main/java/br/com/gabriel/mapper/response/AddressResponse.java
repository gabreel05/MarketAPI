package br.com.gabriel.mapper.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.gabriel.model.Address;
import lombok.Getter;

@Getter
public class AddressResponse {

	public AddressResponse(Address address) {
		this.country = address.getCountry();
		this.state = address.getState();
		this.city = address.getCity();
		this.zipCode = address.getZipCode();
		this.street = address.getStreet();
	}
	
	private String country;
	private String state;
	private String city;
	private Integer zipCode;
	private String street;
	
	
	public static Page<AddressResponse> toAddress(Page<Address> addresses) {
		return addresses.map(AddressResponse::new);
	}
	
	public static List<AddressResponse> toAddressList(List<Address> addresses) {
		return addresses.stream().map(AddressResponse::new).collect(Collectors.toList());
	}
}
