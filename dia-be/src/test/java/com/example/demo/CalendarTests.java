package com.example.demo;

import com.example.demo.Service.CalendarDTO;
import com.example.demo.Service.CalendarService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest({CalendarController.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
public class CalendarTests {

    @Autowired
    private ObjectMapper mapper;
    private MockMvc mockMvc;
    @MockBean
    private CalendarService calendarService;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void testPostCalendar() throws Exception {
        when(calendarService.postCalendar(any())).thenAnswer(invocation -> {
            Long id = 1L;
            return id;
        });

        CalendarDTO dto = new CalendarDTO();

        mockMvc.perform(
                        post("/api/calendar")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                requestFields(
                                        fieldWithPath("id").description("calendar id")
                                )
                        )
                );

        verify(calendarService, times(1)).postCalendar(any());
    }

    @Test
    public void testGetCalendar() throws Exception{
        when(calendarService.getCalendarPodlaId(any())).thenReturn(
                new CalendarDTO(1L)
        );

        mockMvc.perform(
                        get("/api/calendar/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                pathParameters(
                                        parameterWithName("id").description("calendar id")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("calendar id")
                                )
                        )
                )
                .andReturn();

        verify(calendarService, times(1)).getCalendarPodlaId(any());
    }

    @Test
    public void testDeleteCalendar() throws Exception{
        doNothing().when(calendarService).deleteCalendar(any());

        mockMvc.perform(
                    delete("/api/calendar/{id}", 1)
                )
                .andExpect(status().isOk());

        verify(calendarService, times(1)).deleteCalendar(any());
    }
}

