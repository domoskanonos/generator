package com.dbr.generator.gen.common.csv;

import com.dbr.generator.basic.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.common.csv.model.CSVImporterVM;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class CSVImporterGenerator extends AbstractGeneratorJava {

    private CSVImporterVM vm;

    public CSVImporterGenerator(CSVImporterVM vm) {
        super(vm.getClazzSimpleName(), vm.getPackageName());
        this.vm = vm;
    }

    @Override
    public String create() throws Exception {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        Template t = velocityEngine.getTemplate("common/csv/csv-importer.vm");

        VelocityContext context = new VelocityContext();

        context.put("stringUtil", new StringUtil());

        context.put("packageName", getPackageName());
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("modelClazzSimpleName", vm.getModelClazzSimpleName());
        context.put("modelClazzPackageName", vm.getModelClazzPackageName());
        context.put("columnSplit", vm.getColumnSplit());
        context.put("rowSplit", vm.getRowSplit());
        context.put("withHeaderRow", vm.getWithHeaderRow());

        context.put("properties", vm.getProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

}
