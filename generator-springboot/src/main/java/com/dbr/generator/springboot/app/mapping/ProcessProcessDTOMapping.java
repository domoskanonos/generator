package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.dto.ProcessDTO;
import com.dbr.generator.basic.entity.Process;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessProcessDTOMapping {

    final ProjectProjectDTOMapping projectProjectDTOMapping;

    public ProcessProcessDTOMapping(ProjectProjectDTOMapping projectProjectDTOMapping) {
        this.projectProjectDTOMapping = projectProjectDTOMapping;
    }

    public Process toEntity(ProcessDTO source) {
        Process dest = new Process();
        BeanUtils.copyProperties(source, dest, "projects");
        dest.setProjects(projectProjectDTOMapping.toEntities(source.getProjects()));
        return dest;
    }

    public ProcessDTO toDTO(Process source) {
        ProcessDTO dest = new ProcessDTO();
        BeanUtils.copyProperties(source, dest, "projects");
        dest.setProjects(projectProjectDTOMapping.toDTOs(source.getProjects()));
        return dest;
    }

    public List<ProcessDTO> toDTOs(Collection<Process> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<Process> toEntities(Collection<ProcessDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
