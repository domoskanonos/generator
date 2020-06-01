package com.dbr.generator.springboot.util;

import com.dbr.generator.basic.process.ProcessGenerator;
import com.dbr.generator.basic.dto.ProcessDTO;
import com.dbr.generator.basic.dto.project.JavaProjectDTO;
import com.dbr.generator.basic.dto.project.SpringBootProjectDTO;
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
        //processDTO.getProjectDTOS().add(springBootProjectDTO);


        JavaProjectDTO javaProjectDTO = new JavaProjectDTO(processDTO, "springboot", "com.dbr.generator");
        processDTO.getProjectDTOS().add(javaProjectDTO);


        //NidocaProjectDTO nidocaProjectDTO = new NidocaProjectDTO(processDTO, "nidoca");
        //processDTO.getProjectDTOS().add(nidocaProjectDTO);

        ProcessGenerator.generate(processDTO);

    }





}
