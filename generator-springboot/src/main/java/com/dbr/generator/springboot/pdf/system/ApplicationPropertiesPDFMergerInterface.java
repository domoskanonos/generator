package com.dbr.generator.springboot.pdf.system;

import com.dbr.generator.springboot.system.freemarker.FreemarkerTemplateConfiguration;
import com.dbr.generator.springboot.system.freemarker.FreemarkerMergerInterface;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ApplicationPropertiesPDFMergerInterface implements FreemarkerMergerInterface<ApplicationPropertiesPDFModel> {

    @Override
    public String create(ApplicationPropertiesPDFModel model) throws IOException, TemplateException {
        FreemarkerTemplateConfiguration mailTemplateConfiguration = new FreemarkerTemplateConfiguration();
        Configuration configuration = mailTemplateConfiguration.createFreemarkerConfiguration();
        Template temp = configuration.getTemplate("/pdf/system/application-properties.ftlh");
        Map<String, ApplicationPropertiesPDFModel> root = new HashMap<>();
        root.put("model", model);
        StringWriter out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }

}
