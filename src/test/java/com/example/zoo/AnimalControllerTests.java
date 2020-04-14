package com.example.zoo;

import com.example.zoo.controller.AnimalController;
import com.example.zoo.models.Animal;
import com.example.zoo.repository.AnimalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(AnimalController.class)
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

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void animalControllerIsNotNullTest() {
        assertThat(animalController).isNotNull();
    }

    @Test
    public void checkReturnAnimalsListTest() throws Exception {
        this.mockMvc.perform(get("/animals/list")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void checkReturnAnimalsNotSupportedRouteTest() throws Exception {
        this.mockMvc.perform(get("/animals")).andDo(print()).andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void checkCreateAnimalTest() throws Exception {
        this.mockMvc.perform(
                post("/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(asJsonString(new Animal(3, "zebra", "dasha", "7")))).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void checkGetAnimalById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/animals/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void checkUpdateAnimal() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/animals/{id}", 2)
                .content(asJsonString(new Animal(3, "zebra", "dasha", "7")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteEmployeeAPI() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/animals/{id}", 1) )
                .andExpect(status().isOk());
    }
}
