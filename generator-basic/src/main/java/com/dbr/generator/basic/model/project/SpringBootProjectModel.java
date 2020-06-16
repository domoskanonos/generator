package com.dbr.generator.basic.model.project;

import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.model.ProcessModel;
import lombok.Data;

import java.io.File;

@Data
public class SpringBootProjectModel extends JavaProjectModel {

    public SpringBootProjectModel(ProcessModel processModel, String technicalDescriptor, String javaBasePackage, Template... projectTemplates) {
        super(processModel, technicalDescriptor, javaBasePackage, projectTemplates);
    }

    private String springBootTemplateZipUrl = "https://github.com/domoskanonos/spring-boot-template/archive/master.zip";
    private String springBootTemplateFilename = "spring-boot-template-master";
    private String springBootTemplateZipFilename = new StringBuilder().append(springBootTemplateFilename).append(".zip")
            .toString();
    private Boolean addSpringBootMailRestController = false;
    private Boolean addSpringBootSecurityModule = false;
    private Boolean addSpringBootStorageModule = false;

    @Override
    public String getNamespace() {
        return new StringBuilder().append(super.getNamespace()).append(".springboot").toString();
    }

    public File getSpringBootTemplateFolder() {
        return new File(getProcessModel().getProcessTempPath(), this.springBootTemplateFilename);
    }

    public File getSpringBootTemplateZipFile() {
        return new File(getProcessModel().getProcessTempPath(), getSpringBootTemplateZipFilename());
    }

    public String getSpringBootProjectArtifactId() {
        return new StringBuilder().append(getProcessModel().getTechnicalDescriptor()).append("-springboot").toString();
    }

    public File getSpringBootProjectFolder() {
        return new File(getProcessModel().getProcessFolder(), getSpringBootProjectArtifactId());
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
        return new File(getSpringBootProjectSourceFolder(), getNamespace().replaceAll("\\.", "\\/"));
    }

    public File getSpringBootProjectTestSourceBasePackageFolder() {
        return new File(getSpringBootProjectTestSourceFolder(), getNamespace().replaceAll("\\.", "\\/"));
    }

    public File getProcessTempFolder() {
        return getProcessModel().getProcessTempFolder();
    }

    public File getProcessFolder() {
        return getProcessModel().getProcessFolder();
    }

}
