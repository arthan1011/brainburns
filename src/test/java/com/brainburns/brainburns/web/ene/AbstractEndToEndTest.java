package com.brainburns.brainburns.web.ene;

import com.brainburns.brainburns.config.RootConfig;
import com.brainburns.brainburns.config.SecurityConfig;
import com.brainburns.brainburns.config.WebConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by arthan on 17.03.2017. | Project brainburns
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:/test.properties")
@ContextConfiguration(classes = {
        RootConfig.class,
        SecurityConfig.class,
        WebConfig.class
})
@WebAppConfiguration
@Sql(
        scripts = {"/sql/clear_all.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
abstract class AbstractEndToEndTest {
}
