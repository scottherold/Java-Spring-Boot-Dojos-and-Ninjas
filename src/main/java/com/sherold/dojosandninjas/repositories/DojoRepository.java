package com.sherold.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.dojosandninjas.models.Dojo;

@Repository // Designates interface as repo
public interface DojoRepository extends CrudRepository<Dojo, Long> {
	// Sets query to return Dojo objects
	List<Dojo> findAll();
}
