package com.dbr.generator.springboot;

import com.dbr.generator.springboot.SpringBootTemplateApplication;
import com.dbr.generator.springboot.system.enumeration.BuildEnvironment;
import com.dbr.generator.springboot.system.ApplicationProperties;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicApplicationTests {

    @Autowired
    private Environment env;

    @Autowired
    ApplicationProperties applicationProperties;

    @Test
    public void contextLoads() {
        assertThat(env).isNotNull();
    }

    @Test
    public void systemEnvironmentTest() {
        assertThat(env).isNotNull();
        BuildEnvironment buildEnvironment = BuildEnvironment.get(env.getProperty("spring.profiles.active"));
        assertThat(applicationProperties.getBuildEnvironments().contains(buildEnvironment)).isTrue();

        for (BuildEnvironment be : BuildEnvironment.values()) {
            assertThat(be).isEqualTo(BuildEnvironment.get(be.getEnvironment()));
        }

    }

    @Test
    public void checkBasePackage() {
        assertThat(ApplicationProperties.BASE_PACKAGE)
                .isEqualTo(SpringBootTemplateApplication.class.getPackage().getName());
    }

}
