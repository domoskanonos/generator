package com.dbr.generator.basic.item.merger;

import com.dbr.generator.basic.AbstractMerger;
import com.dbr.generator.basic.item.merger.dto.ItemMergerDTO;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;

public abstract class ItemMerger extends AbstractMerger {

    protected ItemMergerDTO dto;
    private String templatePath;

    public ItemMerger(ItemMergerDTO dto, String templatePath) {
        this.dto = dto;
        this.templatePath = templatePath;
    }

    @Override
    public String create() {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();
        Template t = velocityEngine.getTemplate(this.templatePath);

        VelocityContext context = new VelocityContext();
        context.put("model", this.dto);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

    public void writeDown() throws IOException {
        super.writeDown(dto.getPath());
    }
}
