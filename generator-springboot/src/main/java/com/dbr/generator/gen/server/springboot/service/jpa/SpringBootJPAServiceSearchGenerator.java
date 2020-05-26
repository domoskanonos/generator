package com.dbr.generator.gen.server.springboot.service.jpa;

import com.dbr.generator.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.service.jpa.model.SpringBootJPAServiceBasicVM;
import com.dbr.generator.gen.server.springboot.service.jpa.model.SpringBootJPAServiceSearchVM;
import com.dbr.generator.sample.entity.Example;
import com.dbr.generator.util.generator.GeneratorUtil;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

/**
 * Erstellt eine Spring Boot Service Klasse aus einer Entity Class und einem clazzSimpleName, welcher den Service
 * Klassennamen darstellt.
 */
public class SpringBootJPAServiceSearchGenerator extends AbstractGeneratorJava {

    private SpringBootJPAServiceSearchVM springBootJpaServiceBasicVM;

    public SpringBootJPAServiceSearchGenerator(SpringBootJPAServiceSearchVM springBootJpaServiceBasicVM) {
        super(springBootJpaServiceBasicVM.getServiceClazzSimpleName(), springBootJpaServiceBasicVM.getPackageName());
        this.springBootJpaServiceBasicVM = springBootJpaServiceBasicVM;
    }

    public static void main(String[] args) {
        SpringBootJPAServiceSearchGenerator generator = new SpringBootJPAServiceSearchGenerator(
                new SpringBootJPAServiceSearchVM("com.dbr.springboot", "com.dbr.springboot.ws.service", "AppDTO",
                        Example.class));
        String content = generator.create();
        _log.info(content);
    }

    /**
     * Erstellt eine Spring Boot Service Klasse aus einer Entity Class und einem serviceClazzName, welcher den Service
     * Klassennamen darstellt.
     *
     * @return
     */
    @Override
    public String create() {
        {
            VelocityEngine velocityEngine = VelocityUtil.getEngine();

            Template t = velocityEngine.getTemplate("sb-jpa-service-search.vm");

            VelocityContext context = new VelocityContext();

            context.put("stringUtil", new StringUtil());
            context.put("generatorUtil", new GeneratorUtil());

            context.put("systemPackageName", springBootJpaServiceBasicVM.getSystemPackageName());
            context.put("packageName", getPackageName());
            context.put("basePackageName", springBootJpaServiceBasicVM.getBasePackageName());
            context.put("serviceClazzName", getClazzSimpleName());
            context.put("dtoClazzSimpleName", springBootJpaServiceBasicVM.getDtoClazzSimpleName());
            context.put("repositoryClazzSimpleName", springBootJpaServiceBasicVM.getRepositoryClazzSimpleName());
            context.put("jpaClazzSimpleName", springBootJpaServiceBasicVM.getJpaClazzSimpleName());
            context.put("properties", springBootJpaServiceBasicVM.getProperties());

            context.put("idClazz", springBootJpaServiceBasicVM.getIdClazz());

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            return writer.toString();

        }

    }

}
