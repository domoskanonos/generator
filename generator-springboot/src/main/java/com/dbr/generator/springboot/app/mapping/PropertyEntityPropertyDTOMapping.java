package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.entity.PropertyEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyEntityPropertyDTOMapping {

    public PropertyEntity toEntity(PropertyDTO source) {
        PropertyEntity dest = new PropertyEntity();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public PropertyDTO toDTO(PropertyEntity source) {
        PropertyDTO dest = new PropertyDTO();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public List<PropertyDTO> toDTOs(Collection<PropertyEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<PropertyEntity> toEntities(Collection<PropertyDTO> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
