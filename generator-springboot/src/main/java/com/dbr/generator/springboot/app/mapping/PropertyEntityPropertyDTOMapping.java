package com.dbr.generator.springboot.app.mapping;

import com.dbr.generator.basic.model.PropertyModel;
import com.dbr.generator.basic.entity.PropertyEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PropertyEntityPropertyDTOMapping {

    public PropertyEntity toEntity(PropertyModel source) {
        PropertyEntity dest = new PropertyEntity();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public PropertyModel toDTO(PropertyEntity source) {
        PropertyModel dest = new PropertyModel();
        BeanUtils.copyProperties(source, dest);
        return dest;
    }

    public List<PropertyModel> toDTOs(Collection<PropertyEntity> sources) {
        return sources.stream().map(source -> toDTO(source)).collect(Collectors.toList());
    }

    public List<PropertyEntity> toEntities(Collection<PropertyModel> sources) {
        return sources.stream().map(source -> toEntity(source)).collect(Collectors.toList());
    }

}
