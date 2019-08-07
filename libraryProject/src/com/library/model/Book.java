package com.library.model;

import java.util.Date;

public class Book {
	
	String name;
	String author;
	Date publicDate;
	
	
	public Book(String name) {
		
		this.name = name;
		this.author ="Ericka";
		this.publicDate = new Date();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}
	

}
