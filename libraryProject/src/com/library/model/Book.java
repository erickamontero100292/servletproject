package com.library.model;

import java.sql.Date;
import java.time.LocalDate;

public class Book {
	int id;
	String name;
	String author;
	int datePublished;
	String description;
	String detail;

	public Book(int id) {
		this.id = id;
	}

	public Book(String name) {
		
		this.name = name;
		this.author ="Ericka";
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
	public int getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(int datePublished) {
		this.datePublished = datePublished;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
