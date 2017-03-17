package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.config.RootConfig;
import com.brainburns.brainburns.config.SecurityConfig;
import com.brainburns.brainburns.config.WebConfig;
import com.brainburns.brainburns.domain.model.Card;
import com.brainburns.brainburns.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import mockit.*;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by arthan on 31.01.2017. | Project brainburns
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/test.properties")
@ContextConfiguration(classes = {
        RootConfig.class,
        WebConfig.class
})
@WebAppConfiguration
public class CardControllerTest {

    private static final String TEST_USERNAME = "test_username";
    private static final String UNUSED_PASSWORD = "UNUSED_PASSWORD";
    private MockMvc mockMvc;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Tested(availableDuringSetup = true)
    private CardController cardController;

    @Injectable
    private CardService cardService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(cardController)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    @Test
    public void should_call_save_card_method() throws Exception {
        final int DESK_ID = 123;

        ObjectMapper mapper = new ObjectMapper();
        Card inputCard = new Card("Progress", null, "Прогресс");
        inputCard.setDeskId(DESK_ID);
        String inputJson = mapper.writeValueAsString(inputCard);

        new Expectations() {{
            cardService.saveCard("test_username", (Card) any); result = inputCard;
        }};

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/card")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                // todo: Use created users, there should be test config for SpringSecurity
                .with(SecurityMockMvcRequestPostProcessors.user(TEST_USERNAME).password(UNUSED_PASSWORD))
                .content(inputJson);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", Matchers.is("success")))
                .andExpect(jsonPath("$.data[0].deskId", Matchers.is(DESK_ID)));
    }

    @Test
    public void should_call_get_cards_method() throws Exception {
        final long DESK_ID = 32;

        new Expectations() {{
            cardService.getCardsInDesk(DESK_ID);
        }};

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/card")
                        .param("deskId", Long.toString(DESK_ID))
                        .with(SecurityMockMvcRequestPostProcessors.user(UNUSED_PASSWORD)))
                .andExpect(status().isOk());
    }
}
