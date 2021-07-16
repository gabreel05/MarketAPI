package br.com.gabriel.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET is_active = false WHERE id=?")
@Where(clause = "is_active = true")
@Getter
@Setter
public class Product {

	public Product(String description, Double unitPrice) {
		this.description = description;
		this.unitPrice = unitPrice;
	}

	public Product() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "unitPrice", nullable = false)
	private Double unitPrice;
	
	@ManyToMany(mappedBy = "products")
	private List<Demand> demand;
	
	@Column(name = "isActive", nullable = false)
	private Boolean isActive = Boolean.TRUE;

}
