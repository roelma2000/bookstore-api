package com.rowel.bookstore.service;

import com.rowel.bookstore.model.Book;
import com.rowel.bookstore.repository.BooksRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BooksRepository booksRepository;
    @Override
    public Book createNewBook(Book book) {
        return booksRepository.save(book);
    }

    @Override
    public Book editBook(Book book) {
        return booksRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        booksRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(String id) {
        return booksRepository.findById(id);
    }

    @Override
    public Book updateBookInfo(String id, Book bookUpdateDTO) {
        Optional<Book> booktemp = booksRepository.findById(id);
        if(!booktemp.isPresent()){
            throw new EntityNotFoundException("Book not found with id: " + id);
        }
        Book book = booktemp.get();
        if(bookUpdateDTO.getAuthor() !=null){
            book.setAuthor(bookUpdateDTO.getAuthor());
        }
        if(bookUpdateDTO.getTitle() !=null){
            book.setTitle(bookUpdateDTO.getTitle());
        }
        if(bookUpdateDTO.getIsbn() !=null){
            book.setIsbn(bookUpdateDTO.getIsbn());
        }
        if(bookUpdateDTO.getCategory() !=null){
            book.setCategory(bookUpdateDTO.getCategory());
        }
        if(bookUpdateDTO.getPrice() !=null){
            book.setPrice(bookUpdateDTO.getPrice());
        }
        if(bookUpdateDTO.getDescription() !=null){
            book.setDescription(bookUpdateDTO.getDescription());
        }
        if(bookUpdateDTO.getPublisher() !=null){
            book.setPublisher(bookUpdateDTO.getPublisher());
        }
        if(bookUpdateDTO.getPublishedDate() !=null){
            book.setPublishedDate(bookUpdateDTO.getPublishedDate());
        }
        if(bookUpdateDTO.getStockQuantity() !=null){
            book.setStockQuantity(bookUpdateDTO.getStockQuantity());
        }
        if(bookUpdateDTO.getCoverImageUrl() !=null){
            book.setCoverImageUrl(bookUpdateDTO.getCoverImageUrl());
        }
        return booksRepository.save(book);
    }
}
