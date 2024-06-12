package com.w2m.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w2m.challenge.config.SecurityConfig;
import com.w2m.challenge.exception.NotFoundException;
import com.w2m.challenge.model.Spaceship;
import com.w2m.challenge.service.SpaceshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.w2m.challenge.util.MockConstant.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpaceshipController.class)
@Import(SecurityConfig.class)
@WithMockUser(username = "w2m-user", password = "w2m-user-password", roles = "W2M-USER")
class SpaceshipControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceshipService spaceshipService;

    private Spaceship spaceship;
    private List<Spaceship> spaceships;

    @BeforeEach
    void init(){
        spaceship = new Spaceship(SPACESHIP_ID, "nave-1");
        spaceships = List.of(spaceship);
    }

    @Test
    void testGetAllSpaceships() throws Exception {

        when(spaceshipService.getAll(null,  OFFSET, LIMIT_TEN))
                .thenReturn(spaceships);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/spaceship")
                        .queryParam("limit",String.valueOf(LIMIT_TEN))
                        .queryParam("offset",String.valueOf(OFFSET)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.[0].id").value(String.valueOf(SPACESHIP_ID)));
    }

    @Test
    void testGetSpaceshipById() throws Exception {

        when(spaceshipService.getById(SPACESHIP_ID))
                .thenReturn(spaceship);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/spaceship/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(String.valueOf(SPACESHIP_ID)));

    }

    @Test
    void testGetSpaceshipByNegativeId() throws Exception {
        when(spaceshipService.getById(NEGATIVE_ID))
                .thenThrow(new NotFoundException("Spaceship not found"));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/spaceship/-1"))
                .andExpect(status().isNotFound());

    }

    @Test
    void testCreateSpaceship() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/spaceship")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spaceship)))
                .andExpect(status().isCreated());

    }

    @Test
    void testUpdateSpaceship() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/spaceship")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spaceship)))
                .andExpect(status().isNoContent());
    }

    @Test

    void testDeleteSpaceship() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/spaceship/1"))
                .andExpect(status().isNoContent());
    }

}
