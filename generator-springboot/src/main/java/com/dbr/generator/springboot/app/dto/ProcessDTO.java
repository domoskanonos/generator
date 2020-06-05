package com.dbr.generator.springboot.app.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProcessDTO {

    private Long id;
    private List<ProjectDTO> projects;
    private String processTempPath;
    private String processParentPath;
    private String technicalDescriptor;

    public void addProject(ProjectDTO project) {
        if (this.projects == null) {
            this.projects = new ArrayList<>();
        }
        this.projects.add(project);
    }
}