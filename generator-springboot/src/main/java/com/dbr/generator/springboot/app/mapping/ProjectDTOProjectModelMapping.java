package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.ProcessModel;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.springboot.app.dto.ItemDTO;
import com.dbr.generator.springboot.app.dto.ProjectDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectDTOProjectModelMapping {

    private final ItemDTOItemModelMapping itemDTOItemModelMapping;

    public ProjectDTOProjectModelMapping(ItemDTOItemModelMapping itemDTOItemModelMapping) {
        this.itemDTOItemModelMapping = itemDTOItemModelMapping;
    }

    public ProjectModel toModel(ProjectDTO source, ProcessModel processModel) {
        ProjectModel dest = new ProjectModel(processModel, source.getTechnicalDescriptor(), source.getTemplate().toArray(new Template[source.getTemplate().size()]));
        BeanUtils.copyProperties(source, dest, "items");
        for (ItemDTO item : source.getItems()) {
            dest.addItem(itemDTOItemModelMapping.toModel(item, dest));
        }
        return dest;
    }

    public ProjectDTO toDTO(ProjectModel source) {
        ProjectDTO dest = new ProjectDTO();
        BeanUtils.copyProperties(source, dest, "items");
        for (ItemModel item : source.getItems()) {
            dest.addItem(itemDTOItemModelMapping.toDTO(item));
        }
        return dest;
    }

    public List<ProjectDTO> toDTOs(Collection<ProjectModel> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }


}