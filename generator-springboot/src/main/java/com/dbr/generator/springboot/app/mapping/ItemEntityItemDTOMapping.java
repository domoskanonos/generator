package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.entity.ItemEntity;
import com.dbr.generator.basic.entity.PropertyEntity;
import com.dbr.generator.springboot.app.dto.ItemDTO;
import com.dbr.generator.springboot.app.dto.PropertyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemEntityItemDTOMapping {

    final
    PropertyEntityPropertyDTOMapping propertyEntityPropertyDTOMapping;

    public ItemEntityItemDTOMapping(PropertyEntityPropertyDTOMapping propertyEntityPropertyDTOMapping) {
        this.propertyEntityPropertyDTOMapping = propertyEntityPropertyDTOMapping;
    }

    public ItemEntity toEntity(ItemDTO source) {
        ItemEntity dest = new ItemEntity();
        BeanUtils.copyProperties(source, dest, "properties");
        for (PropertyDTO property : source.getProperties()) {
            dest.addProperty(propertyEntityPropertyDTOMapping.toEntity(property));
        }
        return dest;
    }

    public ItemDTO toDTO(ItemEntity source) {
        ItemDTO dest = new ItemDTO();
        BeanUtils.copyProperties(source, dest, "properties");
        for (PropertyEntity property : source.getProperties()) {
            dest.addProperty(propertyEntityPropertyDTOMapping.toDTO(property));
        }
        return dest;
    }

    public List<ItemDTO> toDTOs(Collection<ItemEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ItemEntity> toEntities(Collection<ItemDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}