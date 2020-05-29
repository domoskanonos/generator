package com.dbr.generator.springboot.system.mail.config;

import com.dbr.generator.springboot.system.ApplicationProperties;
import com.dbr.generator.springboot.system.enumeration.BuildEnvironment;
import com.dbr.generator.springboot.util.SecurityUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final ApplicationProperties applicationProperties;

    private final Environment environment;

    public MailConfiguration(ApplicationProperties applicationProperties, Environment environment) {
        this.applicationProperties = applicationProperties;
        this.environment = environment;
    }

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        try {
            String propertyPassword = this.environment.getProperty("spring.mail.password");
            String propertyUsername = this.environment.getProperty("spring.mail.username");
            if (!this.applicationProperties.getBuildEnvironments().contains(BuildEnvironment.DEV)
                    && StringUtils.isNotBlank(propertyUsername) && StringUtils.isNotBlank(propertyPassword)) {
                javaMailSender.setUsername(SecurityUtil.getUniqueInstance().decrypt(propertyUsername));
                javaMailSender.setPassword(SecurityUtil.getUniqueInstance().decrypt(propertyPassword));
            }
        } catch (SecurityUtil.SecurityUtilException e) {
            log.warn(
                    "error decrypting email username or password for mail configuration, do you set the correct secret key for decrypting ?");
        } catch (NegativeArraySizeException e) {
            log.warn(
                    "error decrypting email username or password for mail configuration, do you set the correct encrypted spring property pring.mail.username or spring.mail.password ?");
        }

        javaMailSender.setHost(this.environment.getProperty("spring.mail.host"));
        Integer port = this.environment.getProperty("spring.mail.port", Integer.TYPE);
        if (port != null) {
            log.info("setting mail port to: {}", port);
            javaMailSender.setPort(port);
        }

        Properties props = javaMailSender.getJavaMailProperties();
        setMailProperty(props, "mail.smtp.auth", this.environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        setMailProperty(props, "mail.smtp.starttls.enable",
                this.environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        setMailProperty(props, "mail.debug", this.environment.getProperty("spring.mail.username"));

        return javaMailSender;
    }

    private void setMailProperty(Properties props, String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            log.info("setting mail property {} to: {}", key, value);
            props.put(key, value);
        }
    }
}
