package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.util.ValidationUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PROCESS_PROJECT", joinColumns = @JoinColumn(name = "PROCESS_ID", nullable = false, updatable = false, referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PROJECT_ID", nullable = false, updatable = false, referencedColumnName = "ID"))
    private Set<Item> projects = new HashSet<>();

    private String processTempPath;

    private String processParentPath;

    private String technicalDescriptor;

    private List<ProjectDTO> projectDTOS = new ArrayList<>();

    public Process(String processTempPath, String processParentPath, String technicalDescriptor) {
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
