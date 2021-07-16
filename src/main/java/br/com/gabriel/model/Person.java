package br.com.gabriel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {

	public Person(String name, String document, Double wage, String gender, List<Address> addresses) {
		this.name = name;
		this.document = document;
		this.wage = wage;
		this.gender = gender;
		this.addresses = addresses;
	}
	
	public Person() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "document", nullable = false)
	private String document;

	@Column(name = "wage", nullable = false)
	private Double wage;

	@Column(name = "gender", nullable = false)
	private String gender;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Address> addresses = new ArrayList<>();

}
