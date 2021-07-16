package br.com.gabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriel.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
