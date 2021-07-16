package br.com.gabriel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriel.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByDescription(String description, Pageable pagination);

}
