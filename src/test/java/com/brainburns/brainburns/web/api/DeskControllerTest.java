package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.config.RootConfig;
import com.brainburns.brainburns.config.SecurityConfig;
import com.brainburns.brainburns.config.WebConfig;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by arthan on 19.01.2017. | Project brainburns
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RootConfig.class,
        SecurityConfig.class,
        WebConfig.class
})
@WebAppConfiguration
public class DeskControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void should_get_all_desks() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/desk")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$.data[1].name", Matchers.is("Kanji")));

    }
}