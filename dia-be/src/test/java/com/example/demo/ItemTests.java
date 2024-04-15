package com.example.demo;


import com.example.demo.Perzistent.ItemRepository;
import com.example.demo.Service.*;
import com.example.demo.Service.ItemService;
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
@WebMvcTest({ItemController.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
public class ItemTests {

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    //todo testPostItem

    @Test
    public void testGetItemById() throws Exception{
        when(itemService.getItemById(any())).thenReturn(
                new ItemDTO(1L,"task", "popis")
        );

        mockMvc.perform(
                        get("/api/item/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                pathParameters(
                                        parameterWithName("id").description("item id")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("item id"),
                                        fieldWithPath("name").description("nazov tasku"),
                                        fieldWithPath("popis").description("popis tasku")
                                )
                        )
                )
                .andReturn();

        verify(itemService, times(1)).getItemById(any());

    }
    @Test
    public void testPutItem() throws Exception{
        doNothing().when(itemService).putItem(any(),any(ItemDTO.class));

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName("moj task");
        itemDTO.setPopis("popis tasku");

        mockMvc.perform(put("/api/item/{id}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(itemDTO))
        ).andExpect(status().isOk());

        verify(itemService, times(1)).putItem(eq(1L), any(ItemDTO.class));
    }
    @Test
    public void testDeleteItem() throws Exception{
        doNothing().when(itemService).deleteItem(any());

        mockMvc.perform(
                        delete("/api/item/{id}", 1)
                )
                .andExpect(status().isOk());

        verify(itemService, times(1)).deleteItem(any());
    }
}
