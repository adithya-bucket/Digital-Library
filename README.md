# Digital Library Book Management System
 The spring boot application for managing book in digital

A Spring Boot application for managing books in a digital library, supporting CRUD operations, search, and pagination.

## Features  
- Add, update, delete, and view books  
- Pagination and search functionality  
- User-friendly UI built with Thymeleaf  
- PostgreSQL database integration  
- REST API for external interactions  

## Technologies Used  
- Java Spring Boot
- Spring Data JPA
- PostgreSQL  
- Thymeleaf (for UI)  
- Maven  

## API Endpoints  
| Method | Endpoint                  | Description                  |  
|--------|---------------------------|------------------------------|  
| GET    | `/api/books/get`          | Get all books (paginated)    |  
| GET    | `/api/books/get/{id}`     | Get book by ID               |  
| GET    | `/api/books/search`       | Search books (paginated)     |  
| POST   | `/api/books/add`          | Add a new book               |  
| PUT    | `/api/books/edit/{id}`    | Update book details          |  
| DELETE | `/api/books/delete/{id}`  | Delete a book                |  


## Setup Instructions  
1. Clone the repository:  
   ```sh
   git clone https://github.com/adithya-bucket/Digital-Library.git
   
## Database setup
1. Create Database
   CREATE DATABASE digitalLibrary;
2.Configure Application Properties
Rename application.sample.properties to application.properties and update it with your credentials.
3. Build and Run the Application
   Using Maven
    ```sh
    mvn clean install  
mvn spring-boot:run

The application will be available at:
UI Entry Point: http://localhost:8080/
API Base URL: http://localhost:8080/api/books
