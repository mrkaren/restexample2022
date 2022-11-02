package com.example.restexample.endpoint;

import com.example.restexample.dto.AuthorResponseDto;
import com.example.restexample.dto.CreateAuthorDto;
import com.example.restexample.mapper.AuthorMapper;
import com.example.restexample.model.Author;
import com.example.restexample.repository.AuthorRepository;
import com.example.restexample.security.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorEndpoint {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    Logger log = LoggerFactory.getLogger(AuthorEndpoint.class.getName());

    @Operation(
            operationId = "getAllAuthors",
            summary = "Get all authors",
            description = "get all authors description"
    )
    @GetMapping("/authors")
    public List<AuthorResponseDto> getAllAuthors(@AuthenticationPrincipal CurrentUser currentUser) {
        log.info("endpoint /authors called by {}", currentUser.getUser().getEmail());
        return authorMapper.map(authorRepository.findAll());
    }

    @PostMapping("/authors")
    public ResponseEntity<?> createAuthor(@RequestBody CreateAuthorDto createAuthorDto) {
        Author savedAuthor = authorRepository.save(authorMapper.map(createAuthorDto));
        return ResponseEntity.ok(savedAuthor);
    }

}
