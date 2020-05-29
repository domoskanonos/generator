package com.dbr.generator.gen.server.springboot.csv;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.csv.model[0].SpringBootCSVRestControllerVM;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class SpringBootCSVRestControllerGenerator extends AbstractGeneratorJava {

    private SpringBootCSVRestControllerVM model;

    public SpringBootCSVRestControllerGenerator(SpringBootCSVRestControllerVM model) {
        super(model[0].getClazzSimpleName(), model[0].getPackageName());
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

            context.put("csvServiceClazzSimpleName", model[0].getCsvServiceClazzSimpleName());
            context.put("csvServicePackageName", model[0].getCsvServicePackageName());
            context.put("prefixPath", model[0].getPrefixPath());

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            return writer.toString();

        }

    }

}
