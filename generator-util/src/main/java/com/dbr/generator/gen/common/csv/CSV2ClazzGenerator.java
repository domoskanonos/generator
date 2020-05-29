package com.dbr.generator.gen.common.csv;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.common.csv.model.CSVClazzVM;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class CSV2ClazzGenerator extends AbstractGeneratorJava {

    protected CSVClazzVM vm;

    public CSV2ClazzGenerator(CSVClazzVM vm) {
        super(vm.getClazzSimpleName(), vm.getPackageName());
        this.vm = vm;
    }

    @Override
    public String create() {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        Template t = velocityEngine.getTemplate("common/csv/csv-clazz.vm");

        VelocityContext context = new VelocityContext();
        context.put("packageName", vm.getPackageName());
        context.put("clazzSimpleName", vm.getClazzSimpleName());
        context.put("properties", vm.getProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

}
