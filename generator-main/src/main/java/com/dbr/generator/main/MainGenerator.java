package com.dbr.generator.main;

import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.converter.dto.ItemConverterDTO;
import com.dbr.generator.basic.item.merger.dto.DTOItemMergerDTO;
import com.dbr.generator.basic.item.merger.dto.ItemMergerDTO;
import com.dbr.generator.basic.item.merger.ItemMergerFactory;
import com.dbr.generator.basic.process.dto.ProcessDTO;
import com.dbr.generator.basic.project.dto.ProjectDTO;
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
        ProcessDTO processDTO = new ProcessDTO();
        processDTO.setTempFolder(new File(System.getProperty("java.io.tmpdir"), "generator"));
        processDTO.setRootFolder(new File("C:\\_dev\\vhs"));
        processDTO.setTechnicalDescriptor("generator");
        processDTO.setJavaBasePackage("com.dbr.generator");

        processDTO.setUseSpringBootTemplate(true);
        processDTO.setAddSpringBootSecurityModule(false);

        processDTO.setUseNidocaClient(false);

        ProjectDTO projectDTO = new ProjectDTO();
        processDTO.setJavaBasePackage("com.dbr.generator");

        processDTO.getItemMergerDTOS().add(new DTOItemMergerDTO(new JavaClass2ItemDTOConverter().convert(new ItemConverterDTO(projectDTO, new StringBuilder().append(processDTO.getJavaBasePackage()).append(".dto").toString(), ProjectDTO.class))));

        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.generate(processDTO);
    }

    public void generate(ProcessDTO processDTO) throws IOException, InterruptedException {

        processDTO.validate();


        for (ItemMergerDTO itemMergerDTO : processDTO.getItemMergerDTOS()) {
            ItemMergerFactory.createMerger(itemMergerDTO).writeDown();
        }

        if (true)
            return;

        logger.info("generate project start...");

        File tempFolder = processDTO.getTempFolder();
        if (tempFolder.exists()) {
            FileUtils.deleteDirectory(tempFolder);
        }
        GeneratorUtil.makeDir(tempFolder);
        GeneratorUtil.makeDir(processDTO.getRootFolder());
        GeneratorUtil.makeDir(processDTO.getProcessFolder());

        if (processDTO.getUseSpringBootTemplate()) {
            deleteFile(processDTO.getSpringBootProjectFolder());
            File springBootZipFile = copyUrlToTempFolder(processDTO.getSpringBootTemplateZipUrl(),
                    processDTO.getSpringBootTemplateZipFile());
            unzipFile(springBootZipFile, tempFolder);
            createMavenArchetype(processDTO.getSpringBootTemplateFolder());
            createFromArchetype(processDTO.getProcessFolder(), processDTO.getSpringBootProjectArtifactId(),
                    processDTO.getSpringBootProjectGroupId(), processDTO.getSpringBootArchetypeArtifactId(),
                    processDTO.getSpringBootGroupId());

            if (!processDTO.getAddSpringBootMailRestController()) {
                deleteFile(new File(processDTO.getSpringBootProjectSourceBasePackageFolder(), "system/mail/rest/MailRestController.java"));
            }

            if (!processDTO.getAddSpringBootSecurityModule()) {
                deleteFile(new File(processDTO.getSpringBootProjectSourceBasePackageFolder(), "system/auth"));
                deleteFile(new File(processDTO.getSpringBootProjectResourceFolder(), "public/login.html"));
                deleteFile(new File(processDTO.getSpringBootProjectResourceFolder(), "application-disable-security.properties"));
            }

            if (!processDTO.getAddSpringBootStorageModule()) {
                deleteFile(new File(processDTO.getSpringBootProjectSourceBasePackageFolder(), "system/storage"));
                deleteFile(new File(processDTO.getSpringBootProjectTestSourceBasePackageFolder(), "system/storage"));
                deleteFile(new File(processDTO.getSpringBootProjectResourceFolder(), "upload.properties"));
            }

        }

        if (processDTO.getUseNidocaClient()) {
            deleteFile(processDTO.getNidocaProjectFolder());
            File nidocaTemplateZipFile = copyUrlToTempFolder(processDTO.getNidocaTemplateZipUrl(),
                    processDTO.getNidocaTemplateZipFile());
            unzipFile(nidocaTemplateZipFile, processDTO.getProcessFolder());
            FileUtils.moveDirectory(new File(processDTO.getProcessFolder(), processDTO.getNidocaTemplateFilename()), processDTO.getNidocaProjectFolder());
        }


        for (ItemMergerDTO itemMergerDTO : processDTO.getItemMergerDTOS()) {
            ItemMergerFactory.createMerger(itemMergerDTO).writeDown();
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
