package com.dbr.generator.springboot.util;

import com.dbr.generator.basic.generator.process.ProcessGenerator;
import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.model.project.JavaProjectModel;
import com.dbr.generator.basic.model.project.SpringBootProjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MainGenerator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {

        String processParentPath = new File("C:\\_dev\\vhs").getAbsolutePath();
        String processTempPath = new File(System.getProperty("java.io.tmpdir"), "generator").getAbsolutePath();
        ProcessModel processModel = new ProcessModel(processTempPath, processParentPath, "generator");

        SpringBootProjectModel springBootProjectModel = new SpringBootProjectModel(processModel, "springboot", "com.dbr.generator");
        springBootProjectModel.setAddSpringBootSecurityModule(true);
        //processDTO.getProjectDTOS().add(springBootProjectDTO);


        JavaProjectModel javaProjectDTO = new JavaProjectModel(processModel, "springboot", "com.dbr.generator");
        processModel.getProjects().add(javaProjectDTO);


        //NidocaProjectDTO nidocaProjectDTO = new NidocaProjectDTO(processDTO, "nidoca");
        //processDTO.getProjectDTOS().add(nidocaProjectDTO);

        ProcessGenerator.generate(processModel);

    }





}
