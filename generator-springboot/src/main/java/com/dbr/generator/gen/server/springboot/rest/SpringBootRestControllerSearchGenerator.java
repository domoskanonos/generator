package com.dbr.generator.gen.server.springboot.rest;

import com.dbr.generator.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.springboot.rest.model.SpringBootRestControllerBasicVM;
import com.dbr.generator.gen.server.springboot.rest.model.SpringBootRestControllerSearchVM;
import com.dbr.generator.util.generator.GeneratorUtil;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.lang.reflect.Field;

/**
 * Erstellt eine Spring Boot Service Klasse aus einer Entity Class und einem clazzSimpleName, welcher den Service
 * Klassennamen darstellt.
 */
public class SpringBootRestControllerSearchGenerator extends AbstractGeneratorJava {

    private SpringBootRestControllerSearchVM vm;

    public SpringBootRestControllerSearchGenerator(SpringBootRestControllerSearchVM vm) {
        super(vm.getClazzSimpleName(), vm.getRestControllerPackageName());
        this.vm = vm;
    }

    /**
     * Erstellt eine Spring Boot RestController Klasse mit CRUD Funktionalität.
     *
     * @return
     */
    @Override
    public String create() {
        {
            VelocityEngine velocityEngine = VelocityUtil.getEngine();

            Template t = velocityEngine.getTemplate("sb-rest-controller-search.vm");

            VelocityContext context = new VelocityContext();

            context.put("stringUtil", new StringUtil());

            context.put("packageName", getPackageName());
            context.put("prefixPath", this.vm.getPrefixPath());
            context.put("clazzSimpleName", getClazzSimpleName());
            context.put("serviceClazzName", this.vm.getServiceClazzName());
            context.put("serviceClazzSimpleName", this.vm.getServiceClazzSimpleName());
            context.put("dtoClazzName", this.vm.getDTOClazzName());
            context.put("dtoClazzSimpleName", this.vm.getDtoClazzSimpleName());

            context.put("jpaClazzSimpleName", this.vm.getEntityClazzSimpleName());
            context.put("properties", this.vm.getProperties());

            context.put("idPrimitive", GeneratorUtil.isBaseJavaType(vm.getIdClazzSimpleName()));

            context.put("idClazz", this.vm.getIdClazzSimpleName());

            // fileUploadMethods - START
            String fileUploadMethods = "";
            for (Field field : GeneratorUtil.getFieldsByTypeName(this.vm.getEntityClazz(), "byte[]")) {
                VelocityContext contextFileUpload = new VelocityContext();
                Template templateFileUpload = velocityEngine.getTemplate("sb-rest-controller-file-upload.vm");
                contextFileUpload.put("fieldNameFirstLetterUpperCase",
                        StringUtil.firstLetterToUpperCase(field.getName()));
                contextFileUpload.put("idClazz", this.vm.getIdClazzSimpleName());
                contextFileUpload.put("dtoClazzSimpleName", this.vm.getDtoClazzSimpleName());
                StringWriter writerFileUpload = new StringWriter();
                templateFileUpload.merge(contextFileUpload, writerFileUpload);
                fileUploadMethods += writerFileUpload.toString();

            }
            context.put("fileUploadMethods", fileUploadMethods);
            // fileUploadMethods - END

            StringWriter writer = new StringWriter();
            t.merge(context, writer);

            return writer.toString();

        }

    }

}
