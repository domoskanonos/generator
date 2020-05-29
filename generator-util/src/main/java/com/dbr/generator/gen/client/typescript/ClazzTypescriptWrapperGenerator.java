package com.dbr.generator.gen.client.typescript;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.client.typescript.model[0].ClazzTypescriptWrapperVM;
import lombok.AllArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

@AllArgsConstructor
public class ClazzTypescriptWrapperGenerator extends BasicTypescriptGenerator {

    private ClazzTypescriptWrapperVM model;

    public void writeDown() throws Exception {
        this.typescriptSuffixPath = model[0].getFolderName();
        writeDown(model[0].getFilename(), createComponent());
    }

    public String createComponent() throws Exception {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate(model[0].getTemplatePath());
        VelocityContext context = new VelocityContext();
        context.put("clazzTypescriptWrapper", model[0].getClazzTypescriptWrapper());
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }

}
