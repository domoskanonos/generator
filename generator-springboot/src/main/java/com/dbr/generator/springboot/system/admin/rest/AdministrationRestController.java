package com.dbr.generator.springboot.system.admin.rest;

import com.dbr.generator.springboot.system.media.MediaResponseModel;
import com.dbr.generator.springboot.pdf.system.ApplicationPropertiesMediaResponseCreator;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(tags = AdministrationRestController.PATH_PREFIX)
@RestController
@RequiredArgsConstructor
@RequestMapping(AdministrationRestController.PATH_PREFIX)
public class AdministrationRestController {

    public static final String PATH_PREFIX = "SYSTEM/ADMIN";

    Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    Environment env;

    @GetMapping("/PDF/APPLICATION_PROPERTIES")
    public ResponseEntity<InputStreamResource> downloadApplicationProperties() throws IOException, TemplateException {
        MediaResponseModel mediaResponseModel = new ApplicationPropertiesMediaResponseCreator(env).get();
        return ResponseEntity.ok().contentLength(mediaResponseModel.getLength()).headers(mediaResponseModel.getHeaders())
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .body(new InputStreamResource(mediaResponseModel.get()));
    }

    @ApiOperation(value = "change log level by packageName, possible values for logLevel are: [\"OFF\",\"FATAL\",\"ERROR\",\"WARN\",\"INFO\",\"DEBUG\",\"TRACE\"]")
    @PostMapping(value = "/LOG_CHANGE/BY_PACKAGE")
    public ResponseEntity changeLogLevelByPackageName(@RequestParam("loglevel") String logLevel,
            @RequestParam(value = "packageName") String packageName) {
        changeLogLevel(Level.getLevel(logLevel), packageName);
        return ResponseEntity.accepted().build();
    }

    @ApiOperation(value = "change root log level, possible values for logLevel are: [\"OFF\",\"FATAL\",\"ERROR\",\"WARN\",\"INFO\",\"DEBUG\",\"TRACE\"]")
    @PostMapping(value = "/LOG_CHANGE/GLOBAL")
    public ResponseEntity root(@RequestParam("loglevel") String logLevel) {
        changeLogLevel(Level.getLevel(logLevel), LogManager.ROOT_LOGGER_NAME);
        return ResponseEntity.accepted().build();
    }

    private void changeLogLevel(Level level, String packageName) {
        log.info("change log level of package: {} , new log level: {} ", packageName, level);
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(packageName);
        loggerConfig.setLevel(level);
        ctx.updateLoggers();
    }

}
