package br.com.gabriel.mapper.request;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.gabriel.model.Address;
import br.com.gabriel.repository.AddressRepository;
import lombok.Getter;

@Getter
public class AddressRequest {

	@NotBlank
	private String country;
	@NotBlank
	private String state;
	@NotBlank
	private String city;
	@NotNull
	@Positive
	private Integer zipCode;
	@NotBlank
	private String street;
	
	public List<Address> toAddresses(List<AddressRequest> addressRequests, AddressRepository addressRepository) {		
		List<Address> addresses = addressRequests.stream()
				.map(address -> new Address(address.city, address.state, address.city, address.zipCode, address.street))
				.collect(Collectors.toList());
		
		return addresses;
	}
	
}
