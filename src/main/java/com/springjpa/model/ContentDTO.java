package com.springjpa.model;

public class ContentDTO {
	private String fileName;
	private String datatype;
	private String owner;
	private char[] content;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public char[] getContent() {
		return content;
	}
	public void setContent(char[] content) {
		this.content = content;
	}
}
