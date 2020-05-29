package com.dbr.generator.main;

import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.generator.main.model[0].MainGeneratorModel;
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
        mainGeneratorModel.setRootFolder(new File("D:\\_dev\\vhs\\git"));
        mainGeneratorModel.setTechnicalDescriptor("ocivap");
        mainGeneratorModel.setProjectJavaPackageBaseName("com.dbr.ocivap");

        mainGeneratorModel.setUseSpringBootTemplate(false);
        mainGeneratorModel.setAddSpringBootSecurityModule(false);

        mainGeneratorModel.setUseNidocaClient(true);

        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.generate(mainGeneratorModel);
    }

    public void generate(MainGeneratorModel model) throws IOException, InterruptedException {

        model[0].validate();

        logger.info("generate project start...");

        GeneratorUtil.makeDir(model[0].getTempFolder());
        GeneratorUtil.makeDir(model[0].getRootFolder());
        GeneratorUtil.makeDir(model[0].getProjectFolder());

        if (model[0].getUseSpringBootTemplate()) {
            deleteFile(model[0].getSpringBootProjectFolder());
            File springBootZipFile = copyUrlToTempFolder(model[0].getSpringBootTemplateZipUrl(),
                    model[0].getSpringBootTemplateZipFile());
            unzipFile(springBootZipFile, model[0].getTempFolder());
            createMavenArchetype(model[0].getSpringBootTemplateFolder());
            createFromArchetype(model[0].getProjectFolder(), model[0].getSpringBootProjectArtifactId(),
                    model[0].getSpringBootProjectGroupId(), model[0].getSpringBootArchetypeArtifactId(),
                    model[0].getSpringBootGroupId());

            if (!model[0].getAddSpringBootMailRestController()) {
                deleteFile(new File(model[0].getSpringBootProjectSourceBasePackageFolder(), "system/mail/rest/MailRestController.java"));
            }

            if (!model[0].getAddSpringBootSecurityModule()) {
                deleteFile(new File(model[0].getSpringBootProjectSourceBasePackageFolder(), "system/auth"));
                deleteFile(new File(model[0].getSpringBootProjectResourceFolder(), "mail"));
                deleteFile(new File(model[0].getSpringBootProjectResourceFolder(), "public/login.html"));
                deleteFile(new File(model[0].getSpringBootProjectResourceFolder(), "application-disable-security.properties"));
            }

            if (!model[0].getAddSpringBootStorageModule()) {
                deleteFile(new File(model[0].getSpringBootProjectSourceBasePackageFolder(), "system/storage"));
                deleteFile(new File(model[0].getSpringBootProjectResourceFolder(), "upload.properties"));
            }

        }

        if (model[0].getUseNidocaClient()) {
            deleteFile(model[0].getNidocaProjectFolder());
            File nidocaTemplateZipFile = copyUrlToTempFolder(model[0].getNidocaTemplateZipUrl(),
                    model[0].getNidocaTemplateZipFile());
            unzipFile(nidocaTemplateZipFile, model[0].getProjectFolder());
            FileUtils.moveDirectory(new File(model[0].getProjectFolder(), model[0].getNidocaTemplateFilename()), model[0].getNidocaProjectFolder());
        }


        logger.info("generate project end...");

    }

    private void deleteFile(File file) throws IOException {
        String absolutePath = file.getAbsolutePath();
        if (file.exists()) {

            if (file.isDirectory()) {
                logger.info("delete directory recursive: {}", absolutePath);
                FileUtils.deleteDirectory(file);
                return;
            }

            logger.info("delete file: {}", absolutePath);
            if (file.delete()) {
                logger.info("file deleted: {}", absolutePath);
            }
        } else {
            logger.info("file not exist: {}", absolutePath);
        }
    }

    private void createFromArchetype(File folder, String artifactId, String groupId, String archetypeArtifactId,
                                     String archetypeGroupId) throws IOException, InterruptedException {
        logger.info("execute create from maven archetype command, path: {}", folder.getAbsolutePath());
        String createFromArchetypeCommand = new StringBuilder().append("mvn -DgroupId=").append(groupId).append(" -DartifactId=").append(artifactId)
                .append(" -Dversion=1.0.0 archetype:generate -B -DarchetypeGroupId=").append(archetypeGroupId)
                .append(" -DarchetypeArtifactId=").append(archetypeArtifactId)
                .append(" -DarchetypeVersion=1.0.0 -DarchetypeRepository=local").toString();
        logger.info("execute create from maven archetype command, command: {}", createFromArchetypeCommand);
        SystemUtil.executeCommand(folder, "cmd.exe", "/C",
                createFromArchetypeCommand);
    }

    private void createMavenArchetype(File folder) throws IOException, InterruptedException {
        logger.info("execute create maven archetype command, path: {}", folder.getAbsolutePath());
        SystemUtil.executeCommand(folder, "cmd.exe", "/C", "create-maven-archetype.bat");
    }

    private void unzipFile(File file, File destination) throws IOException {
        logger.info("unzip file, file: {}", file.getAbsolutePath());
        ZipUtil.unzipFile(file, destination);
    }

    private File copyUrlToTempFolder(String url, File destination) throws IOException {
        logger.info("copy url to temp folder, url: {}", url);
        FileUtils.copyURLToFile(new URL(url), destination, 3000, 3000);
        return destination;
    }

}
