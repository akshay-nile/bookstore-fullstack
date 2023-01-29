package com.stackroute.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.models.Book;
import com.stackroute.services.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	private static ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private BookService bookService;
	private Book book;
	private List<Book> books;

	@InjectMocks
	private BookController bookController;

	@BeforeEach
	public void setUp() {
		book = new Book(-1, "Mock Book", "Mock Author", "CATEGORY", 700.56);
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	private static String asJsonString(Object obj) {
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Test
	public void getAllBooksSortedByPriceTest() throws Exception {
		when(bookService.getAllBooks("price", null)).thenReturn(books);
		mockMvc.perform(get("/api/book/get?sortBy=price")).andExpect(status().isOk()).andDo(print());
		verify(bookService).getAllBooks("price", null);
	}

	@Test
	public void getBooksSearchedByNameTest() throws Exception {
		when(bookService.searchBooksByName("")).thenReturn(books);
		mockMvc.perform(get("/api/book/search?name=")).andExpect(status().isOk()).andDo(print());
		verify(bookService).searchBooksByName("");
	}

	@Test
	public void getBookByIdTest() throws Exception {
		when(bookService.getBookById(book.getId())).thenReturn(book);
		mockMvc.perform(get("/api/book/get/" + book.getId())).andExpect(status().isFound()).andDo(print());
		verify(bookService).getBookById(book.getId());
	}

	@Test
	public void createBookTest() throws Exception {
		when(bookService.createBook(any())).thenReturn(book.getId());
		mockMvc.perform(post("/api/book/create").contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
				.andExpect(status().isCreated());
		verify(bookService).createBook(any());
	}

	@Test
	public void deleteBookTest() throws Exception {
		int id = anyInt();
		when(bookService.deleteBookById(id)).thenReturn(book.getId());
		mockMvc.perform(delete("/api/book/delete/" + id)).andExpect(status().isOk());
		verify(bookService).deleteBookById(id);
	}

	@Test
	public void updateBookTest() throws Exception {
		when(bookService.updateBook(any())).thenReturn(book.getId());
		mockMvc.perform(put("/api/book/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(book)))
				.andExpect(status().isAccepted());
		verify(bookService).updateBook(any());
	}
}
