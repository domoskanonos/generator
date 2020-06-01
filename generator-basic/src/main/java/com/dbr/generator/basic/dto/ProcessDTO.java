package com.dbr.generator.basic.dto;

import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.util.ValidationUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProcessDTO {

    private String processTempPath;

    private String processParentPath;

    private String technicalDescriptor;

    private List<ProjectDTO> projectDTOS = new ArrayList<>();

    public ProcessDTO(String processTempPath, String processParentPath, String technicalDescriptor) {
        this.processTempPath = processTempPath;
        this.processParentPath = processParentPath;
        this.technicalDescriptor = technicalDescriptor;
    }

    public File getProcessFolder() {
        return new File(processParentPath, technicalDescriptor);
    }

    public File getProcessParentFolder() {
        return new File(getProcessParentPath());
    }

    private File getProcessTempFile() {
        return new File(getProcessTempPath());
    }

    public File getProcessTempFolder() {
        return new File(getProcessTempPath());
    }

    public void validate() {
        ValidationUtil.validateNotHDDBaseDirectory(getProcessTempFile());
        ValidationUtil.validateNotHDDBaseDirectory(getProcessParentFolder());
        ValidationUtil.validateNotHDDBaseDirectory(getProcessFolder());
        ValidationUtil.validateDirectoryPathNotEqual(getProcessParentFolder(), getProcessFolder());
    }

    public String getProjectFolderPrefix() {
        return StringUtils.isNotBlank(technicalDescriptor) ? new StringBuilder().append(technicalDescriptor).append("-").toString() : "";
    }
}
