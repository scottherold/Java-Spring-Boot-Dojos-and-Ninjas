package com.sherold.dojosandninjas.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sherold.dojosandninjas.models.Dojo;
import com.sherold.dojosandninjas.models.Ninja;
import com.sherold.dojosandninjas.services.AppService;

@Controller // Designates class as controller
public class HomeController {
	// <----- Attributes ----->
	private final AppService appService;
	
	// <----- Constructors ----->
	public HomeController(AppService appService) {
		this.appService = appService;
	}
	
	// <----- Methods ----->
	// GET Routes
	
	// Display new Dojo form
	@RequestMapping(value="dojos/new", method=RequestMethod.GET)
	public String newDojo(@ModelAttribute("dojo") Dojo dojo) {
		return "/dojos/new.jsp";
	}
	
	// Display new Ninja form
	@RequestMapping(value="ninjas/new", method=RequestMethod.GET)
	public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model) {
		List<Dojo> dojos = appService.allDojos();
		model.addAttribute("dojos", dojos);
		
		return "/ninjas/new.jsp";
	}
	
	// Display all Ninjas in a dojo
	@RequestMapping(value="dojos/{id}", method=RequestMethod.GET)
	public String showDojo(@PathVariable("id") Long id, Model model) {
		Dojo dojo = appService.findDojo(id);
		model.addAttribute("dojo", dojo);
		
		return "dojos/show.jsp";
	}
		
	// POST routes
	
	// Create new Dojo
	@RequestMapping(value="dojos/new", method=RequestMethod.POST)
	// Valid and BindingResult validate form data
	public String createDojo(@Valid @ModelAttribute("dojo") Dojo dojo, BindingResult result) {
		// Event handler for validation
		if (result.hasErrors()) {
			return "/dojos/new.jsp";
		} else {
			appService.createDojo(dojo);
			
			return "redirect:/ninjas/new";
		}
	}
	
	// Create new Ninja
	@RequestMapping(value="ninjas/new", method=RequestMethod.POST)
	// Valid and BindingResult validate form data
	public String createNinja(@Valid @ModelAttribute("ninja") Ninja ninja, BindingResult result, Model model) {
		// Event handler for validation
		if (result.hasErrors()) {
			List<Dojo> dojos = appService.allDojos();
			model.addAttribute("dojos", dojos);
			return "/ninjas/new.jsp";
		} else {
			appService.createNinja(ninja);
			Long dojoNum = ninja.getDojo().getId();
			
			return "redirect:/dojos/" + dojoNum;
		}
	}
	
}
