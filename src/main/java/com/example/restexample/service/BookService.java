package com.example.restexample.service;

import com.example.restexample.model.Book;
import com.example.restexample.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        if (book == null) {
            throw new RuntimeException("Book can't be null");
        }
        return bookRepository.save(book);
    }

}
