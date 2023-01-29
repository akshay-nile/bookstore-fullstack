import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/Book';
import { BookService } from '../../services/BookService';

@Component({
  selector: 'bookstore',
  templateUrl: './bookstore.component.html',
  styleUrls: ['./bookstore.component.css'],
  providers: [BookService]
})
export class BookstoreComponent implements OnInit {

  books: Book[] = [];

  editBook: Book = { id: -1, name: "", author: "", category: "", price: "" };
  addBook: Book = { id: -1, name: "", author: "", category: "", price: "" };

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(
      books => this.books = books,
      error => alert("Could not fetch the list of available books !")
    );
  }

  areBookFieldsValid(book: Book): boolean {
    if (book.id == -1 && this.books.map(b => b.name.toLocaleLowerCase()).includes(book.name.trim().toLocaleLowerCase())) {
      return false;
    }
    if (!book.name.length || !book.author.length || !book.category.length || book.price < 1) {
      return false;
    }
    return true;
  }

  clearBookFields() {
    this.editBook = { id: -1, name: "", author: "", category: "", price: "" };
    this.addBook = { id: -1, name: "", author: "", category: "", price: "" };
  }

  createBook() {
    this.bookService.createBook(this.addBook).subscribe(createdId => {
      if (createdId > 0) {
        this.addBook.id = createdId;
        this.books.push(this.addBook);
        this.clearBookFields();
      } else {
        alert("Book is not added !");
      }
    });
  }

  deleteBookById(id: number) {
    if (confirm("Do you really want to delete this book?")) {
      this.bookService.deleteBookById(id).subscribe(deletedId => {
        if (deletedId === id) {
          this.books = this.books.filter(b => b.id !== deletedId);
        } else {
          alert("Book is not deleted !");
        }
      });
    }
  }

  makeBookEditable(book: Book) {
    this.editBook.id = book.id;
    this.editBook.name = book.name;
    this.editBook.author = book.author;
    this.editBook.category = book.category;
    this.editBook.price = book.price;
  }

  updateBook(book: Book) {
    this.bookService.updateBook(this.editBook).subscribe(updatedId => {
      if (updatedId === book.id) {
        book.name = this.editBook.name;
        book.author = this.editBook.author;
        book.category = this.editBook.category;
        book.price = this.editBook.price;
        this.clearBookFields();
      } else {
        alert("Book is not updated !");
      }
    });
  }

}
