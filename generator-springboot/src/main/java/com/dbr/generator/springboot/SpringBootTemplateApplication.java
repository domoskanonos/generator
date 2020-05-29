package com.dbr.generator.springboot;

import com.dbr.generator.springboot.system.ApplicationProperties;

import com.dbr.generator.springboot.util.SecurityUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { ApplicationProperties.BASE_PACKAGE })
@EnableJpaRepositories(basePackages = { ApplicationProperties.SYSTEM_BASE_PACKAGE,
        ApplicationProperties.APP_BASE_PACKAGE + ".repository" })
@EntityScan(basePackages = { ApplicationProperties.SYSTEM_BASE_PACKAGE, ApplicationProperties.APP_ENTITY_PACKAGE })
@EnableScheduling
@EnableAsync
public class SpringBootTemplateApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LogManager.getLogger(SpringBootTemplateApplication.class);

    public static void main(String[] args) {

        if (args.length > 0) {
            String secret = args[0];
            if (secret != null) {
                LOGGER.info("set secret by command line argument.");
                SecurityUtil.getUniqueInstance().setSystemSecret(secret);
            }
        } else {
            String secret = System.getProperty("secret");
            if (secret != null) {
                LOGGER.info("set secret by sytem property.");
                SecurityUtil.getUniqueInstance().setSystemSecret(secret);
            }
        }

        SpringApplication application = new SpringApplicationBuilder(SpringBootTemplateApplication.class).build();
        application.run(args);
    }

}
