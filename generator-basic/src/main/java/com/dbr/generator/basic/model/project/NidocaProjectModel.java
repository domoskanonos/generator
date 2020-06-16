package com.dbr.generator.basic.model.project;

import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.model.ProcessModel;
import lombok.Data;

import java.io.File;

@Data
public class NidocaProjectModel extends ProjectModel {

    public NidocaProjectModel(ProcessModel processModel, String technicalDescriptor, Template... projectTemplates) {
        super(processModel, technicalDescriptor,projectTemplates);
    }

    private Boolean useNidocaClient = false;
    private String nidocaTemplateZipUrl = "https://github.com/domoskanonos/nidoca-template-dashboard/archive/master.zip";
    private String nidocaTemplateFilename = "nidoca-template-dashboard-master";
    private String nidocaTemplateZipFilename = new StringBuilder().append(nidocaTemplateFilename).append(".zip")
            .toString();

    public File getNidocaTemplateZipFile() {
        return new File(getProcessModel().getProcessTempPath(), nidocaTemplateZipFilename);
    }

    public String getNidocaProjectArtifactId() {
        return new StringBuilder().append(getProcessModel().getTechnicalDescriptor()).append("-nidoca").toString();
    }

    public File getNidocaProjectFolder() {
        return new File(getProcessModel().getProcessFolder(), getNidocaProjectArtifactId());
    }

    public File getProcessTempFolder() {
        return getProcessModel().getProcessTempFolder();
    }

    public File getProcessFolder() {
        return getProcessModel().getProcessFolder();
    }
}
