package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ConverterDTO;
import com.dbr.generator.basic.dto.ObjectDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.enumeration.PropertyTypeEnum;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaField2PropertyDTOConverter implements ConverterInterface<ObjectDTO, PropertyDTO, Field> {


    public boolean isIDField(Field field) {
        if (field.getAnnotation(Id.class) != null || field.getAnnotation(EmbeddedId.class) != null) {
            return true;
        }
        return false;
    }

    @Override
    public PropertyDTO convert(ConverterDTO<ObjectDTO, Field> converterDTO) {
        PropertyDTO propertyDTO = new PropertyDTO();
        Field source = converterDTO.getSource();
        ObjectDTO objectDTO = converterDTO.getParent();
        propertyDTO.setObjectDTO(objectDTO);
        propertyDTO.setName(source.getName());
        propertyDTO.setPropertyType(PropertyTypeEnum.byField(source));
        propertyDTO.setIdProperty(isIDField(source));
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> convert(Collection<ConverterDTO<ObjectDTO, Field>> converterDTOS) {
        return converterDTOS.stream().map(this::convert).collect(Collectors.toList());
    }
}
