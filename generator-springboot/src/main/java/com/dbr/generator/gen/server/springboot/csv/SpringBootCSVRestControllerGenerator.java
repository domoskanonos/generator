package com.dbr.generator.gen.server.springboot.csv;

import com.dbr.generator.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.csv.model.SpringBootCSVRestControllerVM;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class SpringBootCSVRestControllerGenerator extends AbstractGeneratorJava {

    private SpringBootCSVRestControllerVM model;

    public SpringBootCSVRestControllerGenerator(SpringBootCSVRestControllerVM model) {
        super(model.getClazzSimpleName(), model.getPackageName());
        this.model = model;
    }

    /**
     * Erstellt eine Spring Boot RestController Klasse mit CRUD Funktionalität.
     *
     * @return
     */
    @Override
    public String create() {
        {
            VelocityEngine velocityEngine = VelocityUtil.getEngine();

            Template t = velocityEngine.getTemplate("sb-csv-rest-controller.vm");

            VelocityContext context = new VelocityContext();

            context.put("stringUtil", new StringUtil());

            context.put("packageName", getPackageName());
            context.put("clazzSimpleName", getClazzSimpleName());

            context.put("csvServiceClazzSimpleName", model.getCsvServiceClazzSimpleName());
            context.put("csvServicePackageName", model.getCsvServicePackageName());
            context.put("prefixPath", model.getPrefixPath());

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            return writer.toString();

        }

    }

}
