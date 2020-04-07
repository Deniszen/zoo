package com.example.zoo;

import com.example.zoo.controller.AnimalController;
import com.example.zoo.models.Animal;
import com.example.zoo.repository.AnimalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTests {

    @MockBean
    private AnimalRepository repository;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(new AnimalController(repository)).build();
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalController animalController;

    @Test
    public void animalControllerIsNotNullTest() {
        assertThat(animalController).isNotNull();
    }

    @Test
    public void checkReturnAnimalsListTest() throws Exception {
        this.mockMvc.perform(get("/animals/list")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("{\"count\":0,\"animals\":[]}"));
    }

    @Test
    public void checkReturnAnimalsNotSupportedRouteTest() throws Exception {
        this.mockMvc.perform(get("/animals")).andDo(print()).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void checkCreateAnimalTest() throws Exception {
        Animal animal = new Animal();
        animal.setId(3L);
        animal.setType("zebra");
        animal.setName("dasha");
        animal.setAge("7");

        this.mockMvc.perform(
                post("/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(asJsonString(animal))).andDo(print())
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
