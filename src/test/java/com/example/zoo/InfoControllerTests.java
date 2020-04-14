package com.example.zoo;

import com.example.zoo.controller.InfoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ZooApplication.class)
class InfoControllerTests {

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(new InfoController()).build();
    }

    @MockBean
    private InfoController infoController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void infoControllerIsNotNullTest() {
        assertThat(infoController).isNotNull();
    }

    @Test
    public void checkReturnInfoMessageTest() throws Exception {
        this.mockMvc.perform(get("/info")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("This is simple CRUD service")))
                .andExpect(content().string(containsString("REST routes:")))
                .andExpect(content().string(containsString("Request JSON:")))
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"));
    }

    @Test
    public void checkReturnInfoNotSupportedRouteTest() throws Exception {
        this.mockMvc.perform(post("/info")).andDo(print()).andExpect(status().isMethodNotAllowed());
    }
}
