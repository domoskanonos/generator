package com.dbr.generator.main.model;

import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.util.StringUtil;
import lombok.Data;

import java.io.File;

@Data
public class MainGeneratorModel {

    private File tempFolder;

    private File rootFolder;

    private String technicalDescriptor;

    private String projectJavaPackageBaseName;

    private Boolean useSpringBootTemplate = false;
    private String springBootTemplateZipUrl = "https://github.com/domoskanonos/spring-boot-template/archive/master.zip";
    private String springBootTemplateFilename = "spring-boot-template-master";
    private String springBootTemplateZipFilename = new StringBuilder().append(springBootTemplateFilename).append(".zip")
            .toString();
    private String springBootArchetypeArtifactId = "spring-boot-template-archetype";
    private String springBootGroupId = "com.dbr.springboot.template";
    private Boolean addSpringBootMailRestController = false;
    private Boolean addSpringBootSecurityModule = false;
    private Boolean addSpringBootStorageModule = false;

    private Boolean useNidocaClient = false;
    private String nidocaTemplateZipUrl = "https://github.com/domoskanonos/nidoca-template-dashboard/archive/master.zip";
    private String nidocaTemplateFilename = "nidoca-template-dashboard-master";
    private String nidocaTemplateZipFilename = new StringBuilder().append(nidocaTemplateFilename).append(".zip")
            .toString();

    public File getProjectFolder() {
        return new File(rootFolder, technicalDescriptor);
    }

    public File getSpringBootTemplateFolder() {
        return new File(this.tempFolder, this.springBootTemplateFilename);
    }

    public File getSpringBootTemplateZipFile() {
        return new File(this.tempFolder, getSpringBootTemplateZipFilename());
    }

    public String getSpringBootProjectGroupId() {
        return new StringBuilder().append(projectJavaPackageBaseName).append(".springboot").toString();
    }

    public String getSpringBootProjectArtifactId() {
        return new StringBuilder().append(technicalDescriptor).append("-springboot").toString();
    }

    public File getSpringBootProjectFolder() {
        return new File(this.getProjectFolder(), getSpringBootProjectArtifactId());
    }

    public File getSpringBootProjectSourceFolder() {
        return new File(getSpringBootProjectFolder(), "src/main/java");
    }

    public File getSpringBootProjectResourceFolder() {
        return new File(getSpringBootProjectFolder(), "src/main/resources");
    }

    public File getSpringBootProjectSourceBasePackageFolder() {
        return new File(getSpringBootProjectSourceFolder(), getSpringBootProjectGroupId().replaceAll("\\.", "\\/"));
    }

    public File getNidocaTemplateFolder() {
        return new File(this.tempFolder, this.nidocaTemplateFilename);
    }

    public File getNidocaTemplateZipFile() {
        return new File(this.tempFolder, getNidocaTemplateZipFilename());
    }

    public String getNidocaProjectArtifactId() {
        return new StringBuilder().append(technicalDescriptor).append("-nidoca").toString();
    }

    public File getNidocaProjectFolder() {
        return new File(this.getProjectFolder(), getNidocaProjectArtifactId());
    }

    public void validate() {
        validateNotHDDBaseDirectory(getRootFolder());
        validateNotHDDBaseDirectory(getProjectFolder());
        validateNotHDDBaseDirectory(getSpringBootProjectFolder());
        validateDirectoryPathNotEqual(getRootFolder(), getProjectFolder());
    }

    private void validateDirectoryPathNotEqual(File folderOne, File folderTwo) {
        if (folderOne.getAbsolutePath().equals(folderTwo.getAbsolutePath())) {
            throw new RuntimeException(new StringBuilder().append("same folder error: ").append(folderOne.getAbsolutePath()).toString());
        }
    }

    private void validateNotHDDBaseDirectory(File folder) {
        if (folder.getAbsolutePath().length() < 4) {
            throw new RuntimeException(String.format("root folder can't be hdd base directory: %s", folder.getAbsolutePath()));
        }
    }


}
