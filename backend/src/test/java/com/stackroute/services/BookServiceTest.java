package com.stackroute.services;

import static com.stackroute.SharedUtils.assertStreamEquals;
import static com.stackroute.SharedUtils.byId;
import static com.stackroute.SharedUtils.byPrice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stackroute.models.Book;

@SpringBootTest
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	private static Book book;
	private static int bookId;

	@BeforeEach
	public void setUp() {
		book = new Book(-1, "Dummy Book", "Dummy Author", "Dummy Category", 777.77);
		bookId = bookService.createBook(book);
		book.setId(bookId);
	}

	@AfterEach
	public void tearDown() {
		bookService.deleteBookById(bookId);
		book = null;
		bookId = -1;
	}

	@Test
	public void givenSortByPriceShouldReturnSortedBooksByPrice() {
		List<Book> booksRecieved = bookService.getAllBooks("price", null);
		List<Book> booksSortedByPrice = booksRecieved.stream().sorted(byPrice).collect(Collectors.toList());
		assertStreamEquals(booksRecieved, booksSortedByPrice, b -> b.getPrice());
	}

	@Test
	public void givenOrderByDescShouldReturnReverseSortedBooksById() {
		List<Book> booksRecieved = bookService.getAllBooks(null, "desc");
		List<Book> booksReverseOrdered = booksRecieved.stream().sorted(byId).collect(Collectors.toList());
		Collections.reverse(booksReverseOrdered);
		assertStreamEquals(booksRecieved, booksReverseOrdered, b -> b.getId());
	}

	@Test
	public void givenInvalidInputShouldThrowRuntimeException() {
		assertThrows(RuntimeException.class, () -> bookService.getAllBooks("abc", "xyz"));
	}

	@Test
	public void givenSearchByNameQueryShouldReturnMatchedResults() {
		List<Book> booksRecieved = bookService.searchBooksByName("The");
		List<Book> booksHavingTheInName = booksRecieved.stream().filter(b -> b.getName().toLowerCase().contains("the"))
				.collect(Collectors.toList());
		assertStreamEquals(booksRecieved, booksHavingTheInName, b -> b.getName());
	}

	@Test
	public void givenNullInputShouldThrowRuntimeException() {
		assertThrows(RuntimeException.class, () -> bookService.searchBooksByName(null));
	}

	@Test
	public void testingBookRetriveAndUpdateFunctionalities() {
		book.setCategory("Updated Category");
		assertTrue(bookId == bookService.updateBook(book));
		assertEquals(book, bookService.getBookById(bookId));
	}

	@Test
	public void givenNonExistingIdShouldThrowRuntimeException() {
		assertThrows(RuntimeException.class, () -> bookService.getBookById(-1));
	}

	@Test
	public void givenExistingBookToCreateShouldRefuseToCreateThatBook() {
		assertThrows(RuntimeException.class, () -> bookService.createBook(book));
	}

	@Test
	public void givenNonExistingBookToUpdateShouldRefuseToUpdateThatBook() {
		book.setId(-1);
		assertThrows(RuntimeException.class, () -> bookService.updateBook(book));
	}

}
