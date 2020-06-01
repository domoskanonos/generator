package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.entity.Property;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyPropertyDTOMapping {

    public PropertyDTO toEntity(Property source) {
        PropertyDTO dest = new PropertyDTO();
        return dest;
    }

    public Property toDTO(PropertyDTO source) {
        Property dest = new Property();
        return dest;
    }

    public List<Property> toDTOs(Collection<PropertyDTO> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<PropertyDTO> toEntities(Collection<Property> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
