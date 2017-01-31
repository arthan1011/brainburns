package com.brainburns.brainburns.web.api;

import com.brainburns.brainburns.service.CardService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by arthan on 31.01.2017. | Project brainburns
 */
public class CardControllerUnitTest {

    @Tested
    CardController cardController;

    @Injectable
    CardService cardService;

    @Test
    public void shouldTest() throws Exception {

        new Expectations() {{
            cardService.tested("Var");
        }};

        Assert.assertEquals("Tested", cardController.test());
    }
}
