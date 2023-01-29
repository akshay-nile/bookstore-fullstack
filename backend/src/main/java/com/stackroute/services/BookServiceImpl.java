package com.stackroute.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.models.Book;
import com.stackroute.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;

	private static Map<String, Supplier<List<Book>>> map = new HashMap<>();

	@Override
	public List<Book> getAllBooks(String sortBy, String order) {
		if (sortBy == null) {
			sortBy = "id";
		}

		if (order == null) {
			order = "asc";
		}

		if (order.equalsIgnoreCase("asc")) {
			map.put("id", bookRepo::findByOrderByIdAsc);
			map.put("name", bookRepo::findByOrderByNameAsc);
			map.put("author", bookRepo::findByOrderByAuthorAsc);
			map.put("category", bookRepo::findByOrderByCategoryAsc);
			map.put("price", bookRepo::findByOrderByPriceAsc);
		} else if (order.equalsIgnoreCase("desc")) {
			map.put("id", bookRepo::findByOrderByIdDesc);
			map.put("name", bookRepo::findByOrderByNameDesc);
			map.put("author", bookRepo::findByOrderByAuthorDesc);
			map.put("category", bookRepo::findByOrderByCategoryDesc);
			map.put("price", bookRepo::findByOrderByPriceDesc);
		} else {
			throw new RuntimeException("Can't sort results! Invalid order option '" + order + "'.");
		}

		Supplier<List<Book>> repoMethod = map.get(sortBy);
		if (repoMethod == null) {
			throw new RuntimeException("Can't sort results! Invalid sortBy option '" + sortBy + "'.");
		}

		return repoMethod.get();
	}

	@Override
	public Book getBookById(int id) {
		Book book = bookRepo.findById(id).orElse(null);
		if (book == null) {
			throw new RuntimeException("Can't fetch! Book with Id=" + id + " doesn't exist.");
		}
		return book;
	}

	@Override
	public Integer createBook(Book book) {
		if (bookRepo.findById(book.getId()).isPresent()) {
			throw new RuntimeException("Can't create! Book with Id=" + book.getId() + " already exists.");
		}
		Book createdBook = bookRepo.save(book);
		return createdBook.getId();
	}

	@Override
	public Integer updateBook(Book book) {
		if (!bookRepo.findById(book.getId()).isPresent()) {
			throw new RuntimeException("Can't update! Book with Id=" + book.getId() + " doesn't exist.");
		}
		Book updatedBook = bookRepo.save(book);
		return updatedBook.getId();
	}

	@Override
	public Integer deleteBookById(int id) {
		if (!bookRepo.findById(id).isPresent()) {
			throw new RuntimeException("Can't delete! Book with Id=" + id + " doesn't exist.");
		}
		bookRepo.deleteById(id);
		return id;
	}

	@Override
	public List<Book> searchBooksByName(String name) {
		if (name == null) {
			throw new RuntimeException("Can't search! No search query provided for 'name'.");
		}
		return bookRepo.searchBooksByName(name);
	}

	@Override
	public Integer patchBook(Integer id, Map<String, Object> map) {
		Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book not found for Id=" + id));
		for (String field : map.keySet()) {
			switch (field) {
			case "name":
				book.setName((String) map.get(field));
				break;
			case "category":
				book.setCategory((String) map.get(field));
				break;
			case "author":
				book.setAuthor((String) map.get(field));
				break;
			case "price":
				book.setPrice((Double) map.get(field));
				break;
			default:
				throw new RuntimeException("Unknown property '" + field + "'");
			}
		}
		bookRepo.save(book);
		return id;
	}

}
