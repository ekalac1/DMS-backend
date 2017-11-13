package com.springjpa.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "content")
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
 
	private String fileName;
	private String datatype;
	
	@ManyToOne(targetEntity=Korisnik.class)
	@JoinColumn(name="idKorisnika")
    private Korisnik owner;
	
	private char[] content;
	
	public Content() {}
	
	public Content(String fileName, String datatype, Korisnik owner, char[] content) {
		super();
		this.fileName = fileName;
		this.datatype = datatype;
		this.owner = owner;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public Korisnik getOwner() {
		return owner;
	}

	public void setOwner(Korisnik owner) {
		this.owner = owner;
	}

	public char[] getContent() {
		return content;
	}

	public void setContent(char[] content) {
		this.content = content;
	}
}
