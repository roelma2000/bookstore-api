package com.rowel.bookstore.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String userId;
    private List<OrderedBook> orderedBooks;
    private Double totalPrice;
    private Date orderDate;
    private String shippingAddress;
    private String orderStatus;

    // OrderedBook sub-document
    public static class OrderedBook {
        private String bookId;
        private Integer quantity;
    }
}
