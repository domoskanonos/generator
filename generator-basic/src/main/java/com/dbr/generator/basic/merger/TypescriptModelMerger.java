package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.dto.ObjectDTO;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class TypescriptModelMerger implements TemplateDTOMergerInterface<ObjectDTO> {

    @Override
    public String create(ObjectDTO objectDTO) {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate("typescript-model.vm");

        VelocityContext context = new VelocityContext();

        context.put("typescriptModelName", objectDTO.getTypescriptModelName());
        context.put("properties", objectDTO.getProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }
}
