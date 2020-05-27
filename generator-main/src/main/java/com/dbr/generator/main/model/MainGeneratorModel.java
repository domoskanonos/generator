package com.dbr.generator.main.model;

import lombok.Data;

import java.io.File;

@Data
public class MainGeneratorModel {

    private File tempFolder;

    private File rootFolder;

    private String technicalDescriptor;

    private Boolean useSpringBootTemplate = false;
    private String springBootTemplateZipUrl = "https://github.com/domoskanonos/spring-boot-template/archive/master.zip";
    private String springBootTemplateFilename = "spring-boot-template-master";
    private String springBootTemplateZipFilename = new StringBuilder().append(springBootTemplateFilename).append(".zip").toString();

    public File getProjectFolder() {
        return new File(rootFolder, technicalDescriptor);
    }

    public File getSpringBootTemplateFolder() {
        return new File(this.tempFolder, this.springBootTemplateFilename);
    }

    public File getSpringBootTemplateZipFile() {
        return new File(this.tempFolder, getSpringBootTemplateZipFilename());
    }
}
