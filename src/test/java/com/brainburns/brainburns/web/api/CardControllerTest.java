package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.config.RootConfig;
import com.brainburns.brainburns.config.SecurityConfig;
import com.brainburns.brainburns.config.WebConfig;
import com.brainburns.brainburns.domain.model.Card;
import com.brainburns.brainburns.service.CardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import mockit.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created by arthan on 31.01.2017. | Project brainburns
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RootConfig.class,
        SecurityConfig.class,
        WebConfig.class
})
@WebAppConfiguration
public class CardControllerTest {

    private MockMvc mockMvc;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Tested(availableDuringSetup = true)
    CardController cardController;

    @Injectable
    CardService cardService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(cardController)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
    }

    @Test
    public void should_call_save_card_method() throws Exception {
        new Expectations() {{
            cardService.saveCard("admin", (Card) any);
        }};

        ObjectMapper mapper = new ObjectMapper();
        Card inputCard = new Card("Progress", null, "Прогресс");
        String inputJson = mapper.writeValueAsString(inputCard);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/card")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        // todo: Используем существующих юзеров, надо будет тестовую конфигуркцию SpringSecurity
                        .with(SecurityMockMvcRequestPostProcessors.user("admin").password("admin"))
                .content(inputJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
