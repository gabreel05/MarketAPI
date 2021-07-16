package br.com.gabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gabriel.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
