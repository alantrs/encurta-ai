package com.dev.encurta_ai.controller;

import com.dev.encurta_ai.dto.LinkResponse;
import com.dev.encurta_ai.model.Link;
import com.dev.encurta_ai.service.LinkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LinkController.class)
class LinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LinkService linkService;

    @Autowired
    private ObjectMapper objectMapper;

    Link link;

    @BeforeEach
    void setUp(){
       link = new Link(1L, "Url long", "Url short", "QR Code", LocalDateTime.now());

    }

    @Test
    @DisplayName("Should create link")
    void createLinkSuccess() throws Exception {
        String urlOriginal = "Url long";
        when(linkService.createLink(urlOriginal)).thenReturn(new LinkResponse(link, "Url short"));

        ResultActions resultActions = mockMvc.perform(post("/encurta-ai")
                        .param("urlOriginal", urlOriginal)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                        .andExpect(status().isCreated());

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        LinkResponse response = objectMapper.readValue(jsonResponse, LinkResponse.class);
        assertEquals(link.getUrlShort(), response.urlShort());
        assertEquals(urlOriginal, response.urlLong());
    }

}