package com.example.restexample.endpoint;

import com.example.restexample.model.Author;
import com.example.restexample.model.Gender;
import com.example.restexample.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthorEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AuthorRepository authorRepository;

    private static final String BASE_URL = "http://localhost:8080";

    @BeforeEach
    public void beforeAll(){
        authorRepository.deleteAll();
    }

    @Test
//    @WithMockUser(username = "poxos", roles = "ADMIN", password = "password")
    public void testGetAllAuthorsEndpoint() throws Exception {
        addTestAuthors();
        mvc.perform(get(BASE_URL + "/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testAddAuthor() throws Exception {
        ObjectNode objectNode = new ObjectMapper().createObjectNode();
        objectNode.put("name", "poxos");
        objectNode.put("surname", "poxosyan");
        objectNode.put("email", "poxos11234@Mail.com");
        objectNode.put("gender", "MALE");
        //save author
        mvc.perform(post(BASE_URL + "/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectNode.toString()))
                .andExpect(status().isOk());

        //get all authors
        mvc.perform(get(BASE_URL + "/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }


    private void addTestAuthors() {
        authorRepository.save(Author.builder()
                .email("test1@mail.com")
                .gender(Gender.MALE)
                .surname("poxosyan")
                .name("poxos")
                .build());

        authorRepository.save(Author.builder()
                .email("test2@mail.com")
                .gender(Gender.MALE)
                .surname("petrosyan")
                .name("petros")
                .build());

    }
}
