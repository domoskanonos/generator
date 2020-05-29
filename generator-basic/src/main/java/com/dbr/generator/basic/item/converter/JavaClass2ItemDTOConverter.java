package com.dbr.generator.basic.item.converter;

import com.dbr.generator.basic.project.dto.ProjectDTO;
import com.dbr.generator.basic.item.dto.ItemConverterDTO;
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

public class JavaClass2ItemDTOConverter implements ItemConverterInterface {

    @Override
    public ItemDTO convert(ItemConverterDTO itemConverterDTO) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setJavaIdClazzSimpleName(getIDClazzSimpleName(itemConverterDTO.getClazz()));
        ProjectDTO projectDTO = itemConverterDTO.getProjectDTO();
        itemDTO.setProjectDTO(projectDTO);
        itemDTO.setJavaClazzSimpleName(getClazzSimpleName(itemConverterDTO.getClazz()));
        itemDTO.setJavaClazzName(String.format("%s.%s", projectDTO.getJavaBasePackage(), itemDTO.getJavaClazzSimpleName()));
        for (Field field : itemConverterDTO.getClazz().getDeclaredFields()) {
            PropertyDTO propertyDTO = new JavaField2PropertyDTOConverter().convert(new PropertyConverterDTO(itemDTO, field));
            itemDTO.getProperties().add(propertyDTO);
        }
        return itemDTO;
    }

    @Override
    public List<ItemDTO> convert(Collection<ItemConverterDTO> itemConverterDTOS) {
        return itemConverterDTOS.stream().map(this::convert).collect(Collectors.toList());
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
