package com.example.zoo;

import com.example.zoo.controller.AnimalController;
import com.example.zoo.controller.InfoController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ZooApplicationTests {

    @MockBean
    private AnimalController animalController;

    @MockBean
    private InfoController infoController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void animalControllerIsNotNullTest() {
        assertThat(animalController).isNotNull();
    }

    @Test
    public void infoControllerIsNotNullTest() {
        assertThat(infoController).isNotNull();
    }

    @Test
    public void checkReturnDefaultMessageTest() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("_links")))
                .andExpect(content().string(containsString("animals")))
                .andExpect(content().string(containsString("http://localhost/animals")));
    }
}
