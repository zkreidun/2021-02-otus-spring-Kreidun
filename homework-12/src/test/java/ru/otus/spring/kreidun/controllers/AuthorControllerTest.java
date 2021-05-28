package ru.otus.spring.kreidun.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.otus.spring.kreidun.repositories.AuthorRepository;
import ru.otus.spring.kreidun.repositories.BookRepository;
import ru.otus.spring.kreidun.repositories.UserRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ComponentScan({"ru.otus.spring.kreidun.services"})
@WebMvcTest(AuthorController.class)
@DisplayName("Тест AuthorController")
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private UserRepository userRepository;


    @WithMockUser(
            username = "admin",
            password = "admin1"
    )
    @Test
    @DisplayName("Получая информацию о пользователе, должен возвращать статус ОК")
    public void find_login_shouldSucceedWith200() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/authors")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Не получая информацию о пользователе, должен делать редирект на форму логина")
    public void find_nologin_redirect_to_loginform() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/authors"))
                .andExpect(status().is3xxRedirection());
    }

}