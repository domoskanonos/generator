package com.dbr.generator.gen.server.springboot.repository;

import com.dbr.generator.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.repository.model.SpringBootJPARepositoryVM;
import com.dbr.generator.sample.entity.Example;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Erstellt eine Spring Boot JPA Repository Klasse aus einer Entity Class und einem clazzSimpleName,
 * welcher den Repository Klassennamen darstellt.
 */
public class SpringBootJPARepositoryGenerator extends AbstractGeneratorJava {

    private SpringBootJPARepositoryVM SpringBootJPARepositoryVM;

    public SpringBootJPARepositoryGenerator(SpringBootJPARepositoryVM SpringBootJPARepositoryVM) {
        super(SpringBootJPARepositoryVM.getRepositoryClazzSimpleName(), SpringBootJPARepositoryVM.getRepositoryPackageName());
        this.SpringBootJPARepositoryVM = SpringBootJPARepositoryVM;
    }

    public static void main(String[] args) throws IOException {
        SpringBootJPARepositoryVM SpringBootJPARepositoryVM = new SpringBootJPARepositoryVM("com.dbr.generator.result", Example.class);
        SpringBootJPARepositoryGenerator generator = new SpringBootJPARepositoryGenerator(SpringBootJPARepositoryVM);
        String content = generator.create();
        _log.info(content);
    }

    /**
     * Erstellt eine Spring Boot JPA Repository Klasse aus einer Entity Class und einem clazzSimpleName,
     * welcher den Repository Klassennamen darstellt.
     *
     * @return clazz content as string
     */
    @Override
    public String create() {
        {
            VelocityEngine velocityEngine = VelocityUtil.getEngine();

            Template t = velocityEngine
                    .getTemplate("sb-jpa-repository.vm");

            VelocityContext context = new VelocityContext();

            context.put("stringUtil", new StringUtil());

            context.put("packageName", getPackageName());
            context.put("clazzSimpleName", getClazzSimpleName());
            context.put("entityClazzPackageName", SpringBootJPARepositoryVM.getEntityClazzPackageName());
            context.put("jpaClazzSimpleName", SpringBootJPARepositoryVM.getJpaClazzSimpleName());

            context.put("properties", SpringBootJPARepositoryVM.getProperties());
            context.put("idClazz", SpringBootJPARepositoryVM.getIdClazz());

            context.put("generateSinglePropertieQuerys", SpringBootJPARepositoryVM.getGenerateSinglePropertieQuerys());

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            return writer.toString();

        }

    }

}
