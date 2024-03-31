package com.rowel.bookstore.repository;

import com.rowel.bookstore.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends MongoRepository<Book, String> {
}
