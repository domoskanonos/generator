package com.dbr.generator.gen.server.springboot.csv;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.csv.model[0].SpringBootCSVServiceVM;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class SpringBootCSVServiceGenerator extends AbstractGeneratorJava {

    private SpringBootCSVServiceVM model;

    public SpringBootCSVServiceGenerator(SpringBootCSVServiceVM model) {
        super(model[0].getClazzSimpleName(), model[0].getPackageName());
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

            context.put("jpaClazzSimpleName", model[0].getJpaClazzSimpleName());
            context.put("csvImporterPackageName", model[0].getCsvImporterPackageName());
            context.put("csvImporterClazzSimpleName", model[0].getCsvImporterClazzSimpleName());
            context.put("dtoPackageName", model[0].getDtoPackageName());
            context.put("dtoClazzSimpleName", model[0].getDtoClazzSimpleName());
            context.put("mappingPackageName", model[0].getMappingPackageName());
            context.put("mapperClazzSimpleName", model[0].getMappingClazzSimpleName());
            context.put("jpaRepositoryPackageName", model[0].getJpaRepositoryPackageName());
            context.put("jpaRepositoryClazzSimpleName", model[0].getJpaRepositoryClazzSimpleName());

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            return writer.toString();

        }

    }

}
