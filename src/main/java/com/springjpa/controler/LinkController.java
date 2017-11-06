package com.springjpa.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.model.ContentLink;
import com.springjpa.model.Korisnik;
import com.springjpa.repository.KorisnikRepository;
import com.springjpa.repository.LinkRepository;

@RestController
public class LinkController {
	@Autowired
	LinkRepository repository;
	
	@Autowired
	KorisnikRepository userRepo;
	
	@RequestMapping("/save")
	public String process(){
		/*repository.save(new ContentLink("Jack", "Smith"));
		repository.save(new ContentLink("Adam", "Johnson"));
		repository.save(new ContentLink("Kim", "Smith"));
		repository.save(new ContentLink("David", "Williams"));
		repository.save(new ContentLink("Peter", "Davis")); */
		//userRepo.save(new Korisnik("elza", "098f6bcd4621d373cade4e832627b4f6", "elzaa_95@hotmail.com"));
		return "Done";
	}
	
	
	@RequestMapping("/findall")
	public Iterable<ContentLink> findAll(){
		return repository.findAll();
	}
}