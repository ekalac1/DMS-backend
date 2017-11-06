package com.springjpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springjpa.model.ContentLink;

public interface LinkRepository extends CrudRepository<ContentLink, Long>{
	List<ContentLink> findByDatatype(String lastName);
}
