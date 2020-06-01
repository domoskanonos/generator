package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.entity.Item;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemItemDTOMapping {

    public ItemDTO toEntity(Item source) {
        ItemDTO dest = new ItemDTO();
        return dest;
    }

    public Item toDTO(PropertyDTO source) {
        Item dest = new Item();
        return dest;
    }

    public List<Item> toDTOs(Collection<PropertyDTO> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<ItemDTO> toEntities(Collection<Item> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
