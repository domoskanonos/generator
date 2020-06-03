package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.dto.ItemDTO;
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

    public String create(TemplateEnum templateEnum, ItemDTO dto) {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();

        Template t = velocityEngine.getTemplate(templateEnum.getTemplatePath());
        VelocityContext context = new VelocityContext();
        context.put("model", dto);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

    public void writeDown(TemplateEnum templateEnum, ItemDTO dto) throws IOException {
        File file = dto.getFilePath(templateEnum);
        log.info("merger, write down content to file: {}", file.getAbsolutePath());
        String content = create(templateEnum, dto);
        FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
    }

}
