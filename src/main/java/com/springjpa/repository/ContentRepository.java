package com.springjpa.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springjpa.model.Content;
import com.springjpa.model.Korisnik;

@Repository
public interface ContentRepository extends PagingAndSortingRepository<Content, Integer> {

	public List<Content> findAll();
	public List<Content> findByOwner(Korisnik owner);
}
