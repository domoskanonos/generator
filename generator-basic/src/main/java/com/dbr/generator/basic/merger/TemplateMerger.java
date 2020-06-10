package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

public class TemplateMerger {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public String create(Template template, Object model) {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        org.apache.velocity.Template t = velocityEngine.getTemplate(template.getTemplatePath());
        VelocityContext context = new VelocityContext();
        context.put("model", model);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

    public void writeDown(File file, Template template, Object model) throws IOException {
        String content = create(template, model);
        log.info("merger, write down content to file: {}", file.getAbsolutePath());
        FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
    }

}
