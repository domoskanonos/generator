package com.dbr.generator.springboot.ws.system.admin.rest;

import com.dbr.generator.springboot.ws.SpringBootTemplateApplication;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootTemplateApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles({"dev", "disable-security"})
public class AdministrationRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void pdfApplicationProperties() {
        ResponseEntity<InputStreamResource> responseEntity = restTemplate
                .getForEntity(String.format("/%s/PDF/APPLICATION_PROPERTIES", AdministrationRestController.PATH_PREFIX), InputStreamResource.class);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity).isNotNull();
    }

    @Test
    public void changeRootLogLevel() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);

        Level currentLogLevel = loggerConfig.getLevel();

        Level newLogLevel = !currentLogLevel.equals(Level.DEBUG) ? Level.DEBUG : Level.INFO;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restTemplate.getRootUri() + AdministrationRestController.PATH_PREFIX + "/LOG_CHANGE/GLOBAL")
                .queryParam("loglevel", newLogLevel.name());

        HttpEntity<HttpStatus> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                null,
                HttpStatus.class);

        ResponseEntity<HttpStatus> responseEntity = ((ResponseEntity<HttpStatus>) response);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        assertThat(loggerConfig.getLevel()).isEqualTo(newLogLevel);

    }

    @Test
    public void changeLogLevel() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        String logLevelPackage = LogManager.ROOT_LOGGER_NAME;
        LoggerConfig loggerConfig = config.getLoggerConfig(logLevelPackage);

        Level currentLogLevel = loggerConfig.getLevel();

        Level newLogLevel = !currentLogLevel.equals(Level.DEBUG) ? Level.DEBUG : Level.INFO;

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restTemplate.getRootUri() + AdministrationRestController.PATH_PREFIX + "/LOG_CHANGE/BY_PACKAGE")
                .queryParam("loglevel", newLogLevel.name()).queryParam("packageName", logLevelPackage);

        HttpEntity<HttpStatus> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                null,
                HttpStatus.class);

        ResponseEntity<HttpStatus> responseEntity = ((ResponseEntity<HttpStatus>) response);
        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        assertThat(loggerConfig.getLevel()).isEqualTo(newLogLevel);

    }

}
