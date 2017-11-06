package com.springjpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="korisnik")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer idKorisnika;
    
    private String username;
    
    @Column(name="password_hash")
    private String password;
    private String email;
    
   public Korisnik(String username, String hashPass, String email) {
	   this.username = username;
	   this.password = hashPass;
	   this.email = email;
   } 
	public Korisnik() {
    	
    }
    
	public void setIdKorisnika(Integer idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdKorisnika() {
		return idKorisnika;
	}
	
	@Override
    public String toString() {
        return String.format("Korisnik[id=%d, username='%s', email='%s']", idKorisnika, username, email);
    }
	
	
}
