package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.enumeration.TypeEnum;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaField2PropertyDTOConverter {

    public PropertyDTO convert(ItemDTO itemDTO, Field field) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setUseJPAIdClazz(itemDTO.getIdTypeEnum() != null);
        propertyDTO.setName(field.getName());
        propertyDTO.setPropertyType(TypeEnum.byField(field));
        propertyDTO.setIdProperty(isIDField(field));
        return propertyDTO;
    }

    public List<PropertyDTO> convert(ItemDTO itemDTO, Collection<Field> fields) {
        List<PropertyDTO> retval = new ArrayList<>();
        for (Field field : fields) {
            retval.add(convert(itemDTO, field));
        }
        return retval;
    }

    public boolean isIDField(Field field) {
        if (field.getAnnotation(Id.class) != null || field.getAnnotation(EmbeddedId.class) != null) {
            return true;
        }
        return false;
    }

}
