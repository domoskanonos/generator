package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.entity.Process;
import com.dbr.generator.basic.entity.Project;
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


    public Process toEntity(ProcessDTO source) {
        Process dest = new Process();
        BeanUtils.copyProperties(source, dest, "projects");
        for (ProjectDTO project : source.getProjects()) {
            dest.addProject(projectEntityProjectDTOMapping.toEntity(project));
        }
        return dest;
    }

    public ProcessDTO toDTO(Process source) {
        ProcessDTO dest = new ProcessDTO();
        BeanUtils.copyProperties(source, dest, "projects");
        for (Project project : source.getProjects()) {
            dest.addProject(projectEntityProjectDTOMapping.toDTO(project));
        }
        return dest;
    }

    public List<ProcessDTO> toDTOs(Collection<Process> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<Process> toEntities(Collection<ProcessDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }


}