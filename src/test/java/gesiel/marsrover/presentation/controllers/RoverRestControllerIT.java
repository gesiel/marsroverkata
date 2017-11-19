package gesiel.marsrover.presentation.controllers;

import gesiel.marsrover.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RoverRestControllerIT {

    private static final String PATH_PATTERN = "/rest/mars/%s";

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldMoveToRight() throws Exception {
        mvc.perform(post("MMRMMRMM"))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("(2, 0, S)")));
    }

    @Test
    public void shouldMoveToLeft() throws Exception {
        mvc.perform(post("MML"))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("(0, 2, W)")));
    }

    @Test
    public void shouldRespondBadRequestOnInvalidCommand() throws Exception {
        mvc.perform(post("AAA"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldRespondBadRequestOnInvalidPosition() throws Exception {
        mvc.perform(post("MMMMMMMMMMMMMMMMMMMMMMMM"))
            .andExpect(status().isBadRequest());
    }

    private MockHttpServletRequestBuilder post(String command) {
        return MockMvcRequestBuilders.post(format(PATH_PATTERN, command)).accept(MediaType.APPLICATION_JSON);
    }

}
