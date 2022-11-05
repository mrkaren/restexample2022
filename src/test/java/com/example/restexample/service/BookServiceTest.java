package com.example.restexample.service;

import com.example.restexample.model.Author;
import com.example.restexample.model.Book;
import com.example.restexample.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Test
    void save() {
        Book book = Book.builder()
                .id(1)
                .author(new Author())
                .description("asaa")
                .price(33)
                .title("title")
                .build();
        when(bookRepository.save(any())).thenReturn(book);

        bookService.save(Book.builder()
                .author(new Author())
                .description("asaa")
                .price(33)
                .title("title")
                .build());
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void saveNull() {
        Book book = Book.builder()
                .id(1)
                .author(new Author())
                .description("asaa")
                .price(33)
                .title("title")
                .build();
        when(bookRepository.save(any())).thenReturn(book);

        assertThrows(RuntimeException.class, ()-> {
            bookService.save(null);
        });

        verify(bookRepository, times(0)).save(any());
    }
}