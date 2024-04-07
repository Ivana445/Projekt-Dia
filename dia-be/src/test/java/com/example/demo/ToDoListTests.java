package com.example.demo;


import com.example.demo.Perzistent.ToDoListRepository;
import com.example.demo.Service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest({ToDoListController.class, UserController.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
public class ToDoListTests {

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @MockBean
    private ToDoListService toDoListService;

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
    public void testPostToDoList() throws Exception {
        when(toDoListService.postToDoList(any(),any())).thenAnswer(invocation -> {
            Long id = 1L;
            return id;
        });

        ToDoListDTO dto = new ToDoListDTO();
        dto.setName("Moj To-Do List");
        dto.setDeadline(new Date());

        //POST request
        mockMvc.perform(
                        post("/api/todolist")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        requestFields(
                                fieldWithPath("id").ignored(),
                                fieldWithPath("name").description("nazov to-do listu"),
                                fieldWithPath("deadline").description("datum dokoncenia")
                        )
                    )
                );

        verify(toDoListService, times(1)).postToDoList(any(), any());
    }

    @Test
    public void testGetToDoListPodlaId() throws Exception{
        when(toDoListService.getToDoListPodlaId(any())).thenReturn(
                new ToDoListDTO(1L, "moj to-do list", new Date())
        );

        mockMvc.perform(
                        get("/api/todolist/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                pathParameters(
                                        parameterWithName("id").description("to-do list id")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("to-do list id"),
                                        fieldWithPath("name").description("nazov to do listu"),
                                        fieldWithPath("deadline").description("datum dokoncenia")
                                )
                        )
                )
                .andReturn();

        verify(toDoListService, times(1)).getToDoListPodlaId(any());

    }
    @Test
    public void testPutToDoList() throws Exception{
        doNothing().when(toDoListService).putToDoList(any(),any(ToDoListDTO.class));

        ToDoListDTO toDoListDTO = new ToDoListDTO();
        toDoListDTO.setName("Moj to-do list");
        toDoListDTO.setDeadline(new Date());

        mockMvc.perform(put("/api/todolist/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(toDoListDTO))
        ).andExpect(status().isOk());

        verify(toDoListService, times(1)).putToDoList(eq(1L), any(ToDoListDTO.class));
    }
    @Test
    public void testDeleteToDoList() throws Exception{
        doNothing().when(toDoListService).deleteToDoList(any());

        mockMvc.perform(
                        delete("/api/todolist/{id}", 1)
                )
                .andExpect(status().isOk());

        verify(toDoListService, times(1)).deleteToDoList(any());
    }
}