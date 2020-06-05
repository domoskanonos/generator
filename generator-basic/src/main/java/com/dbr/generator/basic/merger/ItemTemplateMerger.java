package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class ItemTemplateMerger {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public String create(TemplateEnum templateEnum, ItemModel dto) {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();

        Template t = velocityEngine.getTemplate(templateEnum.getTemplatePath());
        VelocityContext context = new VelocityContext();
        context.put("model", dto);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

    public void writeDown(File projectFolder, TemplateEnum templateEnum, ItemModel dto) throws IOException {
        String content = create(templateEnum, dto);
        File file = dto.getFilePath(projectFolder, templateEnum);
        log.info("merger, write down content to file: {}", file.getAbsolutePath());
        FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
    }

}
