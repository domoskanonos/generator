package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.entity.Property;
import com.dbr.generator.springboot.app.dto.PropertyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyEntityPropertyDTOMapping {

    public Property toEntity(PropertyDTO source)
    {
        Property dest = new Property();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public PropertyDTO toDTO(Property source)
    {
        PropertyDTO dest = new PropertyDTO();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public List<PropertyDTO> toDTOs(Collection<Property> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<Property> toEntities(Collection<PropertyDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }



}