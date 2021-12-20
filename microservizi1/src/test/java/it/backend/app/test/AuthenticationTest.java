package it.backend.app.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
       this.mockMvc.perform(
                 post("/auth/login")
                .content("{\"username\":\"John\",\"password\":\"1234\"}").
                         contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
       this.mockMvc.perform(
                post("/auth/login")
                        .content("{\"username\":\"John2\",\"password\":\"1234\"}").
                        contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().is4xxClientError());
    }
}
