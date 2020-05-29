package com.dbr.generator.basic.item.merger;

import com.dbr.generator.basic.item.dto.ItemMergerDTO;
import com.dbr.generator.basic.merger.TemplateModelMergerInterface;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class ItemMerger implements TemplateModelMergerInterface<ItemMergerDTO> {

    private String templatePath;

    public ItemMerger(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public String create(ItemMergerDTO model) {
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
