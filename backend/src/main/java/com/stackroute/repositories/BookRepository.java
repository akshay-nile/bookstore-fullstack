package com.stackroute.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

	public List<Book> findByOrderByNameAsc();

	public List<Book> findByOrderByNameDesc();

	public List<Book> findByOrderByCategoryAsc();

	public List<Book> findByOrderByCategoryDesc();

	public List<Book> findByOrderByAuthorAsc();

	public List<Book> findByOrderByAuthorDesc();

	public List<Book> findByOrderByPriceAsc();

	public List<Book> findByOrderByPriceDesc();

	public List<Book> findByOrderByIdAsc();

	public List<Book> findByOrderByIdDesc();

	@Query("SELECT b FROM Book b WHERE b.name LIKE concat('%',?1,'%')")
	public List<Book> searchBooksByName(String name);
}
