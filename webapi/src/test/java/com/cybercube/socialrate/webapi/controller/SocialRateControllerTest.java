package com.cybercube.socialrate.webapi.controller;

import com.cybercube.socialrate.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SocialRateControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SocialRateController socialRateController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(socialRateController).isNotNull();
    }

    @Test
    public void shouldProcessPersonRecord() throws Exception {
        String json = objectMapper.writeValueAsString(new Person("Pedro", "Andrade", 41));
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().isCreated()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);
    }

    @Test
    public void shouldNotAcceptPersonWithFirstNameNull() throws Exception {
        String json = "{\"first_name\":null,\"last_name\":\"Andrade\",\"age\":41}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().isBadRequest()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String message = mvcResult.getResolvedException().getMessage();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
        assertThat(message).containsSequence("All attributes of Person are required.");
    }

    @Test
    public void shouldNotAcceptPersonWithLastNameNull() throws Exception {
        String json = "{\"first_name\":\"Pedro\",\"last_name\":null,\"age\":41}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().isBadRequest()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String message = mvcResult.getResolvedException().getMessage();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
        assertThat(message).containsSequence("All attributes of Person are required.");
    }

    @Test
    public void shouldNotAcceptPersonWithAgeNameNull() throws Exception {
        String json = "{\"first_name\":\"Pedro\",\"last_name\":\"Andrade\",\"age\":null}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andExpect(status().isBadRequest()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String message = mvcResult.getResolvedException().getMessage();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);
        assertThat(message).containsSequence("All attributes of Person are required.");
    }

}