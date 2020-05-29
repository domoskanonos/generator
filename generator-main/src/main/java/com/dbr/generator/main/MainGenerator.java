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

        model.validate();

        logger.info("generate project start...");

        GeneratorUtil.makeDir(model.getTempFolder());
        GeneratorUtil.makeDir(model.getRootFolder());
        GeneratorUtil.makeDir(model.getProjectFolder());

        if (model.getUseSpringBootTemplate()) {
            deleteFile(model.getSpringBootProjectFolder());
            File springBootZipFile = copyUrlToTempFolder(model.getSpringBootTemplateZipUrl(),
                    model.getSpringBootTemplateZipFile());
            unzipFile(springBootZipFile, model.getTempFolder());
            createMavenArchetype(model.getSpringBootTemplateFolder());
            createFromArchetype(model.getProjectFolder(), model.getSpringBootProjectArtifactId(),
                    model.getSpringBootProjectGroupId(), model.getSpringBootArchetypeArtifactId(),
                    model.getSpringBootGroupId());

            if (!model.getAddSpringBootMailRestController()) {
                deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/mail/rest/MailRestController.java"));
            }

            if (!model.getAddSpringBootSecurityModule()) {
                deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/auth"));
                deleteFile(new File(model.getSpringBootProjectResourceFolder(), "mail"));
                deleteFile(new File(model.getSpringBootProjectResourceFolder(), "public/login.html"));
                deleteFile(new File(model.getSpringBootProjectResourceFolder(), "application-disable-security.properties"));
            }

            if (!model.getAddSpringBootStorageModule()) {
                deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/storage"));
                deleteFile(new File(model.getSpringBootProjectResourceFolder(), "upload.properties"));
            }

        }

        if (model.getUseNidocaClient()) {
            deleteFile(model.getNidocaProjectFolder());
            File nidocaTemplateZipFile = copyUrlToTempFolder(model.getNidocaTemplateZipUrl(),
                    model.getNidocaTemplateZipFile());
            unzipFile(nidocaTemplateZipFile, model.getProjectFolder());
            FileUtils.moveDirectory(new File(model.getProjectFolder(), model.getNidocaTemplateFilename()), model.getNidocaProjectFolder());
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
