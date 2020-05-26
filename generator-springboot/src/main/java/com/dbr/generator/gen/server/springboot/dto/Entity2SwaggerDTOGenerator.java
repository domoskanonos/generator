package com.dbr.generator.gen.server.springboot.dto;

import com.dbr.generator.util.generator.GeneratorUtil;
import com.dbr.generator.basic.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Generiert aus einer JAVA Entity Klasse eine entsprechende DTO Klasse mit Swagger Api Annotations. .
 */
public class Entity2SwaggerDTOGenerator extends AbstractGeneratorJava {

    private Class<?> entityClazz;

    public Entity2SwaggerDTOGenerator(String clazzSimpleName, String packageName, Class<?> entityClazz) {
        super(clazzSimpleName, packageName);
        this.entityClazz = entityClazz;
    }

    /**
     * Erstellt eine DTO Java Datei mit Swagger Api Annotations als String.
     *
     * @return
     * @throws IOException
     */
    public String create() {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();
        Template t = velocityEngine.getTemplate("sb-swagger-dto.vm");

        VelocityContext context = new VelocityContext();
        context.put("generatorUtil", new GeneratorUtil());
        context.put("packageName", getPackageName());
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("properties", GeneratorUtil.getPrimitivesOnly(entityClazz));

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}
