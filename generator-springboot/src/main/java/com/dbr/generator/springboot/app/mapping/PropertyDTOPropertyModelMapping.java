package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.model.PropertyModel;
import com.dbr.generator.springboot.app.dto.PropertyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyDTOPropertyModelMapping {

    public PropertyModel toModel(PropertyDTO source) {
        PropertyModel dest = new PropertyModel();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public PropertyDTO toDTO(PropertyModel source) {
        PropertyDTO dest = new PropertyDTO();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public List<PropertyDTO> toDTOs(Collection<PropertyModel> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<PropertyModel> toModels(Collection<PropertyDTO> sources) {
        return sources.stream().map(source -> toModel(source)).collect(Collectors.toList());
    }


}