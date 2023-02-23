package com.week1.crudspringbootmongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class BookModel {
	@Id
	private String id;
	private String title;
	private String author;
	
	
	public BookModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BookModel(String id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	
}
