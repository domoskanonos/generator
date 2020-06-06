package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.PropertyModel;
import com.dbr.generator.springboot.app.dto.ItemDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemDTOItemModelMapping {

    @Autowired
    PropertyDTOPropertyModelMapping propertyDTOPropertyModelMapping;

    public ItemModel toModel(ItemDTO source) {
        ItemModel dest = new ItemModel();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public ItemDTO toDTO(ItemModel source) {
        ItemDTO dest = new ItemDTO();
        BeanUtils.copyProperties(source, dest, "items");
        for (PropertyModel property : source.getProperties()) {
            dest.addProperty(propertyDTOPropertyModelMapping.toDTO(property));
        }
        return dest;
    }

    public List<ItemDTO> toDTOs(Collection<ItemModel> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ItemModel> toModels(Collection<ItemDTO> sources) {
        return sources.stream().map(source -> toModel(source)).collect(Collectors.toList());
    }


}