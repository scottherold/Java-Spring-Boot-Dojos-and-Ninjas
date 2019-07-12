package com.sherold.dojosandninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherold.dojosandninjas.models.Dojo;
import com.sherold.dojosandninjas.models.Ninja;
import com.sherold.dojosandninjas.repositories.DojoRepository;
import com.sherold.dojosandninjas.repositories.NinjaRepository;

@Service // Sets the class as a services
public class AppService {
	// <----- Attributes ----->
	private final DojoRepository dojoRepo;
	private final NinjaRepository ninjaRepo;
	
	// <----- Constructors ----->
	public AppService(DojoRepository dojoRepo, NinjaRepository ninjaRepo) {
		this.dojoRepo = dojoRepo;
		this.ninjaRepo = ninjaRepo;
	}
	
	// <----- Methods ----->
	// SELECT * FROM dojos
	public List<Dojo> allDojos() {
		return dojoRepo.findAll();
	}
	
	// SELECT * FROM ninjas
	public List<Ninja> allNinjas() {
		return ninjaRepo.findAll();
	}
	
	// SELECT * FROM dojos WHERE id =
	public Dojo findDojo(Long id) {
		// Optional to determine existence
		Optional<Dojo> oDojo = dojoRepo.findById(id);
		if(oDojo.isPresent()) {
			return oDojo.get();
		} else {
			return null;
		}
	}
	
	// SELECT * FROM ninjas WHERE id =
	public Ninja findNinja(Long id) {
		// Optional to determine existence
		Optional<Ninja> oNinja = ninjaRepo.findById(id);
		if(oNinja.isPresent()) {
			return oNinja.get();
		} else {
			return null;
		}
	}
	
	// INSERT INTO dojos
	public Dojo createDojo(Dojo d) {
		return dojoRepo.save(d);
	}
	
	// INSERT INTO ninjas
	public Ninja createNinja(Ninja n) {
		return ninjaRepo.save(n);
	}
}
