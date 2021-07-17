package br.com.gabriel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gabriel.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Page<Person> findByName(String name, Pageable pagination);
	
	Page<Person> findByGender(String gender, Pageable pagination);
	
	Page<Person> findByNameAndGender(String name, String gender, Pageable pagination);
	
}
