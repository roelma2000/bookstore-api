package com.rowel.bookstore.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    private String id; //Spring Data MongoDB automatically maps this field to the _id field in MongoDB documents
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Publisher is required")
    private String publisher;

    @NotNull(message = "Published date is required")
    private Date publishedDate;

    @NotNull(message = "Stock quantity is required")
    @PositiveOrZero(message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @NotBlank(message = "Cover image URL is required")
    private String coverImageUrl;
}
