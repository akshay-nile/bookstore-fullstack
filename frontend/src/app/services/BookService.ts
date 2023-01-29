import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Book } from '../models/Book';

@Injectable()
export class BookService {

  url = environment.baseUrl + "/api/book";
  headers = { 'headers': { 'Content-Type': 'application/json', 'Accept': 'application/json' } };

  constructor(private http: HttpClient) { }

  getAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.url + "/get", this.headers);
  }

  getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(this.url + "/get/" + id, this.headers);
  }

  deleteBookById(id: number): Observable<number> {
    return this.http.delete<number>(this.url + "/delete/" + id, this.headers);
  }

  createBook(book: Book): Observable<number> {
    return this.http.post<number>(this.url + "/create", book, this.headers);
  }

  updateBook(book: Book): Observable<number> {
    return this.http.put<number>(this.url + "/update", book, this.headers);
  }

}
