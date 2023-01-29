package com.stackroute.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.models.Book;
import com.stackroute.services.BookService;

@CrossOrigin
@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/get")
	public ResponseEntity<List<Book>> getBooks(@RequestParam(required = false) String sortBy,
			@RequestParam(required = false) String order) {
		List<Book> books = bookService.getAllBooks(sortBy, order);
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) {
		Book book = bookService.getBookById(id);
		return new ResponseEntity<>(book, HttpStatus.FOUND);
	}

	@PostMapping("/create")
	public ResponseEntity<Integer> createBook(@RequestBody Book book) {
		Integer bookId = bookService.createBook(book);
		return new ResponseEntity<>(bookId, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Integer> updateBookEntirely(@RequestBody Book book) {
		Integer bookId = bookService.updateBook(book);
		return new ResponseEntity<>(bookId, HttpStatus.ACCEPTED);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Integer> updateBookPatially(@PathVariable Integer id, @RequestBody Map<String, Object> map) {
		Integer bookId = bookService.patchBook(id, map);
		return new ResponseEntity<>(bookId, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Integer> deleteBookById(@PathVariable int id) {
		Integer bookId = bookService.deleteBookById(id);
		return new ResponseEntity<>(bookId, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBooksByName(@RequestParam(required = false) String name) {
		List<Book> results = bookService.searchBooksByName(name);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
}
