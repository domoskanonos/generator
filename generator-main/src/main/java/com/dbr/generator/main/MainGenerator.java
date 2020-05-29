package com.dbr.generator.main;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ConverterDTO;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.ProjectDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
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
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setTempFolder(new File(System.getProperty("java.io.tmpdir"), "generator"));
        projectDTO.setRootFolder(new File("C:\\_dev\\vhs"));
        projectDTO.setTechnicalDescriptor("generator");
        projectDTO.setJavaBasePackage("com.dbr.generator");

        projectDTO.setUseSpringBootTemplate(true);
        projectDTO.setAddSpringBootSecurityModule(false);

        projectDTO.setUseNidocaClient(false);

        projectDTO.getObjects().add(new JavaClass2ItemDTOConverter().convert(new ConverterDTO(projectDTO, ProjectDTO.class)));
        projectDTO.getObjects().add(new JavaClass2ItemDTOConverter().convert(new ConverterDTO(projectDTO, ItemDTO.class)));
        projectDTO.getObjects().add(new JavaClass2ItemDTOConverter().convert(new ConverterDTO(projectDTO, PropertyDTO.class)));

        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.generate(projectDTO);
    }

    public void generate(ProjectDTO model) throws IOException, InterruptedException {

        model.validate();

        logger.info("generate project start...");

        File tempFolder = model.getTempFolder();
        if (tempFolder.exists()) {
            FileUtils.deleteDirectory(tempFolder);
        }
        GeneratorUtil.makeDir(tempFolder);
        GeneratorUtil.makeDir(model.getRootFolder());
        GeneratorUtil.makeDir(model.getProjectFolder());

        if (model.getUseSpringBootTemplate()) {
            deleteFile(model.getSpringBootProjectFolder());
            File springBootZipFile = copyUrlToTempFolder(model.getSpringBootTemplateZipUrl(),
                    model.getSpringBootTemplateZipFile());
            unzipFile(springBootZipFile, tempFolder);
            createMavenArchetype(model.getSpringBootTemplateFolder());
            createFromArchetype(model.getProjectFolder(), model.getSpringBootProjectArtifactId(),
                    model.getSpringBootProjectGroupId(), model.getSpringBootArchetypeArtifactId(),
                    model.getSpringBootGroupId());

            if (!model.getAddSpringBootMailRestController()) {
                deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/mail/rest/MailRestController.java"));
            }

            if (!model.getAddSpringBootSecurityModule()) {
                deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/auth"));
                deleteFile(new File(model.getSpringBootProjectResourceFolder(), "public/login.html"));
                deleteFile(new File(model.getSpringBootProjectResourceFolder(), "application-disable-security.properties"));
            }

            if (!model.getAddSpringBootStorageModule()) {
                deleteFile(new File(model.getSpringBootProjectSourceBasePackageFolder(), "system/storage"));
                deleteFile(new File(model.getSpringBootProjectTestSourceBasePackageFolder(), "system/storage"));
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
