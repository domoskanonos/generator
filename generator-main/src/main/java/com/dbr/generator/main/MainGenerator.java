package com.dbr.generator.main;

import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.generator.main.model.MainGeneratorModel;
import com.dbr.util.SystemUtil;
import com.dbr.util.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainGenerator {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws IOException, InterruptedException {
        MainGeneratorModel mainGeneratorModel = new MainGeneratorModel();
        mainGeneratorModel.setTempFolder(new File(System.getProperty("java.io.tmpdir"), "generator"));
        mainGeneratorModel.setRootFolder(new File("C:\\_dev"));
        mainGeneratorModel.setTechnicalDescriptor("ocivap");
        mainGeneratorModel.setUseSpringBootTemplate(true);
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.generate(mainGeneratorModel);
    }

    public void generate(MainGeneratorModel model) throws IOException, InterruptedException {
        logger.info("generate project start...");
        GeneratorUtil.makeDir(model.getTempFolder());
        GeneratorUtil.makeDir(model.getRootFolder());
        GeneratorUtil.makeDir(model.getProjectFolder());

        if (model.getUseSpringBootTemplate()) {
            File springBootZipFile = copyUrlToTempFolder(model.getSpringBootTemplateZipUrl(), model.getSpringBootTemplateZipFile());
            unzipFile(springBootZipFile, model.getTempFolder());
            createMavenArchetype(model.getSpringBootTemplateFolder());
        }

        logger.info("generate project end...");

    }

    private void createMavenArchetype(File folder) throws IOException, InterruptedException {
        logger.info("execute command, path: {}", folder.getAbsolutePath());
        try {
            Process process = Runtime.
                    getRuntime().
                    exec("cmd /c start \"\" create-maven-archetype.bat",null,folder);
            System.out.println(process.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    private void unzipFile(File file, File destination) throws IOException {
        logger.info("unzip file, file: {}", file.getAbsolutePath());
        ZipUtil.unzipFile(file, destination);
    }

    private File copyUrlToTempFolder(String url, File destination) throws IOException {
        logger.info("copy url to temp folder, url: {}", url);
        FileUtils.copyURLToFile(
                new URL(url),
                destination,
                2000,
                2000);
        return destination;
    }


}
