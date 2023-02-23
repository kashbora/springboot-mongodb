package com.week1.crudspringbootmongodb.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.week1.crudspringbootmongodb.exceptions.BookNotFoundException;
import com.week1.crudspringbootmongodb.model.BookModel;
import com.week1.crudspringbootmongodb.service.BookService;


@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	private final Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@GetMapping("/books")
	public ResponseEntity<List<BookModel>> getAllBooks(){
		List<BookModel> bookModels =  bookService.getAllBookDetails();
		return new ResponseEntity<>(bookModels,HttpStatus.OK);
	}
	
	@PostMapping("/books")
	public ResponseEntity<BookModel> saveBook(@RequestBody BookModel newBookModel) {
		BookModel bookModel = bookService.saveBook(newBookModel);
		logger.info("Save Method executed successfully");
		return new ResponseEntity<>(bookModel, HttpStatus.CREATED);
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<BookModel> getBookById(@PathVariable("id") String id) throws BookNotFoundException{
		BookModel bookModel = bookService.getBookById(id);
		logger.info("GetById Method executed successfully");
		return new ResponseEntity<>(bookModel, HttpStatus.OK);
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<BookModel> updateBook(@PathVariable("id") String id, @RequestBody BookModel newBookModel) throws BookNotFoundException{
		BookModel bookModel = bookService.updateBook(id, newBookModel);
		logger.info("Update Method executed successfully");
		return new ResponseEntity<>(bookModel, HttpStatus.OK);
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") String id) throws BookNotFoundException{
		String msg = bookService.deleteBook(id);
		logger.info("Delete Method executed successfully");
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
