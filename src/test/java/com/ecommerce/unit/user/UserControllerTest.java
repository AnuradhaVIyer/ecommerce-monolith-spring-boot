package com.ecommerce.unit.user;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ecommerce.controller.UserController;
import com.ecommerce.model.Users;
import com.ecommerce.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testRegisterUser() throws Exception {
        Users mockUser = new Users("Anna K", "anna@example.com", "password123", "Address1", "1234567890", "USER" );
   					 

        // Mock userService to return the user object
        when(userService.registerUser(mockUser))
                .thenReturn(mockUser);

        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Anna K"))
                .andExpect(jsonPath("$.email").value("anna@example.com"))
                .andReturn();

        // Log the response for debugging
        //System.out.println("Response: " + result.getResponse().getContentAsString());
        verify(userService).registerUser(mockUser);
    }

    @Test
    void testLogin() throws Exception {
        Users mockUser = new Users("Anna K", "anna@example.com", "password123", "Address1", "1234567890", "USER");
;

        when(userService.loginUser("anna@example.com", "password123"))
                .thenReturn(mockUser);

        mockMvc.perform(post("/api/users/login")
        		.param("name", "Anna K")
        		.param("username", "AnnaK")
                .param("email", "anna@example.com")
                .param("password", "password123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Anna K"))
                .andExpect(jsonPath("$.email").value("anna@example.com"));

        verify(userService).loginUser("anna@example.com", "password123");
    }

    

    
    @Test
    void testLogin_UserNotFound() throws Exception {
        when(userService.loginUser("invalid@example.com", "wrongPass"))
                .thenThrow(new IllegalArgumentException("Invalid email or password"));

        mockMvc.perform(post("/api/users/login")
                .param("email", "invalid@example.com")
                .param("password", "wrongPass")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid email or password"));

        verify(userService).loginUser("invalid@example.com", "wrongPass");
    }
}

