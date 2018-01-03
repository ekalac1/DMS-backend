package com.springjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjpa.model.Content;
import com.springjpa.model.Korisnik;

@Repository
public interface ContentRepository extends PagingAndSortingRepository<Content, Integer> {

	public List<Content> findAll();
	public List<Content> findByOwnerOrderByIdDokumentaDesc(Korisnik owner);
	public Content findByIdDokumenta(int id);
	public boolean existsByIdDokumenta(int id);
}
