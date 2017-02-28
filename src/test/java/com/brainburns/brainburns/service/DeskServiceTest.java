package com.brainburns.brainburns.service;

import com.brainburns.brainburns.config.RootConfig;
import com.brainburns.brainburns.domain.mapper.DeskMapper;
import com.brainburns.brainburns.domain.model.Desk;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import mockit.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

/**
 * Created by arthan on 20.01.2017. | Project brainburns
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/test.properties")
@ContextConfiguration(
        classes = {RootConfig.class}
)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class DeskServiceTest {

    @Autowired
    private DeskService deskService;
    @Autowired
    private UserService userService;

    @Test
    @DatabaseSetup("/dbtest/desks.xml")
    public void should_retrieve_user() throws Exception {

        System.out.println(userService.getHello());

        List<Desk> desks = deskService.findByUsername("admin");

        Assert.assertEquals(
                "Should find all desks for user",
                2,
                desks.size()
        );
    }

    @Test
    @DatabaseSetup("/dbtest/desks.xml")
    @ExpectedDatabase(value = "/dbtest/desks_after_add.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void should_create_desk_for_user() throws Exception {
        Desk desk = new Desk();
        desk.setTitle("Trd");
        desk.setUsername("admin");

        deskService.createDesk(desk);
    }

    @Test(expected = DuplicateDeskTitleException.class)
    @DatabaseSetup("/dbtest/desks.xml")
    @ExpectedDatabase(value = "/dbtest/desks.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void should_not_create_desk_with_the_same_name() throws Exception {
        Desk desk = new Desk();
        desk.setTitle("Fst");
        desk.setUsername("admin");

        deskService.createDesk(desk);
    }

    @Test(expected = DuplicateDeskTitleException.class)
    public void should_raise_exception_when_desks_with_duplicate_title_exist() throws Exception {
        new MockUp<DeskService>() {
            @Mock
            boolean deskTitleExists(Desk desk) {
                return true;
            }
        };

        DeskService deskService = new DeskService(null);
        deskService.createDesk(new Desk());

    }
}