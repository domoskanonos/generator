package com.dbr.generator.basic.merger.objectdto;

import com.dbr.generator.basic.merger.TemplateModelMergerInterface;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class ObjectModelMerger implements TemplateModelMergerInterface {

    private String templatePath;

    public ObjectModelMerger(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public String create(Object model) {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();
        Template t = velocityEngine.getTemplate(this.templatePath);

        VelocityContext context = new VelocityContext();
        context.put("model", model);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }
}
