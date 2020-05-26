package com.dbr.generator.gen.client.i18n;

import com.dbr.generator.VelocityUtil;
import com.dbr.generator.gen.AbstractGenerator;
import com.dbr.generator.gen.client.i18n.model.I18nVM;
import com.dbr.generator.gen.client.typescript.BasicTypescriptGenerator;
import com.dbr.generator.sample.dto.UserDTO;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.StringWriter;

public class I18nJSONGenerator extends BasicTypescriptGenerator {

    private I18nVM vm;

    public I18nJSONGenerator(I18nVM vm) {
        this.vm = vm;
    }

    public String create() {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate("i18n-json.vm");
        VelocityContext context = new VelocityContext();
        context.put("keyValues", this.vm.getKeyValues());
        StringWriter writer = new StringWriter();
        t.merge(context, writer);
        return writer.toString();
    }

    public void writeDown() throws Exception {
        this.typescriptSuffixPath = "../i18n";
        writeDown(vm.getFilename(), create());
    }

}
