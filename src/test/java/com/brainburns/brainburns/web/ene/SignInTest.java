package com.brainburns.brainburns.web.ene;

import com.brainburns.brainburns.config.RootConfig;
import com.brainburns.brainburns.config.SecurityConfig;
import com.brainburns.brainburns.config.WebConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created by arthan on 16.03.2017. | Project brainburns
 */

// This is HtmlUnit test example
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/test.properties")
@ContextConfiguration(classes = {
        RootConfig.class,
        SecurityConfig.class,
        WebConfig.class
})
@WebAppConfiguration
public class SignInTest {

    private static final String INCORRECT_LOGIN_MESSAGE = "Incorrect Username or Password";
    private WebClient webClient;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        MockMvc mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity(springSecurityFilterChain))
                .build();
        webClient = MockMvcWebClientBuilder
                .mockMvcSetup(mockMvc)
                .build();
    }

    @Test
    public void should_show_error_message_for_incorrect_login() throws Exception {
        HtmlPage page = webClient.getPage("http://localhost:8080/signin");

        HtmlPage signInPage = tryIncorrectLogin(page);

        Assert.assertThat(
                "Should still be on signIn page with error param",
                signInPage.getUrl().toString(),
                Matchers.endsWith("signin?error"));

        List<?> messages = signInPage.getByXPath("//form/h2[@class='errorMessage']");
        Assert.assertEquals("Should find exactly one error message", 1, messages.size());

        Assert.assertEquals(
                "Should show error message for incorrect login",
                INCORRECT_LOGIN_MESSAGE,
                ((HtmlElement) messages.get(0)).getTextContent());
        System.out.println();
    }

    private HtmlPage tryIncorrectLogin(HtmlPage page) throws IOException {
        HtmlTextInput usernameInput = page.getHtmlElementById("username");
        usernameInput.setValueAttribute("a");

        HtmlPasswordInput passwordInput = page.getHtmlElementById("password");
        passwordInput.setValueAttribute("b");

        HtmlButton submitInput = page.getHtmlElementById("submit");
        return submitInput.click();
    }
}
