package com.dbr.generator;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.ProcessDTO;
import com.dbr.generator.basic.dto.project.JavaProjectDTO;
import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.dto.project.SpringBootProjectDTO;
import com.dbr.generator.basic.enumeration.ItemType;
import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.generator.process.ProcessGenerator;
import com.dbr.generator.basic.merger.TemplateEnum;

import java.io.File;
import java.util.List;

public class GeneratorProjectMetaData {

    public static ProcessDTO PROCESS_DTO;

    public static SpringBootProjectDTO SPRING_BOOT_PROJECT_DTO;

    public static JavaProjectDTO SPRING_BOOT_JAVA_PROJECT_DTO;

    static {
        String processParentPath = new File("C:\\_dev\\vhs\\git").getAbsolutePath();
        String processTempPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();
        PROCESS_DTO = new ProcessDTO(processTempPath, processParentPath, "generator");
        List<ProjectDTO> projectDTOS = PROCESS_DTO.getProjects();

        String javaBasePackage = "com.dbr.generator.springboot.app";

        SPRING_BOOT_PROJECT_DTO = new SpringBootProjectDTO(PROCESS_DTO, "springboot", javaBasePackage);
        SPRING_BOOT_PROJECT_DTO.setAddSpringBootSecurityModule(true);
        //projectDTOS.add(SPRING_BOOT_PROJECT_DTO);

        SPRING_BOOT_JAVA_PROJECT_DTO = new JavaProjectDTO(PROCESS_DTO, "springboot", javaBasePackage);
        projectDTOS.add(SPRING_BOOT_JAVA_PROJECT_DTO);

        for (String name : new String[]{"Property", "Item", "Project", "Process"}) {
            SPRING_BOOT_JAVA_PROJECT_DTO.getItems().add(new ItemDTO(name, ItemType.JAVA, TypeEnum.TYPE_LONG, TemplateEnum.SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE, TemplateEnum.SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE));
        }


    }

    public static void main(String[] args) throws Exception {
        ProcessGenerator.generate(PROCESS_DTO);
    }

}
