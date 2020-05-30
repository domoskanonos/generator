package com.dbr.generator.basic.item.converter;

import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.property.converter.JavaField2PropertyDTOConverter;
import com.dbr.generator.basic.property.dto.PropertyConverterDTO;
import com.dbr.generator.basic.property.dto.PropertyDTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaClass2ItemDTOConverter {

    public ItemDTO convert(Class<?> clazz) {
        ItemDTO itemDTO = ItemDTO.builder().build();
        itemDTO.setJavaIdClazzSimpleName(getIDClazzSimpleName(clazz));
        itemDTO.setJavaClazzName(clazz.getName());
        for (Field field : clazz.getDeclaredFields()) {
            PropertyDTO propertyDTO = new JavaField2PropertyDTOConverter().convert(new PropertyConverterDTO(itemDTO, field));
            itemDTO.addProperty(propertyDTO);
        }
        return itemDTO;
    }

    public List<ItemDTO> convert(Collection<Class<?>> clazzes) {
        return clazzes.stream().map(this::convert).collect(Collectors.toList());
    }

    private String getClazzSimpleName(Class<?> source) {
        return source.getSimpleName();
        //return source.getSimpleName().replace("DTO", "").replace("Model", "").replace("Entity", "");
    }

    public static String getIDClazzSimpleName(Class<?> clazz) {
        String javaIdClazzSimpleName = null;
        IdClass idClassAnnotation = clazz.getAnnotation(IdClass.class);
        if (idClassAnnotation != null) {
            javaIdClazzSimpleName = idClassAnnotation.value().getSimpleName();
        } else {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getAnnotation(Id.class) != null) {
                    javaIdClazzSimpleName = field.getType().getSimpleName();
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
