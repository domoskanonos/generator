package com.dbr.generator.gen.server.springboot.elasticsearch.repository;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.elasticsearch.repository.model.ESRepositoryVM;
import com.dbr.generator.sample.entity.Example;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Erstellt eine Spring Boot JPA Repository Klasse aus einer Entity Class und einem clazzSimpleName, welcher den
 * Repository Klassennamen darstellt.
 */
public class ESRepositoryGenerator extends AbstractGeneratorJava {

    private ESRepositoryVM model;

    public ESRepositoryGenerator(ESRepositoryVM model) {
        super(model.getRepositoryClazzSimpleName(), model.getRepositoryPackageName());
        this.model = model;
    }

    public static void main(String[] args) throws IOException {
        ESRepositoryVM ESRepositoryVM = new ESRepositoryVM("com.dbr.generator.result", "UserRepository", Example.class);
        ESRepositoryGenerator generator = new ESRepositoryGenerator(ESRepositoryVM);
        String content = generator.create();
        _log.info(content);
    }

    /**
     * Erstellt eine Spring Boot JPA Repository Klasse aus einer Entity Class und einem clazzSimpleName, welcher den
     * Repository Klassennamen darstellt.
     *
     * @return clazz content as string
     */
    @Override
    public String create() {
        {
            VelocityEngine velocityEngine = VelocityUtil.getEngine();

            Template t = velocityEngine.getTemplate("sb-es-repository.vm");

            VelocityContext context = new VelocityContext();

            context.put("stringUtil", new StringUtil());

            context.put("packageName", getPackageName());
            context.put("clazzSimpleName", getClazzSimpleName());
            context.put("documentClazzName", model.getDocumentClazzName());

            context.put("properties", model.getProperties());
            context.put("idClazz", model.getIdClazz());

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            return writer.toString();

        }

    }

}
