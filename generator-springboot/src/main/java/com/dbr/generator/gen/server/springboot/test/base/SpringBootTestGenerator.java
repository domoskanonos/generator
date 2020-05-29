package com.dbr.generator.gen.server.springboot.test.base;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.test.base.model.SpringBootBaseTestVM;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;

public class SpringBootTestGenerator extends AbstractGeneratorJava {

    private SpringBootBaseTestVM vm;

    public SpringBootTestGenerator(SpringBootBaseTestVM vm) {
        super(vm.getClazzSimpleName(), vm.getTestPackageName());
        setWriteDownPath(AbstractGeneratorJava.class.getResource("/").getPath() + "../../src/test/java/");
        this.vm = vm;
    }

    @Override
    public String create() {

        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        Template t = velocityEngine.getTemplate(vm.getTemplateName());

        VelocityContext context = new VelocityContext();
        context.put("generatorUtil", new GeneratorUtil());
        context.put("stringUtil", new StringUtil());
        context.put("packageName", vm.getTestPackageName());
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("entityClazzSimpleName", vm.getEntityClazzSimpleName());
        context.put("restControllerClazzName", vm.getRestControllerClazzName());
        context.put("restControllerClazzSimpleName", vm.getRestControllerClazzSimpleName());
        context.put("serviceClazzName", vm.getServiceClazzName());
        context.put("serviceClazzSimpleName", vm.getServiceClazzSimpleName());
        context.put("dtoClazzName", vm.getDTOClazzName());
        context.put("dtoClazzSimpleName", vm.getDtoClazzSimpleName());
        context.put("idClazzSimpleName", vm.getIdClazzSimpleName());
        context.put("properties", vm.getProperties());

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();

    }

}
