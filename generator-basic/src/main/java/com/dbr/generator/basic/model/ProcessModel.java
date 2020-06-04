package com.dbr.generator.basic.model;

import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.basic.util.ValidationUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProcessModel {

    private String processTempPath;

    private String processParentPath;

    private String technicalDescriptor;

    private List<ProjectModel> projects = new ArrayList<>();

    public ProcessModel(String processTempPath, String processParentPath, String technicalDescriptor) {
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
