package br.com.gabriel.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "demand")
@SQLDelete(sql = "UPDATE demand SET is_active = false WHERE id=?")
@Where(clause = "is_active = true")
@Getter
@Setter
public class Demand {

	public Demand(List<Product> products) {
		this.products = products;
	}

	public Demand() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "demand_product",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "demand_id")
	)
	private List<Product> products = new ArrayList<>();

	@Column(name = "isActive", nullable = false)
	private Boolean isActive = Boolean.TRUE;

}
