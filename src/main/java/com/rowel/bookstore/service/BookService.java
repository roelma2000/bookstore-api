package com.rowel.bookstore.service;

import com.rowel.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book createNewBook(Book book);
    Book editBook(Book book);
    void deleteBook(String id);
    List<Book> getAllBooks();
    Optional<Book> getBookById(String id);
    Book updateBookInfo(String id, Book bookUpdateDTO);
}
