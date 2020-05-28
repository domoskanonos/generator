package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.dto.ObjectDTO;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class DTOMerger implements TemplateDTOMergerInterface<ObjectDTO> {

    @Override
    public String create(ObjectDTO objectDTO) {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();
        Template t = velocityEngine.getTemplate("dto.vm");

        VelocityContext context = new VelocityContext();
        context.put("packageName", objectDTO.getPackageName());
        context.put("clazzSimpleName", objectDTO.getClazzSimpleName());
        context.put("properties", objectDTO.getProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }
}
