package com.dbr.generator.basic.generator.process;

import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.generator.project.ProjectGenerator;
import com.dbr.generator.basic.generator.project.ProjectGeneratorFactory;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.basic.util.GeneratorUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ProcessGenerator {

    private static final Logger logger = LoggerFactory.getLogger(ProcessGenerator.class);

    public static void generate(ProcessModel processModel) throws Exception {

        processModel.validate();

        logger.info("generate project start...");

        File tempFolder = processModel.getProcessTempFolder();
        if (tempFolder.exists()) {
            FileUtils.deleteDirectory(tempFolder);
        }
        GeneratorUtil.makeDir(tempFolder);
        GeneratorUtil.makeDir(processModel.getProcessParentFolder());
        GeneratorUtil.makeDir(processModel.getProcessFolder());

        for (ProjectModel projectModel : processModel.getProjects()) {
            logger.info("generate project, technical descriptor: {}", projectModel.getTechnicalDescriptor());

            ProjectGenerator projectGeneratorInterface = ProjectGeneratorFactory.create(projectModel);
            projectGeneratorInterface.validate(projectModel);
            projectGeneratorInterface.execute(projectModel);
        }

        logger.info("generate project end...");

    }

}
