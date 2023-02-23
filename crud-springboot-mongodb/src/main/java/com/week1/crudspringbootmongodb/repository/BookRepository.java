package com.week1.crudspringbootmongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.week1.crudspringbootmongodb.model.BookModel;



@Repository
public interface BookRepository extends MongoRepository<BookModel, String>{
	
}
