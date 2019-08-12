package com.library.model;

import java.util.Date;

public class Book {
	int id;
	String name;
	String author;
	Date datePublished;
	String description;

	public Book(int id) {
		this.id = id;
	}

	public Book(String name) {
		
		this.name = name;
		this.author ="Ericka";
		this.datePublished = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public Date getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
