package com.dbr.generator.main;

import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.ItemMergerFactory;
import com.dbr.generator.basic.item.merger.dto.DTOItemMergerDTO;
import com.dbr.generator.basic.item.merger.dto.EntityItemMergerDTO;
import com.dbr.generator.basic.item.merger.dto.ItemMergerDTO;
import com.dbr.generator.basic.process.dto.ProcessDTO;
import com.dbr.generator.basic.project.ProjectFactory;
import com.dbr.generator.basic.project.ProjectGeneratorInterface;
import com.dbr.generator.basic.project.dto.ProjectDTO;
import com.dbr.generator.basic.project.dto.SpringBootProjectDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MainGenerator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {

        String processParentPath = new File("C:\\_dev\\vhs").getAbsolutePath();
        String processTempPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();
        ProcessDTO processDTO = new ProcessDTO(processTempPath, processParentPath, "generator");

        SpringBootProjectDTO springBootProjectDTO = new SpringBootProjectDTO(processDTO, "springboot", "com.dbr.generator");
        springBootProjectDTO.setAddSpringBootSecurityModule(true);


        ItemDTO itemDTOItemDTO = new JavaClass2ItemDTOConverter().convert(springBootProjectDTO.getProjectFolder().getAbsolutePath(), ItemDTO.class);
        itemDTOItemDTO.setJavaClazzName(new StringBuilder().append(springBootProjectDTO.getJavaDTOPackageName()).append(".ItemDTO").toString());
        ItemDTO itemDTOItemEntity = new JavaClass2ItemDTOConverter().convert(springBootProjectDTO.getProjectFolder().getAbsolutePath(), ItemDTO.class);
        itemDTOItemEntity.setJavaClazzName(new StringBuilder().append(springBootProjectDTO.getJavaEntityPackageName()).append(".Item").toString());


        springBootProjectDTO.getItemMergerDTOS().add(new EntityItemMergerDTO(itemDTOItemDTO));
        springBootProjectDTO.getItemMergerDTOS().add(new DTOItemMergerDTO(itemDTOItemDTO));
        //springBootProjectDTO.getItemMergerDTOS().add(new MappingClazzItemMergerDTO(itemDTOItemDTO));

        processDTO.getProjectDTOS().add(springBootProjectDTO);


        //NidocaProjectDTO nidocaProjectDTO = new NidocaProjectDTO(processDTO, "nidoca");
        //processDTO.getProjectDTOS().add(nidocaProjectDTO);

        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.generate(processDTO);

    }

    public void generate(ProcessDTO processDTO) throws Exception {

        processDTO.validate();

        logger.info("generate project start...");

        File tempFolder = processDTO.getProcessTempFolder();
        if (tempFolder.exists()) {
            FileUtils.deleteDirectory(tempFolder);
        }
        GeneratorUtil.makeDir(tempFolder);
        GeneratorUtil.makeDir(processDTO.getProcessParentFolder());
        GeneratorUtil.makeDir(processDTO.getProcessFolder());

        for (ProjectDTO projectDTO : processDTO.getProjectDTOS()) {
            logger.info("generate project, technical descriptor: {}", projectDTO.getTechnicalDescriptor());

            ProjectGeneratorInterface projectGeneratorInterface = ProjectFactory.create(projectDTO);
            projectGeneratorInterface.validate(projectDTO);
            projectGeneratorInterface.execute(projectDTO);

            for (ItemMergerDTO itemMergerDTO : projectDTO.getItemMergerDTOS()) {
                ItemMergerFactory.createMerger(itemMergerDTO).writeDown();
            }
        }

        logger.info("generate project end...");

    }



}
