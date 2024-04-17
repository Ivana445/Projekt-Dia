package com.example.demo;

import com.example.demo.Perzistent.UserEntity;
import com.example.demo.Perzistent.UserRepository;
import com.example.demo.Service.UserDTO;
import com.example.demo.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest({UserController.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")

public class UserTests {
    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }
    @Test
    public void testUserRegistration_Success() throws Exception {
        when(userService.registerUser(any(UserDTO.class))).thenReturn(1L);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("TestPassword123");

        mockMvc.perform(
                        post("/api/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userDTO))
                )
                .andExpect(status().isOk())
                .andDo(System.out::println); // Tisk odpovědi pro ladění

        verify(userService, times(1)).registerUser(any(UserDTO.class));
    }

    @Test
    public void testUserRegistration_Failure() throws Exception {
        when(userService.registerUser(any(UserDTO.class))).thenReturn(null);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("existingUser");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("TestPassword123");

        mockMvc.perform(
                        post("/api/registration")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userDTO))
                )
                .andExpect(status().isOk())
                .andDo(System.out::println);

        verify(userService, times(1)).registerUser(any(UserDTO.class));
    }

    @Test
    public void testLogin_Success() throws Exception {
        when(userService.PostLogin(any(UserDTO.class))).thenReturn(anyString());

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setPassword("TestPassword123");

        mockMvc.perform(
                        post("/api/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userDTO))
                )
                .andExpect(status().isOk())
                .andDo(System.out::println);

        verify(userService, times(1)).PostLogin(any(UserDTO.class));
    }

    @Test
    public void testLogin_Failure() throws Exception {
        when(userService.PostLogin(any(UserDTO.class))).thenReturn(null);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("nonExistingUser");
        userDTO.setPassword("TestPassword123");

        mockMvc.perform(
                        post("/api/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(userDTO))
                )
                .andExpect(status().isOk())
                .andDo(System.out::println);

        verify(userService, times(1)).PostLogin(any(UserDTO.class));
    }


}
