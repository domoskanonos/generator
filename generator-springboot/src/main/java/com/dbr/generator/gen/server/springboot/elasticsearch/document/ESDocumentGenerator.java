package com.dbr.generator.gen.server.springboot.elasticsearch.document;

import com.dbr.generator.basic.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.elasticsearch.document.model.ESDocumentVM;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class ESDocumentGenerator extends AbstractGeneratorJava {

    private ESDocumentVM ESDocumentVM;

    public ESDocumentGenerator(ESDocumentVM ESDocumentVM) {
        super(ESDocumentVM.getClazzSimpleName(), ESDocumentVM.getPackageName());
        this.ESDocumentVM = ESDocumentVM;
    }

    @Override
    public String create() {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();
        Template t = velocityEngine.getTemplate("sb-es-document.vm");
        VelocityContext context = new VelocityContext();

        context.put("packageName", getPackageName());
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("indexName", this.ESDocumentVM.getIndexName());
        context.put("properties", this.ESDocumentVM.getProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }
}
