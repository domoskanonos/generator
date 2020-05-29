package com.dbr.generator.gen.server.entity;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.entity.model.EntityVM;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class EntityGenerator extends AbstractGeneratorJava {

    private EntityVM entityVM;

    public EntityGenerator(EntityVM entityVM) {
        super(entityVM.getClazzSimpleName(), entityVM.getPackageName());
        this.entityVM = entityVM;
    }

    @Override
    public String create() {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate("server/entity.vm");
        VelocityContext context = new VelocityContext();

        context.put("packageName", getPackageName());
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("tableName", this.entityVM.getTableName());
        context.put("idClazzSimpleName", this.entityVM.getIdClazzSimpleName());
        context.put("idClazz", this.entityVM.isIdClazz());
        context.put("entityProperties", this.entityVM.getEntityProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }
}
