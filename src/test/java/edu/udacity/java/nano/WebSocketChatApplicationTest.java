package edu.udacity.java.nano;


import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import edu.udacity.java.nano.chat.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSocketChatApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    private Message message;

    @Test
    public void login() throws Exception {
        // verify that when loading the root page, it opens the login endpoint
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void userJoin() throws Exception {
        // check that once logged in, the chat page shows the user accordingly.
        this.mockMvc.perform(get("/index?username=jimmy")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("jimmy")));
        // You can also check the user counter, and make sure it shows the correct number of connected users.
    }

    @Test
    public void chat() throws Exception {
        // verify that after entering with a username, this indeed opens the chat endpoint
        this.mockMvc.perform(get("/index?username=jimmy")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void leave() throws Exception {
        //  verify that once the user has logged out, we return to the login page
        this.mockMvc.perform(get("/index?username=jimmy")).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("/"));
    }

}
