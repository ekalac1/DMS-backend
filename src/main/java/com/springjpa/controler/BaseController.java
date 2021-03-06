package com.springjpa.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.model.Korisnik;
import com.springjpa.repository.UserRepository;

@RestController
public class BaseController {
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping("/saveUser")
	public String process(){
		userRepo.save(new Korisnik("elza", "098f6bcd4621d373cade4e832627b4f6", "elzaa_95@hotmail.com", "ELZA", Korisnik.roleUser));
		return "Done";
	}
	
	@RequestMapping("/saveAdmin")
	public String saveAdmin(){
		userRepo.save(new Korisnik("admin", "098f6bcd4621d373cade4e832627b4f6", "admin@hotmail.com", "ELZA", Korisnik.roleAdmin));
		return "Done";
	}
}