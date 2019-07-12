package com.sherold.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.dojosandninjas.models.Ninja;

@Repository // Designates interface as repo
public interface NinjaRepository extends CrudRepository<Ninja, Long> {
	// Sets query to return Ninja objects
	List<Ninja> findAll();
}
