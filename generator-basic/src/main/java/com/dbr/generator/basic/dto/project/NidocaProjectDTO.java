package com.dbr.generator.basic.dto.project;

import com.dbr.generator.basic.dto.ProcessDTO;
import lombok.Data;

import java.io.File;

@Data
public class NidocaProjectDTO extends ProjectDTO {

    public NidocaProjectDTO(ProcessDTO processDTO, String technicalDescriptor) {
        super(processDTO, technicalDescriptor);
    }

    private Boolean useNidocaClient = false;
    private String nidocaTemplateZipUrl = "https://github.com/domoskanonos/nidoca-template-dashboard/archive/master.zip";
    private String nidocaTemplateFilename = "nidoca-template-dashboard-master";
    private String nidocaTemplateZipFilename = new StringBuilder().append(nidocaTemplateFilename).append(".zip")
            .toString();

    public File getNidocaTemplateZipFile() {
        return new File(getProcessDTO().getProcessTempPath(), nidocaTemplateZipFilename);
    }

    public String getNidocaProjectArtifactId() {
        return new StringBuilder().append(getProcessDTO().getTechnicalDescriptor()).append("-nidoca").toString();
    }

    public File getNidocaProjectFolder() {
        return new File(getProcessDTO().getProcessFolder(), getNidocaProjectArtifactId());
    }


    public File getProcessFolder() {
        return getProcessDTO().getProcessFolder();
    }
}
