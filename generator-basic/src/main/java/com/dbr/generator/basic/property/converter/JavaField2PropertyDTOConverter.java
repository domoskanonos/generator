package com.dbr.generator.basic.property.converter;

import com.dbr.generator.basic.property.enumeration.PropertyTypeEnum;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.property.dto.PropertyConverterDTO;
import com.dbr.generator.basic.property.dto.PropertyDTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaField2PropertyDTOConverter {

    public PropertyDTO convert(PropertyConverterDTO propertyConverterDTO) {
        PropertyDTO propertyDTO = new PropertyDTO();
        Field source = propertyConverterDTO.getSource();
        ItemDTO itemDTO = propertyConverterDTO.getParent();
        propertyDTO.setUseJPAIdClazz(itemDTO.getJavaIdClazzSimpleName() != null);
        propertyDTO.setName(source.getName());
        propertyDTO.setPropertyType(PropertyTypeEnum.byField(source));
        propertyDTO.setIdProperty(isIDField(source));
        return propertyDTO;
    }

    public List<PropertyDTO> convert(Collection<PropertyConverterDTO> propertyConverterDTOS) {
        return propertyConverterDTOS.stream().map(this::convert).collect(Collectors.toList());
    }

    public boolean isIDField(Field field) {
        if (field.getAnnotation(Id.class) != null || field.getAnnotation(EmbeddedId.class) != null) {
            return true;
        }
        return false;
    }

}
