package edu.udacity.java.nano;

import edu.udacity.java.nano.chat.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest
public class WebSocketChatApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    private Message message;

    @Test
    public void login() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }

    @Test
    public void userJoin() throws Exception {
        this.mockMvc.perform(get("/index?username=jimmy")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("jimmy")));
    }
}
