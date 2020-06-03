package com.dbr.generator.springboot.app.mapping;


import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.entity.Project;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectProjectDTOMapping {

    final ItemItemDTOMapping itemItemDTOMapping;

    public ProjectProjectDTOMapping(ItemItemDTOMapping itemItemDTOMapping) {
        this.itemItemDTOMapping = itemItemDTOMapping;
    }

    public Project toEntity(ProjectDTO source) {
        Project dest = new Project();
        BeanUtils.copyProperties(source, dest, "items");
        dest.setItems(this.itemItemDTOMapping.toEntities(source.getItems()));
        return dest;
    }

    public ProjectDTO toDTO(Project source) {
        ProjectDTO dest = new ProjectDTO();
        BeanUtils.copyProperties(source, dest, "items");
        dest.setItems(this.itemItemDTOMapping.toDTOs(source.getItems()));
        return dest;
    }


    public List<ProjectDTO> toDTOs(Collection<Project> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<Project> toEntities(Collection<ProjectDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
