package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.springboot.app.dto.ProcessDTO;
import com.dbr.generator.springboot.app.dto.ProjectDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessDTOProcessModelMapping {

    private final ProjectDTOProjectModelMapping projectDTOProjectModelMapping;

    public ProcessDTOProcessModelMapping(ProjectDTOProjectModelMapping projectDTOProjectModelMapping) {
        this.projectDTOProjectModelMapping = projectDTOProjectModelMapping;
    }

    public ProcessModel toModel(ProcessDTO source) {
        ProcessModel dest = new ProcessModel();
        BeanUtils.copyProperties(source, dest, "projects");
        for (ProjectDTO project : source.getProjects()) {
            dest.addProject(projectDTOProjectModelMapping.toModel(project));
        }
        return dest;
    }

    public ProcessDTO toDTO(ProcessModel source) {
        ProcessDTO dest = new ProcessDTO();
        BeanUtils.copyProperties(source, dest, "projects");
        for (ProjectModel project : source.getProjects()) {
            dest.addProject(projectDTOProjectModelMapping.toDTO(project));
        }
        return dest;
    }

    public List<ProcessDTO> toDTOs(Collection<ProcessModel> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ProcessModel> toModels(Collection<ProcessDTO> sources) {
        return sources.stream().map(source -> toModel(source)).collect(Collectors.toList());
    }


}