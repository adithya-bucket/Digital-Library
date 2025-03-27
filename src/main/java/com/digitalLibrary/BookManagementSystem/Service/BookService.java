package com.digitalLibrary.BookManagementSystem.Service;

import com.digitalLibrary.BookManagementSystem.Model.Book;
import com.digitalLibrary.BookManagementSystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    /**
     * Retrieves all books from the book table.
     *
     * @return List of books.
     */
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    /**
     * Adds a new book to the book table.
     *
     * @param book The book to add.
     * @return The saved book.
     */
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }


    /**
     * Retrieves a book by its ID.
     *
     * @param id The book ID.
     * @return The found book.
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }


    /**
     * Searches for books by title.
     *
     * @param title The title keyword.
     * @return List of books matching the search criteria.
     */
    public Page<Book> searchBooksByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }


    /**
     * Updates an existing book .
     *
     * @param id          The book ID to update.
     * @param bookDetails The new details of the book.
     * @return The updated book entity.
     */
    public Book updateBook(Long id, Book bookDetails) {

        return bookRepository.findById(id).map(book -> {
            // Update only non-null fields to prevent overwriting existing data
            if (bookDetails.getTitle() != null && !bookDetails.getTitle().isBlank()) {
                book.setTitle(bookDetails.getTitle());
            }
            if (bookDetails.getAuthor() != null && !bookDetails.getAuthor().isBlank()) {
                book.setAuthor(bookDetails.getAuthor());
            }
            if (bookDetails.getGenre() != null) {
                book.setGenre(bookDetails.getGenre());
            }
            book.setAvailable(bookDetails.isAvailable());

            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id The book ID.
     */
    public void deleteBook(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));

        bookRepository.deleteById(id);
    }

}
