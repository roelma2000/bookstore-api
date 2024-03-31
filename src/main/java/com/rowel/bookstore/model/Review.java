package com.rowel.bookstore.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String bookId;
    private String userId;
    private Integer rating;
    private String comment;
    private Date date;
}
