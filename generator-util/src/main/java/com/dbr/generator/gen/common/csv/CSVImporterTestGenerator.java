package com.dbr.generator.gen.common.csv;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.common.csv.model[0].CSVImporterVM;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.StringWriter;

public class CSVImporterTestGenerator extends AbstractGeneratorJava {

    private CSVImporterVM vm;

    public CSVImporterTestGenerator(CSVImporterVM vm) {
        super(vm.getClazzSimpleName() + "Test", vm.getPackageName());
        setWriteDownPath(AbstractGeneratorJava.class.getResource("/").getPath() + "../../src/test/java/");
        this.vm = vm;
    }

    @Override
    public String create() {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        Template t = velocityEngine.getTemplate("common/csv/test-csv-importer.vm");

        VelocityContext context = new VelocityContext();
        context.put("generatorUtil", new GeneratorUtil());
        context.put("stringUtil", new StringUtil());
        context.put("packageName", getPackageName());
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("importerClazzSimpleName", vm.getClazzSimpleName());
        // context.put("properties", vm.getProperties());
        context.put("rowCount", vm.getRowCount());
        context.put("filename", vm.getFile().getName());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }

    @Override
    public void writeDown() throws Exception {
        super.writeDown();
        String filename = this.vm.getFile().getName();
        super.writeDown(this.vm.getContent(), new File(
                AbstractGeneratorJava.class.getResource("/").getPath() + "../../src/main/resources", filename));
    }
}
