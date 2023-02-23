package com.week1.crudspringbootmongodb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.week1.crudspringbootmongodb.exceptions.BookNotFoundException;
import com.week1.crudspringbootmongodb.model.BookModel;
import com.week1.crudspringbootmongodb.repository.BookRepository;



@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<BookModel> getAllBookDetails() {
		List<BookModel> bookModels = bookRepository.findAll();
		return bookModels;
	}

	@Override
	public BookModel saveBook(BookModel newBookModel) {
		BookModel bookModel = bookRepository.save(newBookModel);
		return bookModel;
	}

	@Override
	public BookModel getBookById(String id) throws BookNotFoundException {
		Optional<BookModel> optionalBookModel = bookRepository.findById(id);
		if(optionalBookModel.isEmpty()) {
			throw new BookNotFoundException("Book with id "+ id+" not found");
		}
		return optionalBookModel.get();
	}

	@Override
	public BookModel updateBook(String id, BookModel newBookModel) throws BookNotFoundException{
		Optional<BookModel> optionalBookModel = bookRepository.findById(id);
		if(optionalBookModel.isEmpty()) {
			throw new BookNotFoundException("Book with id "+ id+" not found");
		}
		BookModel tempBook = optionalBookModel.get();
		tempBook.setTitle(newBookModel.getTitle());
		tempBook.setAuthor(newBookModel.getAuthor());
		BookModel savedBook = bookRepository.save(tempBook);
		return savedBook;
	}

	@Override
	public String deleteBook(String id) throws BookNotFoundException{
		Optional<BookModel> optionalBookModel = bookRepository.findById(id);
		if(optionalBookModel.isEmpty()) {
			throw new BookNotFoundException("Book with id "+ id+" not found");
		}
		bookRepository.deleteById(id);
		return "Book with id" + id+" has been deleted!";
	}
	
}
