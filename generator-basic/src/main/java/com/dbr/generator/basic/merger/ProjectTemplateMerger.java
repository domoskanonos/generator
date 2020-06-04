package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.enumeration.ProjectTemplateEnum;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.util.VelocityUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class ProjectTemplateMerger {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public String create(ProjectTemplateEnum projectTemplateEnum, ItemModel dto) {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();

        Template t = velocityEngine.getTemplate(projectTemplateEnum.getTemplatePath());
        VelocityContext context = new VelocityContext();
        context.put("model", dto);
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

    public void writeDown(File projectFolder, ProjectTemplateEnum itemTemplateEnum, ItemModel dto) throws IOException {
        String content = create(itemTemplateEnum, dto);
        //File file = dto.getFilePath(projectFolder, itemTemplateEnum);
        //log.info("merger, write down content to file: {}", file.getAbsolutePath());
        //FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
    }

}
