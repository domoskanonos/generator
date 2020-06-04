package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.model.ItemModel;
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

    public ItemEntity toEntity(ItemModel source) {
        ItemEntity dest = new ItemEntity();
        BeanUtils.copyProperties(source, dest, "properties");
        dest.setName(source.getName());
        dest.setIdTypeEnum(source.getIdTypeEnum());
        dest.setProperties(new HashSet<>(propertyEntityPropertyDTOMapping.toEntities(source.getProperties())));
        dest.setTemplate(source.getTemplate());
        return dest;
    }

    public ItemModel toDTO(ItemEntity source) {
        ItemModel dest = new ItemModel();
        BeanUtils.copyProperties(source, dest);
        dest.setProperties(propertyEntityPropertyDTOMapping.toDTOs(source.getProperties()));
        return dest;
    }

    public List<ItemModel> toDTOs(Collection<ItemEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ItemEntity> toEntities(Collection<ItemModel> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
