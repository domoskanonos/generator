package com.dbr.generator.springboot.app.mapping;


import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.basic.entity.ProjectEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectEntityProjectDTOMapping {

    final ItemEntityItemDTOMapping itemEntityItemDTOMapping;

    public ProjectEntityProjectDTOMapping(ItemEntityItemDTOMapping itemEntityItemDTOMapping) {
        this.itemEntityItemDTOMapping = itemEntityItemDTOMapping;
    }

    public ProjectEntity toEntity(ProjectModel source) {
        ProjectEntity dest = new ProjectEntity();
        BeanUtils.copyProperties(source, dest, "items");
        dest.setItemEntities(this.itemEntityItemDTOMapping.toEntities(source.getItems()));
        return dest;
    }

    public ProjectModel toDTO(ProjectEntity source) {
        ProjectModel dest = new ProjectModel();
        BeanUtils.copyProperties(source, dest, "items");
        dest.setItems(this.itemEntityItemDTOMapping.toDTOs(source.getItemEntities()));
        return dest;
    }


    public List<ProjectModel> toDTOs(Collection<ProjectEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ProjectEntity> toEntities(Collection<ProjectModel> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
