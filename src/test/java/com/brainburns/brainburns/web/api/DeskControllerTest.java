package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.config.RootConfig;
import com.brainburns.brainburns.config.SecurityConfig;
import com.brainburns.brainburns.config.WebConfig;
import com.brainburns.brainburns.domain.model.Desk;
import com.brainburns.brainburns.service.DeskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import mockit.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.MessageFormat;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

    private MockMvc mockMvc;
    private MockMvc mockMvcWithoutSecurity;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Tested(availableDuringSetup = true)
    private DeskController deskController;

    @Injectable
    private DeskService deskService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(deskController)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
        mockMvcWithoutSecurity = MockMvcBuilders
                .standaloneSetup(deskController)
                .build();


    }

    @Test
    public void should_get_all_desks() throws Exception {

        mockMvcWithoutSecurity.perform(
                MockMvcRequestBuilders
                        .get("/api/desk/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tested"));

    }

    @Test
    @Ignore("useful as reference. no longer accurate")
    public void should_not_create_desk_with_duplicate_name() throws Exception {
        final int INCORRECT_DESK_NAME_RETURN_CODE = -1;
        final int TEST_DESK_ID = 123;
        final String TEST_DESK_TITLE = "Test_DEsk";

        Desk inputDesk = new Desk(TEST_DESK_ID, TEST_DESK_TITLE);
        ObjectMapper mapper = new ObjectMapper();
        String inputJson = mapper.writeValueAsString(inputDesk);

        String deskName = "DeskWithIncorrectName";
        String messageTemplate = "Desk with name '{0}' already exists";

        new Expectations() {{
            deskService.createDesk((Desk) any); result = INCORRECT_DESK_NAME_RETURN_CODE;
        }};

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/desk")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                .content(inputJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", Matchers.equalTo(MessageFormat.format(messageTemplate, deskName))))
                .andExpect(jsonPath("$.status", Matchers.equalTo("error")));

        new Verifications() {{
            Desk d;
            deskService.createDesk(d = withCapture());
            Assert.assertEquals(TEST_DESK_TITLE, d.getTitle());
            Assert.assertEquals(TEST_DESK_ID, d.getId());
        }};
    }
}