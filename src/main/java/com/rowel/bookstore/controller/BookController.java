package com.rowel.bookstore.controller;

import com.rowel.bookstore.model.Book;
import com.rowel.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createNewBook(@Valid @RequestBody Book book){
        return new ResponseEntity<>(bookService.createNewBook(book), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Book> editBook(@Valid @RequestBody Book book){
        return  new ResponseEntity<>(bookService.editBook(book),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id){
        bookService.deleteBook(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public  ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable String id){
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBookInfo(@PathVariable String id, @RequestBody Book bookDTO){
        Book updatedBook = bookService.updateBookInfo(id,bookDTO);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

}
