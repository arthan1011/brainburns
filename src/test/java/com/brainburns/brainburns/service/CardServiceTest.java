package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.model.Card;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by arthan on 01.02.2017. | Project brainburns
 */

public class CardServiceTest extends AbstractDatabaseTest {

    @Autowired
    private CardService cardService;

    @Test
    @DatabaseSetup("/dbtest/cards.xml")
    @ExpectedDatabase(value = "/dbtest/cards_after_add.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void should_create_new_card() throws Exception {
        Card inputCard = new Card("Test", null, "Тест");
        inputCard.setDeskId(12);
        Card savedCard = cardService.saveCard("arthan", inputCard);

        Assert.assertNotEquals("Should return card with id", -1, savedCard.getId());
        Assert.assertNotNull("Should return card with desk id", savedCard.getDeskId());
    }

    @Test
    @DatabaseSetup("/dbtest/cards_in_desks.xml")
    public void should_find_all_cards_for_desk() throws Exception {
        List<Card> cards = cardService.getCardsInDesk(11);
        int expectedCardsNumber = 2;
        Assert.assertEquals("Should return all cards in desk", expectedCardsNumber, cards.size());
    }
}