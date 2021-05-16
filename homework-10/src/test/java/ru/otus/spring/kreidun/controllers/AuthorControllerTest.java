package ru.otus.spring.kreidun.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.spring.kreidun.repositories.AuthorRepository;


@WebMvcTest(ru.otus.spring.kreidun.controllers.AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AuthorRepository repository;

    @Test
    void getListAuthors() throws Exception {

        ResultActions result = this.mvc.perform(MockMvcRequestBuilders.get("/authors"));

        result.andExpect(MockMvcResultMatchers.view().name("listAuthors"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("authors"));
    }
}