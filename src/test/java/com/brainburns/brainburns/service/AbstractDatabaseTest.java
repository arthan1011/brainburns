package com.brainburns.brainburns.service;

import com.brainburns.brainburns.config.RootConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by arthan on 01.02.2017. | Project brainburns
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/test.properties")
@ContextConfiguration(
        classes = {RootConfig.class}
)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        SqlScriptsTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL)
abstract class AbstractDatabaseTest {
}