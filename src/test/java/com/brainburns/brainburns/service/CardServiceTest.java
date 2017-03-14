package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.model.Card;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Created by arthan on 01.02.2017. | Project brainburns
 */

@Sql(
        scripts = {"/sql/clear_all.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = {"/sql/users.sql"})
public class CardServiceTest extends AbstractDatabaseTest {

    private static final String TEST_CARD_WRITING = "Test";
    private static final String TEST_CARD_MEANING = "Тест";
    private static final String UNUSED_TRANSCRIPTION = null;
    private static final String TEST_USERNAME = "test_user";

    @Autowired
    private CardService cardService;

    @Test
    @DatabaseSetup("/dbtest/cards.xml")
    @ExpectedDatabase(value = "/dbtest/cards_after_add.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void should_create_new_card() throws Exception {
        Card inputCard = new Card(TEST_CARD_WRITING, UNUSED_TRANSCRIPTION, TEST_CARD_MEANING);
        final int DESK_ID = 11;
        inputCard.setDeskId(DESK_ID);
        Card savedCard = cardService.saveCard(TEST_USERNAME, inputCard);

        Assert.assertNotEquals("Should return card with id", -1, savedCard.getId());
        Assert.assertNotNull("Should return card with desk id", savedCard.getDeskId());
    }

    @Test
    @DatabaseSetup("/dbtest/cards_in_desks.xml")
    public void should_find_all_cards_for_desk() throws Exception {
        final int DESK_ID = 11;
        List<Card> cards = cardService.getCardsInDesk(DESK_ID);
        int expectedCardsNumber = 2;
        Assert.assertEquals("Should return all cards in desk", expectedCardsNumber, cards.size());
    }
}