package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.merger.TemplateEnum;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaClass2ItemDTOConverter {

    public ItemDTO convert(TemplateEnum templateEnum, Class<?> clazz) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setJavaIdClazzName(getIDClazzName(clazz));
        itemDTO.setJavaClazzName(clazz.getName());
        itemDTO.setTemplate(templateEnum);
        for (Field field : clazz.getDeclaredFields()) {
            PropertyDTO propertyDTO = new JavaField2PropertyDTOConverter().convert(itemDTO, field);
            itemDTO.addProperty(propertyDTO);
        }
        return itemDTO;
    }

    public List<ItemDTO> convert(TemplateEnum templateEnum, String filePath, Collection<Class<?>> clazzes) {
        List<ItemDTO> retval = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            retval.add(convert(templateEnum, clazz));
        }
        return retval;
    }

    public static String getIDClazzName(Class<?> clazz) {
        String javaIdClazzSimpleName = null;
        IdClass idClassAnnotation = clazz.getAnnotation(IdClass.class);
        if (idClassAnnotation != null) {
            javaIdClazzSimpleName = idClassAnnotation.value().getName();
        } else {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getAnnotation(Id.class) != null) {
                    javaIdClazzSimpleName = field.getType().getName();
                    break;
                }
                if (field.getAnnotation(EmbeddedId.class) != null) {
                    javaIdClazzSimpleName = field.getType().getName();
                    break;
                }
            }
        }
        return javaIdClazzSimpleName;
    }


}
