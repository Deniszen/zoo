package com.example.zoo;

import com.example.zoo.models.Animal;
import com.example.zoo.models.Animals;
import com.example.zoo.repository.AnimalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void checkCreateAnimal() throws Exception {
        Animal user = new Animal(3, "Zebra", "Sasha", "33");

        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))).andDo(print())
                .andExpect(status().isOk());

        Optional<Animal> animal = animalRepository.findById(3L);
        assertEquals("Sasha", animal.get().getName());
    }

    @Test
    public void checkGetAnimals() throws Exception {
        Animal user = new Animal(2, "Zebra", "Sasha", "44");

        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(get("/animals/list")
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        Animals animals = new Animals();
        animals.setAnimals(animalRepository.findByOrderByIdAsc());
        assertEquals("44", animals.getAnimals().get(0).getAge());
        animals.setAnimals(animalRepository.findByOrderByIdDesc());
        assertEquals("33", animals.getAnimals().get(0).getAge());
    }
}
