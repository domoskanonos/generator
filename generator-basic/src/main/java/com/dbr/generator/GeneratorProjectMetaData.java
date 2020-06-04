package com.dbr.generator;

import com.dbr.generator.basic.converter.JavaField2PropertyDTOConverter;
import com.dbr.generator.basic.entity.ItemEntity;
import com.dbr.generator.basic.entity.ProcessEntity;
import com.dbr.generator.basic.entity.ProjectEntity;
import com.dbr.generator.basic.entity.PropertyEntity;
import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.generator.process.ProcessGenerator;
import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.model.PropertyModel;
import com.dbr.generator.basic.model.project.JavaProjectModel;
import com.dbr.generator.basic.model.project.NidocaProjectModel;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.basic.model.project.SpringBootProjectModel;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

public class GeneratorProjectMetaData {

    public static ProcessModel PROCESS_MODEL;

    public static SpringBootProjectModel SPRING_BOOT_PROJECT_MODEL;

    public static JavaProjectModel SPRING_BOOT_JAVA_PROJECT_MODEL;

    public static NidocaProjectModel NIDOCA_PROJECT_MODEL;

    static {
        String processParentPath = new File("C:\\_dev\\vhs\\git").getAbsolutePath();
        String processTempPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();
        PROCESS_MODEL = new ProcessModel(processTempPath, processParentPath, "generator");
        List<ProjectModel> projectModels = PROCESS_MODEL.getProjects();

        String javaBasePackage = "com.dbr.generator.springboot.app";

        SPRING_BOOT_PROJECT_MODEL = new SpringBootProjectModel(PROCESS_MODEL, "springboot", javaBasePackage);
        SPRING_BOOT_PROJECT_MODEL.setAddSpringBootSecurityModule(true);
        //projectDTOS.add(SPRING_BOOT_PROJECT_DTO);

        SPRING_BOOT_JAVA_PROJECT_MODEL = new JavaProjectModel(PROCESS_MODEL, "springboot", javaBasePackage);
        //projectModels.add(SPRING_BOOT_JAVA_PROJECT_MODEL);


        NIDOCA_PROJECT_MODEL = new NidocaProjectModel(PROCESS_MODEL, "nidoca");
        projectModels.add(NIDOCA_PROJECT_MODEL);


        for (Class clazz : new Class[]{PropertyEntity.class, ItemEntity.class, ProjectEntity.class, ProcessEntity.class}) {
            ItemModel itemModel = new ItemModel(SPRING_BOOT_JAVA_PROJECT_MODEL, clazz.getSimpleName().replace("Entity", ""), TypeEnum.LONG, TemplateEnum.DTO_TEMPLATE, TemplateEnum.CLAZZ_MAPPING_TEMPLATE, TemplateEnum.SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE, TemplateEnum.SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE);
            ItemModel itemModelNidoca = new ItemModel(NIDOCA_PROJECT_MODEL, clazz.getSimpleName().replace("Entity", ""), TypeEnum.LONG, TemplateEnum.TYPESCRIPT_MODEL_TEMPLATE, TemplateEnum.TYPESCRIPT_REMOTE_REPOSITORY, TemplateEnum.TYPESCRIPT_NIDOCA_COMPONENT_EDIT, TemplateEnum.TYPESCRIPT_NIDOCA_COMPONENT_LIST);
            for (Field field : clazz.getDeclaredFields()) {
                PropertyModel propertyModel = new JavaField2PropertyDTOConverter().convert(itemModel, field);
                itemModel.addProperty(propertyModel);
                itemModelNidoca.addProperty(propertyModel);
            }
            SPRING_BOOT_JAVA_PROJECT_MODEL.getItems().add(itemModel);
            NIDOCA_PROJECT_MODEL.getItems().add(itemModelNidoca);
        }


    }

    public static void main(String[] args) throws Exception {
        ProcessGenerator.generate(PROCESS_MODEL);
    }

}
