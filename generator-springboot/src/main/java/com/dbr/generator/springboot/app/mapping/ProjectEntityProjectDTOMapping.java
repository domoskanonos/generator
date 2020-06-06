package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.entity.ItemEntity;
import com.dbr.generator.basic.entity.ProjectEntity;
import com.dbr.generator.springboot.app.dto.ItemDTO;
import com.dbr.generator.springboot.app.dto.ProjectDTO;
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

    public ProjectEntity toEntity(ProjectDTO source) {
        ProjectEntity dest = new ProjectEntity();
        BeanUtils.copyProperties(source, dest, "items");
        for (ItemDTO item : source.getItems()) {
            dest.addItem(itemEntityItemDTOMapping.toEntity(item));
        }
        return dest;
    }

    public ProjectDTO toDTO(ProjectEntity source) {
        ProjectDTO dest = new ProjectDTO();
        BeanUtils.copyProperties(source, dest, "items");
        for (ItemEntity item : source.getItems()) {
            dest.addItem(itemEntityItemDTOMapping.toDTO(item));
        }
        return dest;
    }

    public List<ProjectDTO> toDTOs(Collection<ProjectEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ProjectEntity> toEntities(Collection<ProjectDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }


}