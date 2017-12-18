package com.springjpa.controler;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.model.Korisnik;
import com.springjpa.repository.UserRepository;
import com.springjpa.services.KorisnikService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/korisnici")
public class UserController {
	
	@Autowired
    private KorisnikService korisnikService;
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody Korisnik korisnik)
    {
    	if (korisnik==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nemoguce kreirati praznog korisnika");
        try {
         return korisnikService.registerKorisnik(korisnik);
        	} 
        catch (ServiceException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(e.getLocalizedMessage());
        }
    }
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getall()
    {
    	List<Korisnik> list = userRepo.findAll();
       
    	if (list.size()==0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nema korisnika");
						
		return ResponseEntity.ok(list);
    }

}
