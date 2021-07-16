package br.com.gabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabriel.model.Demand;

public interface DemandRepository extends JpaRepository<Demand, Long> {

}
