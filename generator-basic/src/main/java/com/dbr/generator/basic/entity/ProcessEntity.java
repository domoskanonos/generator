package com.dbr.generator.basic.entity;

import com.dbr.generator.basic.util.ValidationUtil;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROCESS")
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "PROCESS_PROJECT", joinColumns = @JoinColumn(name = "PROCESS_ID", nullable = false, updatable = false, referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "PROJECT_ID", nullable = false, updatable = false, referencedColumnName = "ID"))
    private List<ProjectEntity> projectEntities = new ArrayList<>();

    private String processTempPath;

    private String processParentPath;

    private String technicalDescriptor;

    public ProcessEntity(String processTempPath, String processParentPath, String technicalDescriptor) {
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
