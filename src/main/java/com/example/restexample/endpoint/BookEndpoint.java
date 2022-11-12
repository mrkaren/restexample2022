package com.example.restexample.endpoint;

import com.example.restexample.exception.EntityNotFoundException;
import com.example.restexample.model.Book;
import com.example.restexample.repository.BookRepository;
import com.example.restexample.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookEndpoint {

    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;

    private final BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {

        List<Book> all = bookRepository.findAll();
        if (!all.isEmpty()) {
            ResponseEntity<HashMap> currency = restTemplate.getForEntity("https://cb.am/latest.json.php?currency=USD", HashMap.class);
            HashMap<String, String> hashMap = currency.getBody();
            if (!hashMap.isEmpty()) {
                double usdCurrency = Double.parseDouble(hashMap.get("USD"));
                if (usdCurrency > 0) {
                    for (Book book : all) {
                        double price = book.getPrice() / usdCurrency;
                        DecimalFormat df = new DecimalFormat("#.##");
                        book.setPrice(Double.valueOf(df.format(price)));
                    }
                }

            }
        }
        return all;
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) throws EntityNotFoundException {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody @Valid Book book) {
        bookRepository.save(book);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        if (book.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
