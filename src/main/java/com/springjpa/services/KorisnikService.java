package com.springjpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springjpa.model.Korisnik;
import com.springjpa.repository.KorisnikRepository;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Service
public class KorisnikService implements UserDetailsService{

	private final static int PAGESIZE = 3;
	
    @Autowired
    KorisnikRepository repository;

    public Iterable<Korisnik> findAllKorisnici() {
        return repository.findAll();
    }
    
    public Korisnik findKorisnici (int id) {
        return repository.findOne(Integer.valueOf(id));
    }

    public List<Korisnik> getPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.ASC, "idKorisnika");

        return repository.findAll(request).getContent();
    }
    
   
    public ResponseEntity removeKorisnici(int id) {
    	if (repository.findByIdKorisnika(id)==null)
    		ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ne postoji trazeni korisnik");
    	repository.delete(id);
    	return ResponseEntity.status(HttpStatus.OK).body(true);
	}
 
    public ResponseEntity registerKorisnik(Korisnik korisnik) {
    	
    	// Registracija nije implementirana do kraja

       
        if (korisnik.getUsername()=="")
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username ne može biti prazno");
    	else if (repository.findByUsername(korisnik.getUsername())!=null)
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username mora biti jedinstven");
    	else if (korisnik.getPassword()=="")
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password ne može biti prazno");
    	else if (korisnik.getEmail()=="")
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Morate unijeti email");
        
        korisnik.setPassword(toMD5(korisnik.getPassword()));
        Korisnik kreiranKorisnik = repository.save(korisnik);

        return ResponseEntity.status(HttpStatus.OK).body( kreiranKorisnik != null);

    }
	 @Override
	    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

	        Korisnik korisnik = repository.findByUsername(s);
	        if(korisnik == null) {
	            throw new UsernameNotFoundException("Nije pronađen korisnik s takvim username-om");
	        }
	        return new User(korisnik.getUsername(), korisnik.getPassword(), getGrantedAuthorities(korisnik));
	    }
	    public Korisnik getKorisnikByUserName(String username) {
	    	Korisnik k = repository.findByUsername(username);
	    	//k.setPassword("");
	    	return k;
	    	
	    }
	    
	    private Collection<GrantedAuthority> getGrantedAuthorities(Korisnik student) {
	        Collection<GrantedAuthority> authorities = new ArrayList<>();
	           if(student != null) {
	            authorities.add(new SimpleGrantedAuthority("ADMIN"));
	        }
	        return authorities;
	    }
	
	    public String toMD5(String str)
	    {
	        byte[] pass = null;
	        
	        try {
				pass = str.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	        
	        MessageDigest m = null;
			try {
				m = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			StringBuffer hexString = new StringBuffer();
			
			byte[] passHash = m.digest(pass);
			
			for (int i = 0; i < passHash.length; i++) {
			    if ((0xff & passHash[i]) < 0x10) {
			        hexString.append("0"
			                + Integer.toHexString((0xFF & passHash[i])));
			    } else {
			        hexString.append(Integer.toHexString(0xFF & passHash[i]));
			    }
			}
			
			return hexString.toString();
	   }
	    
	   
}
