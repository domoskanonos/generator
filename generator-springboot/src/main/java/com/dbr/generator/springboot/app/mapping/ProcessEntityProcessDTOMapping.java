package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.entity.ProcessEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessEntityProcessDTOMapping {

    final ProjectEntityProjectDTOMapping projectEntityProjectDTOMapping;

    public ProcessEntityProcessDTOMapping(ProjectEntityProjectDTOMapping projectEntityProjectDTOMapping) {
        this.projectEntityProjectDTOMapping = projectEntityProjectDTOMapping;
    }

    public ProcessEntity toEntity(ProcessModel source) {
        ProcessEntity dest = new ProcessEntity();
        BeanUtils.copyProperties(source, dest, "projects");
        dest.setProjectEntities(projectEntityProjectDTOMapping.toEntities(source.getProjects()));
        return dest;
    }

    public ProcessModel toDTO(ProcessEntity source) {
        ProcessModel dest = new ProcessModel();
        BeanUtils.copyProperties(source, dest, "projects");
        dest.setProjects(projectEntityProjectDTOMapping.toDTOs(source.getProjectEntities()));
        return dest;
    }

    public List<ProcessModel> toDTOs(Collection<ProcessEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ProcessEntity> toEntities(Collection<ProcessModel> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
