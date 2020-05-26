package com.dbr.generator.basic;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityUtil {

    public static VelocityEngine getEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        // velocityEngine.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getTypescriptName());
        velocityEngine.init();
        return velocityEngine;
    }

}
