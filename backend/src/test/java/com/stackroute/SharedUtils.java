package com.stackroute;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import com.stackroute.models.Book;

public interface SharedUtils {

	Comparator<Book> byAuthor = (a, b) -> a.getAuthor().toLowerCase().compareTo(b.getAuthor().toLowerCase());
	Comparator<Book> byCategory = (a, b) -> a.getCategory().toLowerCase().compareTo(b.getCategory().toLowerCase());
	Comparator<Book> byName = (a, b) -> a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
	Comparator<Book> byPrice = (a, b) -> a.getPrice().compareTo(b.getPrice());
	Comparator<Book> byId = (a, b) -> Integer.compare(a.getId(), b.getId());

	static void assertStreamEquals(List<Book> a, List<Book> b, Function<Book, Object> c) {
		Iterator<Object> itrA = a.stream().map(c).iterator();
		Iterator<Object> itrB = b.stream().map(c).iterator();
		while (itrA.hasNext() && itrB.hasNext()) {
			assertEquals(itrA.next(), itrB.next());
		}
	}
}
