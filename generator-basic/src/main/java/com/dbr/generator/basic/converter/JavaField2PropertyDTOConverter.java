package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ModelDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.enumeration.PropertyTypeEnum;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaField2PropertyDTOConverter implements ConverterInterface<Field, PropertyDTO> {
    @Override
    public PropertyDTO convert(Field source) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setName(source.getName());
        propertyDTO.setPropertyType(PropertyTypeEnum.byField(source));
        propertyDTO.setIdProperty(isIDField(source));
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> convert(Collection<Field> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }

    public boolean isIDField(Field field) {
        if (field.getAnnotation(Id.class) != null || field.getAnnotation(EmbeddedId.class) != null) {
            return true;
        }
        return false;
    }

}
