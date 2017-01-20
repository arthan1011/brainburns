package com.brainburns.brainburns.service;

import com.brainburns.brainburns.config.RootConfig;
import com.brainburns.brainburns.domain.model.Desk;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

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
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class DeskServiceTest {

    @Autowired
    private DeskService deskService;
    @Autowired
    private UserService userService;

    @Test
    public void should_retrieve_user() throws Exception {

        System.out.println(userService.getHello());

        Desk desk = deskService.findById(1);


        Assert.assertEquals(
                "should get proper desk id",
                1,
                desk.getId());
        Assert.assertEquals(
                "should get title of first desk",
                "firstDesk",
                desk.getTitle());
    }
}