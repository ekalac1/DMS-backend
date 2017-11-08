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
import com.springjpa.services.KorisnikService;

@RestController
@CrossOrigin
@RequestMapping(path="/korisnici")
public class KorisnikController {
	
	@Autowired
    private KorisnikService korisnikService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody Korisnik korisnik)
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

}
