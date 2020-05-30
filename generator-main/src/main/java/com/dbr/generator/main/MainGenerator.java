package com.dbr.generator.main;

import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.converter.dto.ItemConverterDTO;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.dto.DTOItemMergerDTO;
import com.dbr.generator.basic.process.dto.ProcessDTO;
import com.dbr.generator.basic.project.dto.ProjectDTO;
import com.dbr.generator.basic.project.dto.SpringBootProjectDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class MainGenerator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessDTO processDTO = new ProcessDTO(new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath(), new File("C:\\_dev\\vhs\\1").getAbsolutePath(), "generator");

        SpringBootProjectDTO springBootProjectDTO = new SpringBootProjectDTO(processDTO, "springboot", "");
        springBootProjectDTO.setJavaBasePackage("com.dbr.generator");
        springBootProjectDTO.setUseSpringBootTemplate(true);
        springBootProjectDTO.setAddSpringBootSecurityModule(false);

        ItemConverterDTO projectDTO2itemDTOConverterDTO = new ItemConverterDTO(springBootProjectDTO, springBootProjectDTO.getJavaDTOPackageName(), ProjectDTO.class);
        ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert(projectDTO2itemDTOConverterDTO);
        DTOItemMergerDTO dtoItemMergerDTO = new DTOItemMergerDTO(itemDTO);
        springBootProjectDTO.getItemMergerDTOS().add(dtoItemMergerDTO);

        processDTO.getProjectDTOS().add(springBootProjectDTO);

        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.generate(processDTO);

    }

    public void generate(ProcessDTO processDTO) throws IOException, InterruptedException {

        processDTO.validate();

        logger.info("generate project start...");

        File tempFolder = processDTO.getProcessTempFolder();
        if (tempFolder.exists()) {
            FileUtils.deleteDirectory(tempFolder);
        }
        GeneratorUtil.makeDir(tempFolder);
        GeneratorUtil.makeDir(processDTO.getProcessParentFolder());
        GeneratorUtil.makeDir(processDTO.getProcessFolder());







        logger.info("generate project end...");

    }



}
