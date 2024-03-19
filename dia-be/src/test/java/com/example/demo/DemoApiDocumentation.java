package com.example.demo;


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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest({DemoController.class, CustomerController.class, PublisherController.class})
@AutoConfigureRestDocs(outputDir = "/target/generated-snippets")
public class DemoApiDocumentation {
    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private PublisherService publisherService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }
    @Test
    public void testVytvorKnihu() throws Exception {
        when(bookService.vytvorKnihu(any())).thenAnswer(invocation -> {
            //BookDTO dto = invocation.getArgument(0); // Získání předaného argumentu
            Long id = 1L;
            return id;
        });

        BookDTO dto = new BookDTO();
        dto.setAutor("aaa");
        dto.setNazov("nnn");

        // Odeslání požadavku POST s BookDTO
        mockMvc.perform(
                        post("/api/books")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                requestFields(
                                        fieldWithPath("id").ignored(),
                                        fieldWithPath("autor").description("Autor knihy"),
                                        fieldWithPath("nazov").description("Nazov knihy")
                                )
                        )
                );


        verify(bookService, times(1)).vytvorKnihu(any());
    }


    @Test
    public void testZiskajKnihu() throws Exception {
        when(bookService.ZiskajKnihuPodlaId(any())).thenReturn(
                new BookDTO(1l, "nn", "aa")
        );

        mockMvc.perform(
                        get("/api/books/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                pathParameters(
                                        parameterWithName("id").description("id knihy")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("Id knihy"),
                                        fieldWithPath("autor").description("Autor knihy"),
                                        fieldWithPath("nazov").description("Nazov knihy")
                                )
                        )
                )
                .andReturn();

        verify(bookService, times(1)).ZiskajKnihuPodlaId(any());

    }
    @Test
    public void testUpravKnihu() throws Exception {
        doNothing().when(bookService).upravKnihu(anyLong(), any(BookDTO.class));

        BookDTO k = new BookDTO();
        k.setNazov("test");
        k.setAutor("jjj");
        mockMvc.perform(put("/api/books/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(k))
                )
                .andExpect(status().isOk());
        verify(bookService, times(1)).upravKnihu(eq(1L), any(BookDTO.class));

    }
    @Test
    public void testVymazKnihu() throws Exception {
        doNothing().when(bookService).vymazKnihu(anyLong());

        mockMvc.perform(delete("/api/books/{id}", 1))
                .andExpect(status().isOk());

        verify(bookService, times(1)).vymazKnihu(eq(1L));
    }

    @Test
    public void testVytvorCustomera() throws Exception {
        when(customerService.vytvorCustomera(any())).thenAnswer(invocation -> {
            //BookDTO dto = invocation.getArgument(0); // Získání předaného argumentu
            Long id = 1L;
            return id;
        });

        CustomerDTO dto = new CustomerDTO();
        dto.setMeno("aaa");
        dto.setKontakt("nnn");

        // Odeslání požadavku POST s BookDTO
        mockMvc.perform(
                        post("/api/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                requestFields(
                                        fieldWithPath("id").ignored(),
                                        fieldWithPath("meno").description("Meno customera"),
                                        fieldWithPath("kontakt").description("Kontakt na customera")
                                )
                        )
                );


        verify(customerService, times(1)).vytvorCustomera(any());
    }
    @Test
    public void testUpravCustomera() throws Exception {
        doNothing().when(customerService).upravCustomera(anyLong(), any(CustomerDTO.class));

        CustomerDTO k = new CustomerDTO();
        k.setMeno("test");
        k.setKontakt("jjj");
        mockMvc.perform(put("/api/customers/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(k))
                )
                .andExpect(status().isOk());
        verify(customerService, times(1)).upravCustomera(eq(1L), any(CustomerDTO.class));
    }

    @Test
    public void testZiskajCustomera() throws Exception {
        when(customerService.ZiskajCustomeraPodlaId(any())).thenReturn(
                new CustomerDTO("ddd", 1l, "aa")
        );

        mockMvc.perform(
                        get("/api/customers/{id}", 1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                pathParameters(
                                        parameterWithName("id").description("id customera")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("Id customera"),
                                        fieldWithPath("meno").description("meno customera"),
                                        fieldWithPath("kontakt").description("kontakt na customera")
                                )
                        )
                )
                .andReturn();

        verify(customerService, times(1)).ZiskajCustomeraPodlaId(any());
    }
    @Test
    public void testVymazCustomera() throws Exception {
        doNothing().when(customerService).vymazCustomera(anyLong());

        mockMvc.perform(delete("/api/customers/{id}", 1))
                .andExpect(status().isOk());

        verify(customerService, times(1)).vymazCustomera(eq(1L));
    }

    @Test
    public void testZoznamKnih() throws Exception {
        ArrayList<BookDTO> knihy = new ArrayList<>();
        BookDTO kniha1 = new BookDTO();
        kniha1.setNazov("Prva");
        knihy.add(kniha1);
        BookDTO kniha2 = new BookDTO();
        kniha2.setNazov("Druha");
        knihy.add(kniha2);

        when(bookService.zoznamKnih(any(String.class))).thenReturn(knihy);

        List<BookDTO> result = bookService.zoznamKnih("Prva");

        int count = 0;
        for (BookDTO kniha : result) {
            if (kniha.getNazov().equals("Prva")) {
                count++;
            }
        }
        assertEquals(1, count); // 1 kniha s hladanym nazvom
    }

    @Test
    public void testZoznamZakaznikov() {

        ArrayList<CustomerDTO> customers = new ArrayList<>();
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setMeno("Jano");
        customers.add(customer1);
        CustomerDTO customer2 = new CustomerDTO();
        customer2.setMeno("Anna");
        customers.add(customer2);
        CustomerDTO customer3 = new CustomerDTO();
        customer3.setMeno("Jano");
        customers.add(customer3);

        when(customerService.zoznamZakaznikov(any(String.class))).thenReturn(customers);

        List<CustomerDTO> result = customerService.zoznamZakaznikov("Jano");

        int count = 0;
        for (CustomerDTO customer : result) {
            if (customer.getMeno().equals("Jano")) {
                count++;
            }
        }
        assertEquals(2, count); // 2 zakaznici s hladanym menom
    }

    @Test
    public void testVytvorPublishera() throws Exception {
        when(publisherService.vytvorPublishera(any())).thenAnswer(invocation -> {
            Long id = 1L;
            return id;
        });

        PublisherDTO dto = new PublisherDTO();
        dto.setName("slniecko");
        dto.setAddress("bratislava");

        mockMvc.perform(
                        post("/api/publishers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                                requestFields(
                                        fieldWithPath("id").ignored(),
                                        fieldWithPath("name").description("Nazov publishera"),
                                        fieldWithPath("address").description("Adresa publishera")
                                )
                        )
                );


        verify(publisherService, times(1)).vytvorPublishera(any());
    }

    @Test
    public void testUpravPublishera() throws Exception {
        PublisherDTO dto = new PublisherDTO();
        dto.setName("slniecko");
        dto.setAddress("bratislava");

        mockMvc.perform(put("/api/publishers/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk());

        verify(publisherService, times(1)).upravPublishera(eq(1L), any(PublisherDTO.class));
    }
    @Test
    public void testVymazPublishera() throws Exception {
        doNothing().when(publisherService).vymazPublishera(anyLong());

        mockMvc.perform(delete("/api/publishers/{id}", 1))
                .andExpect(status().isOk());

        verify(publisherService, times(1)).vymazPublishera(eq(1L));
    }

    @Test
    public void testZoznamPublisherov() {

        ArrayList<PublisherDTO> publishers = new ArrayList<>();
        PublisherDTO publisher1 = new PublisherDTO();
        publisher1.setName("slniecko");
        publishers.add(publisher1);
        PublisherDTO publisher2 = new PublisherDTO();
        publisher2.setName("leto");
        publishers.add(publisher2);
        PublisherDTO publisher3 = new PublisherDTO();
        publisher3.setName("leto");
        publishers.add(publisher3);



        when(publisherService.zoznamPublisherov(any(String.class))).thenReturn(publishers);

        List<PublisherDTO> result = publisherService.zoznamPublisherov("leto");

        int count = 0;
        for (PublisherDTO publisher : result) {
            if (publisher.getName().equals("leto")) {
                count++;
            }
        }
        assertEquals(2, count); // 2 publisheri s hladanym nazvom
    }
}