package com.week1.crudspringbootmongodb.web;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.week1.crudspringbootmongodb.model.BookModel;
import com.week1.crudspringbootmongodb.service.BookService;

@WebMvcTest
public class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@Autowired
	ObjectMapper mapper;
	
	BookModel BOOK_1 = new BookModel("book1", "Memoirs of a Geisha", "Arthur Golden");
	BookModel BOOK_2 = new BookModel("book2", "Lazy Bones", "Sherlock Holmes");
	BookModel BOOK_3 = new BookModel("book3", "The Witch Of Portobello", "Paulo Coelho");
	
	@Test
	public void getAllBooksTest() throws Exception {
		List<BookModel> bookModels = new ArrayList<>(Arrays.asList(BOOK_1,BOOK_2,BOOK_3));
		
		Mockito.when(bookService.getAllBookDetails()).thenReturn(bookModels);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/books")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$[2].title", Matchers.is("The Witch Of Portobello")));
				
	}
	
	@Test
	public void getBookByIdTest() throws Exception {
		Mockito.when(bookService.getBookById(BOOK_1.getId())).thenReturn(BOOK_1);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/books/book1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.title", is("Memoirs of a Geisha")));
	}
	
	@Test
	public void deleteBookById_Success() throws Exception {
		Mockito.when(bookService.getBookById(BOOK_2.getId())).thenReturn(BOOK_2);
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/books/book2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	
	@Test
	public void saveBookTest() throws Exception {
		Mockito.when(bookService.saveBook(BOOK_3)).thenReturn(BOOK_3);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/books")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(BOOK_3));
		
		mockMvc.perform(mockRequest)
				.andExpect(status().isCreated());
				
	}
	
	@Test
	public void updateBookTest() throws Exception {
		BookModel BOOK_1_updated = new BookModel("book1", "Memoirs of a Lady", "Arthur Golden");
		Mockito.when(bookService.getBookById(BOOK_1.getId())).thenReturn(BOOK_1);
		Mockito.when(bookService.saveBook(BOOK_1_updated)).thenReturn(BOOK_1_updated);
		
		
		 mockMvc.perform(put("/api/books/book1")
		           .contentType(MediaType.APPLICATION_JSON)
		           .accept(MediaType.APPLICATION_JSON)
		           .content(this.mapper.writeValueAsString(BOOK_1_updated)))
		           .andExpect(status().isOk())
		           .andDo(print());
	}
	
	
}
