package com.dbr.generator.springboot.system;

import com.dbr.generator.springboot.system.enumeration.BuildEnvironment;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {

    public static final String BASE_PACKAGE = "com.dbr.generator.springboot";
    public static final String APP_BASE_PACKAGE = BASE_PACKAGE + ".app";
    public static final String APP_ENTITY_PACKAGE = APP_BASE_PACKAGE + ".entity";
    public static final String SYSTEM_BASE_PACKAGE = BASE_PACKAGE + ".system";

    @Autowired
    private Environment env;

    private String name;

    private String description;

    private String version;

    public String getPort() {
        return env.getProperty("local.server.port");
    }

    public String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    public String getCanonicalHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getCanonicalHostName();
    }

    public Set<BuildEnvironment> getBuildEnvironments() {
        String[] profiles = env.getProperty("spring.profiles.active").split(",");
        Set<BuildEnvironment> retval = new HashSet<>();
        for (String profile : profiles) {
            retval.add(BuildEnvironment.get(profile));
        }
        return retval;
    }

}
