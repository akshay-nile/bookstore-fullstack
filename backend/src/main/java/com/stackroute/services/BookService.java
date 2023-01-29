package com.stackroute.services;

import java.util.List;
import java.util.Map;

import com.stackroute.models.Book;

public interface BookService {

	public List<Book> getAllBooks(String sortBy, String order);

	public Book getBookById(int id);

	public Integer createBook(Book book);

	public Integer updateBook(Book book);

	public Integer deleteBookById(int id);

	public List<Book> searchBooksByName(String name);

	public Integer patchBook(Integer id, Map<String, Object> map);

}
