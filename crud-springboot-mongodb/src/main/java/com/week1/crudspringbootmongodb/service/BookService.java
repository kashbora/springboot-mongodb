package com.week1.crudspringbootmongodb.service;

import java.util.List;

import com.week1.crudspringbootmongodb.exceptions.BookNotFoundException;
import com.week1.crudspringbootmongodb.model.BookModel;



public interface BookService {
	
	public List<BookModel> getAllBookDetails();
	
	public BookModel saveBook(BookModel newBookModel);
	
	public BookModel getBookById(String id) throws BookNotFoundException;
	
	public BookModel updateBook(String id, BookModel newBookModel) throws BookNotFoundException;
	
	public String deleteBook(String id) throws BookNotFoundException;
}
