package com.dbr.generator.basic.process;

import com.dbr.generator.basic.process.dto.ProcessDTO;
import com.dbr.generator.basic.project.ProjectFactory;
import com.dbr.generator.basic.project.ProjectGeneratorInterface;
import com.dbr.generator.basic.project.dto.ProjectDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ProcessGenerator {

    private static final Logger logger = LoggerFactory.getLogger(ProcessGenerator.class);

    public static void generate(ProcessDTO processDTO) throws Exception {

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
        }

        logger.info("generate project end...");

    }

}
