package com.dbr.generator.gen.server.mapping;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.basic.model.JavaProperty;
import com.dbr.generator.gen.server.mapping.model.ClazzMappingVM;
import com.dbr.generator.sample.dto.UserDTO;
import com.dbr.generator.sample.entity.UserEntity;
import com.dbr.util.StringUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Generiert eine Mapping Klasse die ein Mapping von Entity nach DTO und von DTO nach Entity ermöglicht.
 */
public class ClazzMappingGenerator extends AbstractGeneratorJava {

    private ClazzMappingVM clazzMappingVM;

    public static void main(String[] args) {
        ClazzMappingVM clazzMappingVM = new ClazzMappingVM("com.dbr.generator.result", UserEntity.class, UserDTO.class);
        ClazzMappingGenerator clazzMappingGenerator = new ClazzMappingGenerator(clazzMappingVM);
        String content = clazzMappingGenerator.create();
        _log.info(content);
    }

    public ClazzMappingGenerator(ClazzMappingVM clazzMappingVM) {
        super(clazzMappingVM.getClazzSimpleName(), clazzMappingVM.getPackageName());
        this.clazzMappingVM = clazzMappingVM;
    }

    @Override
    public String create() {
        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        velocityEngine.init();
        Template t = velocityEngine.getTemplate(this.clazzMappingVM.getTemplate());

        VelocityContext context = new VelocityContext();
        context.put("packageName", getPackageName());
        context.put("clazzSimpleName", getClazzSimpleName());
        context.put("firstClazzName", clazzMappingVM.getFirstClazzName());
        context.put("firstClazzSimpleName", clazzMappingVM.getFirstClazzSimpleName());
        context.put("secondClazzName", clazzMappingVM.getSecondClazzName());
        context.put("secondClazzSimpleName", clazzMappingVM.getSecondClazzSimpleName());

        List<String> mappingFirstSecondEntries = new ArrayList<>();
        List<String> mappingSecondFirstEntries = new ArrayList<>();
        for (JavaProperty firstProperty : clazzMappingVM.getProperties()) {
            for (JavaProperty secondProperty : clazzMappingVM.getProperties()) {
                String firstName = firstProperty.getName();
                String secondName = secondProperty.getName();
                if (firstName.equals(secondName)
                        && firstProperty.getTypeSimpleName().equals(secondProperty.getTypeSimpleName())) {
                    mappingSecondFirstEntries.add("dest." + StringUtil.toSetterMethodPrefix(secondName) + "(source."
                            + StringUtil.toGetterMethodName(firstName) + ");");
                    mappingFirstSecondEntries.add("dest." + StringUtil.toSetterMethodPrefix(secondName) + "(source."
                            + StringUtil.toGetterMethodName(firstName) + ");");
                }

            }

        }
        context.put("mappingFirstSecondEntries", mappingFirstSecondEntries);
        context.put("mappingSecondFirstEntries", mappingSecondFirstEntries);

        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        return writer.toString();
    }

}
