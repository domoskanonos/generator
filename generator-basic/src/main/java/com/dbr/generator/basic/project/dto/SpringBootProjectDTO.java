package com.dbr.generator.basic.project.dto;

import com.dbr.generator.basic.process.dto.ProcessDTO;
import lombok.Data;

import java.io.File;

@Data
public class SpringBootProjectDTO extends JavaProjectDTO {

    public SpringBootProjectDTO(ProcessDTO processDTO, String technicalDescriptor, String javaBasePackage) {
        super(processDTO, technicalDescriptor, javaBasePackage);
    }

    private String springBootTemplateZipUrl = "https://github.com/domoskanonos/spring-boot-template/archive/master.zip";
    private String springBootTemplateFilename = "spring-boot-template-master";
    private String springBootTemplateZipFilename = new StringBuilder().append(springBootTemplateFilename).append(".zip")
            .toString();
    private Boolean addSpringBootMailRestController = false;
    private Boolean addSpringBootSecurityModule = false;
    private Boolean addSpringBootStorageModule = false;

    @Override
    public String getJavaBasePackage() {
        return new StringBuilder().append(super.getJavaBasePackage()).append(".springboot").toString();
    }

    public File getSpringBootTemplateFolder() {
        return new File(getProcessDTO().getProcessTempPath(), this.springBootTemplateFilename);
    }

    public File getSpringBootTemplateZipFile() {
        return new File(getProcessDTO().getProcessTempPath(), getSpringBootTemplateZipFilename());
    }

    public String getSpringBootProjectArtifactId() {
        return new StringBuilder().append(getProcessDTO().getTechnicalDescriptor()).append("-springboot").toString();
    }

    public File getSpringBootProjectFolder() {
        return new File(getProcessDTO().getProcessFolder(), getSpringBootProjectArtifactId());
    }

    public File getSpringBootProjectSourceFolder() {
        return new File(getSpringBootProjectFolder(), "src/main/java");
    }

    public File getSpringBootProjectTestSourceFolder() {
        return new File(getSpringBootProjectFolder(), "src/test/java");
    }

    public File getSpringBootProjectResourceFolder() {
        return new File(getSpringBootProjectFolder(), "src/main/resources");
    }

    public File getSpringBootProjectSourceBasePackageFolder() {
        return new File(getSpringBootProjectSourceFolder(), getJavaBasePackage().replaceAll("\\.", "\\/"));
    }

    public File getSpringBootProjectTestSourceBasePackageFolder() {
        return new File(getSpringBootProjectTestSourceFolder(), getJavaBasePackage().replaceAll("\\.", "\\/"));
    }

    public File getProcessTempFolder() {
        return getProcessDTO().getProcessTempFolder();
    }

    public File getProcessFolder() {
        return getProcessDTO().getProcessFolder();
    }

}
