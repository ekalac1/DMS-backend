package com.springjpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "shareddocument")
public class SharedDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@ManyToOne(targetEntity=Korisnik.class)
	@JoinColumn(name="idKorisnika")
    private Korisnik owner;
	
	@ManyToOne(targetEntity=Content.class)
	@JoinColumn(name="idDokumenta")
    private Content document;
	
	public SharedDocument() {}
	
	public SharedDocument(Korisnik owner, Content document) {
		super();
		this.owner = owner;
		this.document = document;
	}

	public Korisnik getOwner() {
		return owner;
	}

	public void setOwner(Korisnik owner) {
		this.owner = owner;
	}
	
	public Content getDocument() {
		return document;
	}

	public void setDocument(Content document) {
		this.document = document;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
