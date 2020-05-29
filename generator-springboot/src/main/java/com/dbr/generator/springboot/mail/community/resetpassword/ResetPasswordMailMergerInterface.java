package com.dbr.generator.springboot.mail.community.resetpassword;

import com.dbr.generator.springboot.system.freemarker.FreemarkerTemplateConfiguration;
import com.dbr.generator.springboot.system.freemarker.FreemarkerMergerInterface;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ResetPasswordMailMergerInterface implements FreemarkerMergerInterface<ResetPasswordModel> {

    @Override
    public String create(ResetPasswordModel model) throws IOException, TemplateException {
        FreemarkerTemplateConfiguration mailTemplateConfiguration = new FreemarkerTemplateConfiguration();
        Configuration configuration = mailTemplateConfiguration.createFreemarkerConfiguration();
        Template temp = configuration.getTemplate(new StringBuilder().append("/mail/community/").append(model.getLocale().getLanguage()).append("-reset-password.ftlh").toString());
        Map<String, ResetPasswordModel> root = new HashMap<>();
        root.put("model", model);
        StringWriter out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }

}
