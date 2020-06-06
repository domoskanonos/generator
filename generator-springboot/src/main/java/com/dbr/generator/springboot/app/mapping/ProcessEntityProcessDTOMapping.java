package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.entity.ProcessEntity;
import com.dbr.generator.basic.entity.ProjectEntity;
import com.dbr.generator.springboot.app.dto.ProcessDTO;
import com.dbr.generator.springboot.app.dto.ProjectDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessEntityProcessDTOMapping {

    private final ProjectEntityProjectDTOMapping projectEntityProjectDTOMapping;

    public ProcessEntityProcessDTOMapping(ProjectEntityProjectDTOMapping projectEntityProjectDTOMapping) {
        this.projectEntityProjectDTOMapping = projectEntityProjectDTOMapping;
    }


    public ProcessEntity toEntity(ProcessDTO source) {
        ProcessEntity dest = new ProcessEntity();
        BeanUtils.copyProperties(source, dest, "projects");
        for (ProjectDTO project : source.getProjects()) {
            dest.addProject(projectEntityProjectDTOMapping.toEntity(project));
        }
        return dest;
    }

    public ProcessDTO toDTO(ProcessEntity source) {
        ProcessDTO dest = new ProcessDTO();
        BeanUtils.copyProperties(source, dest, "projects");
        for (ProjectEntity project : source.getProjects()) {
            dest.addProject(projectEntityProjectDTOMapping.toDTO(project));
        }
        return dest;
    }

    public List<ProcessDTO> toDTOs(Collection<ProcessEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ProcessEntity> toEntities(Collection<ProcessDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }


}