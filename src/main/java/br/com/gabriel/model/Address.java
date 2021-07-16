package br.com.gabriel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

	public Address(String country, String state, String city, Integer zipCode, String street) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
	}

	public Address() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "country", nullable = false)
	private String country;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "zipCode", nullable = false)
	private Integer zipCode;

	@Column(name = "street", nullable = false)
	private String street;

}
