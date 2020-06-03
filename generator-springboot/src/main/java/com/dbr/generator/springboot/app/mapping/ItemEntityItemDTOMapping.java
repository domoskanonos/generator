package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.entity.ItemEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemEntityItemDTOMapping {

    final PropertyEntityPropertyDTOMapping propertyEntityPropertyDTOMapping;


    public ItemEntityItemDTOMapping(PropertyEntityPropertyDTOMapping propertyEntityPropertyDTOMapping) {
        this.propertyEntityPropertyDTOMapping = propertyEntityPropertyDTOMapping;
    }

    public ItemEntity toEntity(ItemDTO source) {
        ItemEntity dest = new ItemEntity();
        BeanUtils.copyProperties(source, dest, "properties");
        dest.setName(source.getName());
        dest.setIdTypeEnum(source.getIdTypeEnum());
        dest.setProperties(new HashSet<>(propertyEntityPropertyDTOMapping.toEntities(source.getProperties())));
        dest.setTemplate(source.getTemplate());
        return dest;
    }

    public ItemDTO toDTO(ItemEntity source) {
        ItemDTO dest = new ItemDTO();
        BeanUtils.copyProperties(source, dest);
        dest.setProperties(propertyEntityPropertyDTOMapping.toDTOs(source.getProperties()));
        return dest;
    }

    public List<ItemDTO> toDTOs(Collection<ItemEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ItemEntity> toEntities(Collection<ItemDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
