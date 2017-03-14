package com.brainburns.brainburns.service;

import com.brainburns.brainburns.domain.model.Desk;
import com.brainburns.brainburns.domain.model.User;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

/**
 * Created by arthan on 13.03.2017. | Project brainburns
 */

@Sql(
        scripts = {"/sql/clear_all.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceTest extends AbstractDatabaseTest {

    @Autowired
    private UserService userService;
    @Autowired
    private DeskService deskService;

    @Test
    @DatabaseSetup(value = "/dbtest/before_new_user.xml")
    @ExpectedDatabase(value = "/dbtest/after_new_user.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void should_create_default_desk_for_new_user() throws Exception {
        final String USERNAME = "second_test_user";
        final String PASSWORD = USERNAME;
        final int EXPECTED_DESKS_AMOUNT = 1;
        final String EXPECTED_DESK_TITLE = "Default";

        User newUser = new User(USERNAME, PASSWORD);
        userService.createUser(newUser);
        List<Desk> desks = deskService.findByUsername(USERNAME);

        Assert.assertEquals("Should create exactly one default desk", EXPECTED_DESKS_AMOUNT, desks.size());
        Assert.assertEquals(
                "Should create desk with title 'Default'",
                EXPECTED_DESK_TITLE,
                desks.get(0).getTitle());
    }
}
