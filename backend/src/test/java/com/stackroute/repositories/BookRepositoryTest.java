package com.stackroute.repositories;

import static com.stackroute.SharedUtils.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stackroute.models.Book;

@SpringBootTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepo;

	@Test
	public void testingSortByPriceShouldReturnSortedBooksByPrice() {
		// testing for ascending order
		List<Book> booksRecieved = bookRepo.findByOrderByPriceAsc();
		List<Book> booksSortedByPrice = booksRecieved.stream().sorted(byPrice).collect(Collectors.toList());
		assertStreamEquals(booksRecieved, booksSortedByPrice, b -> b.getPrice());

		// testing for descending order
		booksRecieved = bookRepo.findByOrderByPriceDesc();
		Collections.reverse(booksSortedByPrice);
		assertStreamEquals(booksRecieved, booksSortedByPrice, b -> b.getPrice());
	}

	@Test
	public void testingSortByNameShouldReturnSortedBooksByName() {
		// testing for ascending order
		List<Book> booksRecieved = bookRepo.findByOrderByNameAsc();
		List<Book> booksSortedByName = booksRecieved.stream().sorted(byName).collect(Collectors.toList());
		assertStreamEquals(booksRecieved, booksSortedByName, b -> b.getName());

		// testing for descending order
		booksRecieved = bookRepo.findByOrderByNameDesc();
		Collections.reverse(booksSortedByName);
		assertStreamEquals(booksRecieved, booksSortedByName, b -> b.getName());
	}

	@Test
	public void testingSortByCategoryShouldReturnSortedBooksByCategory() {
		// testing for ascending order
		List<Book> booksRecieved = bookRepo.findByOrderByCategoryAsc();
		List<Book> booksSortedByCategory = booksRecieved.stream().sorted(byCategory).collect(Collectors.toList());
		assertStreamEquals(booksRecieved, booksSortedByCategory, b -> b.getCategory());

		// testing for descending order
		booksRecieved = bookRepo.findByOrderByCategoryDesc();
		Collections.reverse(booksSortedByCategory);
		assertStreamEquals(booksRecieved, booksSortedByCategory, b -> b.getCategory());
	}

	@Test
	public void testingSortByAuthorShouldReturnSortedBooksByAuthor() {
		// testing for ascending order
		List<Book> booksRecieved = bookRepo.findByOrderByAuthorAsc();
		List<Book> booksSortedByAuthor = booksRecieved.stream().sorted(byAuthor).collect(Collectors.toList());
		assertStreamEquals(booksRecieved, booksSortedByAuthor, b -> b.getAuthor());

		// testing for descending order
		booksRecieved = bookRepo.findByOrderByAuthorDesc();
		Collections.reverse(booksSortedByAuthor);
		assertStreamEquals(booksRecieved, booksSortedByAuthor, b -> b.getAuthor());
	}

	@Test
	public void testingSortByIdShouldReturnSortedBooksById() {
		// testing for ascending order
		List<Book> booksRecieved = bookRepo.findByOrderByIdAsc();
		List<Book> booksSortedById = booksRecieved.stream().sorted(byId).collect(Collectors.toList());
		assertStreamEquals(booksRecieved, booksSortedById, b -> b.getId());

		// testing for descending order
		booksRecieved = bookRepo.findByOrderByIdDesc();
		Collections.reverse(booksSortedById);
		assertStreamEquals(booksRecieved, booksSortedById, b -> b.getId());
	}

	@Test
	public void givenSearchByNameQueryShouldReturnMatchedResults() {
		List<Book> booksRecieved = bookRepo.searchBooksByName("of");
		List<Book> booksHavingOfInName = booksRecieved.stream().filter(b -> b.getName().toLowerCase().contains("of"))
				.collect(Collectors.toList());
		assertStreamEquals(booksRecieved, booksHavingOfInName, b -> b.getName());
	}

}
