package com.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjpa.model.Korisnik;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Korisnik, Integer> {
	
	public Korisnik findByEmail(String email);
	public Korisnik findByIdKorisnika(Integer id);
	public List<Korisnik> findAll();
	
	@Query("SELECT k FROM Korisnik k WHERE username= :name")
	public Korisnik findByUsername (@Param("name")String name);	
}
