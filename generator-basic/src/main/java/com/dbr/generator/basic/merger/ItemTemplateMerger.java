package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;

public class ItemTemplateMerger extends AbstractTemplateMerger {

    protected ItemDTO dto;

    public ItemTemplateMerger(ItemDTO dto) {
        this.dto = dto;
    }

    @Override
    public String create() {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();
        Template t = velocityEngine.getTemplate(this.dto.getTemplatePath());

        VelocityContext context = new VelocityContext();
        context.put("model", this.dto);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

    public void writeDown() throws IOException {
        super.writeDown(dto.getFilePath());
    }
}
