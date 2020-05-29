package com.dbr.generator.springboot.ws.util.spring;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.logging.Logger;

public class RequestUtil {

    private static Logger logger = Logger.getLogger(RequestUtil.class.getName());

    public static String getServerURL() {
        String serverURL = getScheme() + "://" + getHost() + ":" + getPort() + "/";
        logger.info("serverURL: " + serverURL);
        return serverURL;
    }

    public static String getScheme() {
        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        return servletUriComponentsBuilder.build().getScheme();
    }

    public static int getPort() {
        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        return servletUriComponentsBuilder.build().getPort();
    }

    public static String getHost() {
        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequestUri();
        return servletUriComponentsBuilder.build().getHost();
    }
}
