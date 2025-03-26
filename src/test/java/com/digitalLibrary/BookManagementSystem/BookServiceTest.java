package com.digitalLibrary.BookManagementSystem;

import com.digitalLibrary.BookManagementSystem.Model.Book;
import com.digitalLibrary.BookManagementSystem.Repository.BookRepository;
import com.digitalLibrary.BookManagementSystem.Service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Unit test cases for BookService class.
 */
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        sampleBook = new Book(1L, "Java Basics", "John Doe", "Programming", true);
    }

    /**
     * Test case for retrieving all books.
     */
    @Test
    void getAllBooks_ShouldReturnBookList() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(sampleBook));
        List<Book> books = bookService.getAllBooks();
        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
    }

    /**
     * Test case for retrieving a book by ID.
     */
    @Test
    void getBookById_ShouldReturnBook_WhenBookExists() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        Book book = bookService.getBookById(1L);
        assertNotNull(book);
        assertEquals("Java Basics", book.getTitle());
    }

    /**
     * Test case for handling book not found scenario.
     */
    @Test
    void getBookById_ShouldThrowException_WhenBookNotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> bookService.getBookById(2L));
        assertEquals("Book not found with ID: 2", exception.getMessage());
    }

    /**
     * Test case for adding a book.
     */
    @Test
    void addBook_ShouldSaveBook() {
        when(bookRepository.save(sampleBook)).thenReturn(sampleBook);
        Book savedBook = bookService.addBook(sampleBook);
        assertNotNull(savedBook);
        assertEquals("Java Basics", savedBook.getTitle());
    }

    /**
     * Test case for updating a book.
     */
    @Test
    void updateBook_ShouldModifyExistingBook() {
        Book updatedDetails = new Book(null, "Advanced Java", "Jane Doe", "Technology", false);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedDetails);

        Book updatedBook = bookService.updateBook(1L, updatedDetails);
        assertNotNull(updatedBook);
        assertEquals("Advanced Java", updatedBook.getTitle());
    }

    /**
     * Test case for deleting a book.
     */
    @Test
    void deleteBook_ShouldRemoveBook_WhenBookExists() {
        lenient().when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(new Book(1L, "Java Basics", "John Doe", "Programming", true)));
        doNothing().when(bookRepository).deleteById(1L);

        assertDoesNotThrow(() -> bookService.deleteBook(1L));
        verify(bookRepository, times(1)).deleteById(1L);
    }



    /**
     * Test case for deleting a non-existing book.
     */
    @Test
    void deleteBook_ShouldThrowException_WhenBookNotFound() {
        lenient().when(bookRepository.existsById(2L)).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> bookService.deleteBook(2L));
        assertEquals("Book not found with ID: 2", exception.getMessage());
    }

}
