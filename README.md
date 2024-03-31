
# Bookstore API Documentation

This document provides detailed information about the backend API for managing lists of books, accounts, orders, and reviews in the Bookstore application.

## Endpoints

### POST - Add Book

- **URL:** `http://localhost:8082/api/books`
- **Method:** `POST`
- **Body** (raw json):
```json
{
    "title": "Chocolate All Day",
    "author": "Steven Hodge",
    "isbn": "9780525612025",
    "category": "Cookbook",
    "price": 37.50,
    "description": "Calling all chocolate lovers! Its the bake book youve been waiting for, with over 100 rich, delicious, yet accessible recipes for baking with chocolate all day long.",
    "publisher": "Appetite by Random House",
    "publishedDate": "2023-10-17T00:00:00.000+00:00",
    "stockQuantity": 10,
    "coverImageUrl": "/images/chocolate.jpg"
}
```

### PUT - Edit a Book

- **URL:** `http://localhost:8082/api/books`
- **Method:** `PUT`
- **Body** (raw json):
```json
{
  "id": "65f753a08d56e9665a172f46",
  "title": "The Love Hypothesis",
  "author": "Ali Hazelwood",
  "isbn": "0593336828",
  "category": "Fiction",
  "price": 22.99,
  "description": "This is a sample description of the book.",
  "publisher": "Berkley",
  "publishedDate": "2021-09-17",
  "stockQuantity": 100,
  "coverImageUrl": "http://example.com/cover.jpg"
}
```

### GET - Get All Books

- **URL:** `http://localhost:8082/api/books`
- **Method:** `GET`

### GET - Get a Book

- **URL:** `http://localhost:8082/api/books/6608ad93adfa157b2db9d948`
- **Method:** `GET`

### DELETE - Delete a Book

- **URL:** `http://localhost:8082/api/books/6608afdf064e5848364727c0`
- **Method:** `DELETE`

### PATCH - Update Info of a Book

- **URL:** `http://localhost:8082/api/books/65f901f8f305474619b47866`
- **Method:** `PATCH`
- **Body** (raw json):
```json
{
  "price": 24.99
}
```
