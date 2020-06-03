package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.entity.Item;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemItemDTOMapping {

    final PropertyPropertyDTOMapping propertyPropertyDTOMapping;

    public ItemItemDTOMapping(PropertyPropertyDTOMapping propertyPropertyDTOMapping) {
        this.propertyPropertyDTOMapping = propertyPropertyDTOMapping;
    }

    public Item toEntity(ItemDTO source) {
        Item dest = new Item();
        BeanUtils.copyProperties(source, dest, "properties");
        dest.setProperties(new HashSet<>(propertyPropertyDTOMapping.toEntities(source.getProperties())));
        dest.setTemplate(source.getTemplate());
        return dest;
    }

    public ItemDTO toDTO(Item source) {
        ItemDTO dest = new ItemDTO();
        BeanUtils.copyProperties(source, dest);
        dest.setTemplate(source.getTemplate());
        dest.setProperties(propertyPropertyDTOMapping.toDTOs(source.getProperties()));
        return dest;
    }

    public List<ItemDTO> toDTOs(Collection<Item> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<Item> toEntities(Collection<ItemDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
