package com.dbr.generator.gen.client.typescript;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.client.typescript.model.ClazzTypescriptWrappersVM;
import lombok.AllArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

@AllArgsConstructor
public class ClazzTypescriptWrappersGenerator extends BasicTypescriptGenerator {

    private ClazzTypescriptWrappersVM model;

    public void writeDown() throws Exception {
        this.typescriptSuffixPath = model.getFolderName();
        writeDown(model.getFilename(), createComponent());
    }

    public String createComponent() throws Exception {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate(model.getTemplatePath());
        VelocityContext context = new VelocityContext();
        context.put("model", model);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }

}
