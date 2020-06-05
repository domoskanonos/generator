package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.model.project.ProjectModel;
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

public class ProjectTemplateMerger {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public String create(TemplateEnum projectTemplateEnum, ProjectModel model) {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate(projectTemplateEnum.getTemplatePath());
        VelocityContext context = new VelocityContext();
        context.put("model", model);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

    public void writeDown(TemplateEnum projectTemplateEnum, ProjectModel model) throws IOException {
        String content = create(projectTemplateEnum, model);
        File file = model.getFilePath(projectTemplateEnum);
        log.info("project template merger, write down content to file: {}", file.getAbsolutePath());
        FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
    }

}
