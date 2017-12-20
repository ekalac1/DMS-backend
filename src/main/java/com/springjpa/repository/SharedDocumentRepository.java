package com.springjpa.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.springjpa.model.SharedDocument;
import com.springjpa.model.Content;
import com.springjpa.model.Korisnik;

public interface SharedDocumentRepository extends PagingAndSortingRepository<SharedDocument, Integer> {

	public List<SharedDocument> findAll();
	public List<SharedDocument> findByOwnerOrderByIdDesc(Korisnik owner);
	public List<SharedDocument> findByDocumentOrderByIdDesc(Content document);
	public SharedDocument findById(int id);
}
