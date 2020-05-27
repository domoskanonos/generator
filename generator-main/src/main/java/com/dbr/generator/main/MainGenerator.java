package com.dbr.generator.main;

import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.generator.main.model.MainGeneratorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MainGenerator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        MainGeneratorModel mainGeneratorModel = new MainGeneratorModel();
        mainGeneratorModel.setRootFolder(new File("C:\\_dev"));
        mainGeneratorModel.setTechnicalDescriptor("ocivap");
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.generate(mainGeneratorModel);
    }

    public void generate(MainGeneratorModel model) {
        logger.info("generate project start...");
        GeneratorUtil.makeDir(model.getRootFolder());
        GeneratorUtil.makeDir(model.getProjectFolder());
        logger.info("generate project end...");

    }


}
