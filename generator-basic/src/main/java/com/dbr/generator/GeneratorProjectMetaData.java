package com.dbr.generator;

import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.model.project.JavaProjectModel;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.basic.model.project.SpringBootProjectModel;
import com.dbr.generator.basic.enumeration.ItemType;
import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.generator.process.ProcessGenerator;
import com.dbr.generator.basic.merger.TemplateEnum;

import java.io.File;
import java.util.List;

public class GeneratorProjectMetaData {

    public static ProcessModel PROCESS_DTO;

    public static SpringBootProjectModel SPRING_BOOT_PROJECT_DTO;

    public static JavaProjectModel SPRING_BOOT_JAVA_PROJECT_DTO;

    static {
        String processParentPath = new File("C:\\_dev\\vhs").getAbsolutePath();
        String processTempPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();
        PROCESS_DTO = new ProcessModel(processTempPath, processParentPath, "generator");
        List<ProjectModel> projectModels = PROCESS_DTO.getProjects();

        String javaBasePackage = "com.dbr.generator.springboot.app";

        SPRING_BOOT_PROJECT_DTO = new SpringBootProjectModel(PROCESS_DTO, "springboot", javaBasePackage);
        SPRING_BOOT_PROJECT_DTO.setAddSpringBootSecurityModule(true);
        //projectDTOS.add(SPRING_BOOT_PROJECT_DTO);

        SPRING_BOOT_JAVA_PROJECT_DTO = new JavaProjectModel(PROCESS_DTO, "springboot", javaBasePackage);
        projectModels.add(SPRING_BOOT_JAVA_PROJECT_DTO);

        for (String name : new String[]{"Property", "Item", "Project", "Process"}) {
            ItemModel itemModel = new ItemModel(SPRING_BOOT_JAVA_PROJECT_DTO, name, ItemType.JAVA, TypeEnum.TYPE_LONG, TemplateEnum.DTO_TEMPLATE, TemplateEnum.CLAZZ_MAPPING_TEMPLATE, TemplateEnum.SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE, TemplateEnum.SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE);
            SPRING_BOOT_JAVA_PROJECT_DTO.getItems().add(itemModel);
        }

    }

    public static void main(String[] args) throws Exception {
        ProcessGenerator.generate(PROCESS_DTO);
    }

}
