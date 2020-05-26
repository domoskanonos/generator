package com.dbr.generator.gen.server.springboot.csv;

import com.dbr.generator.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.csv.model.SpringBootCSVServiceVM;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class SpringBootCSVServiceGenerator extends AbstractGeneratorJava {

    private SpringBootCSVServiceVM model;

    public SpringBootCSVServiceGenerator(SpringBootCSVServiceVM model) {
        super(model.getClazzSimpleName(), model.getPackageName());
        this.model = model;
    }

    @Override
    public String create() {
        {
            VelocityEngine velocityEngine = VelocityUtil.getEngine();

            Template t = velocityEngine.getTemplate("sb-csv-service.vm");

            VelocityContext context = new VelocityContext();

            context.put("stringUtil", new StringUtil());

            context.put("packageName", getPackageName());
            context.put("clazzSimpleName", getClazzSimpleName());

            context.put("jpaClazzSimpleName", model.getJpaClazzSimpleName());
            context.put("csvImporterPackageName", model.getCsvImporterPackageName());
            context.put("csvImporterClazzSimpleName", model.getCsvImporterClazzSimpleName());
            context.put("dtoPackageName", model.getDtoPackageName());
            context.put("dtoClazzSimpleName", model.getDtoClazzSimpleName());
            context.put("mappingPackageName", model.getMappingPackageName());
            context.put("mapperClazzSimpleName", model.getMappingClazzSimpleName());
            context.put("jpaRepositoryPackageName", model.getJpaRepositoryPackageName());
            context.put("jpaRepositoryClazzSimpleName", model.getJpaRepositoryClazzSimpleName());

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            return writer.toString();

        }

    }

}
