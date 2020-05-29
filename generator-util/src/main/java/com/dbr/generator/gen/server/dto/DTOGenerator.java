package com.dbr.generator.gen.server.dto;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.dto.model[0].DTOVM;
import com.dbr.generator.sample.entity.UserEntity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Generiert aus einer JAVA Entity Klasse eine entsprechende DTO Klasse. .
 */
public class DTOGenerator extends AbstractGeneratorJava {

    private DTOVM dtovm;

    public static void main(String[] args) {
        DTOVM dtovm = new DTOVM(UserEntity.class);
        dtovm.setClazzSimpleName("XXX");
        DTOGenerator dtoGenerator = new DTOGenerator(dtovm);
        System.out.println(dtoGenerator.create());
    }

    public DTOGenerator(DTOVM dtovm) {
        super(dtovm.getClazzSimpleName(), dtovm.getPackageName());
        this.dtovm = dtovm;
    }

    /**
     * Erstellt eine DTO Java Datei als String.
     *
     * @return
     * @throws IOException
     */
    @Override
    public String create() {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();
        Template t = velocityEngine.getTemplate("common/dto.vm");

        VelocityContext context = new VelocityContext();
        context.put("packageName", getPackageName());
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("properties", this.dtovm.getProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}
