package com.brainburns.brainburns.web.ene;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * Created by arthan on 16.03.2017. | Project brainburns
 */

// This is Selenium test example
public class SignUpTest extends AbstractEndToEndTest {

    public static final String NEW_USER_INFO_MESSAGE = "You created a new user";
    private WebDriver driver;

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
        driver = MockMvcHtmlUnitDriverBuilder
                .mockMvcSetup(mockMvc)
                .build();
    }

    @After
    public void destroy() {
        if (driver != null) {
            driver.close();
        }
    }

    @Test
    public void should_show_info_message_after_creating_new_user() throws Exception {
        SignUpPage signUpPage = SignUpPage.to(driver);
        String expectedButtonText = "Sign Up";
        Assert.assertEquals(
                "Text for singUp button should be 'Sign Up'",
                expectedButtonText,
                signUpPage.getSubmitButtonText());

        SignInPage signInPage = createNewUserAccount(signUpPage);
        Assert.assertEquals(
                "Should show correct info message",
                NEW_USER_INFO_MESSAGE,
                signInPage.getInfoMessageText()
        );

    }

    private SignInPage createNewUserAccount(SignUpPage signUpPage) {
        return signUpPage.createNewUserAccount("new_username", "new_password");
    }

    public static class SignUpPage {
        WebDriver driver;

        @FindBy(id = "username")
        private WebElement usernameInput;
        @FindBy(id = "password")
        private WebElement passwordInput;
        @FindBy(id = "repeatedPassword")
        private WebElement repeatedPassword;
        @FindBy(id = "submit")
        private WebElement submitButton;

        String getSubmitButtonText() {
            return submitButton.getText();
        }

        public SignUpPage(WebDriver driver) {
            this.driver = driver;
        }

        static SignUpPage to(WebDriver driver) {
            driver.get("http://localhost:8080/signup");
            return PageFactory.initElements(driver, SignUpPage.class);
        }

        SignInPage createNewUserAccount(String username, String password) {
            this.usernameInput.sendKeys(username);
            this.passwordInput.sendKeys(password);
            this.repeatedPassword.sendKeys(password);
            this.submitButton.click();
            return PageFactory.initElements(driver, SignInPage.class);
        }
    }

    public static class SignInPage {
        @FindBy(css = "h2.infoMessage")
        WebElement infoMessage;

        String getInfoMessageText() {
            return infoMessage.getText();
        }
    }
}
