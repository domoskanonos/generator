package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ConverterDTO;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.ProjectDTO;
import com.dbr.generator.basic.dto.PropertyDTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaClass2ModelDTOConverter implements ConverterInterface<ProjectDTO, ItemDTO, Class<?>> {

    @Override
    public ItemDTO convert(ConverterDTO<ProjectDTO, Class<?>> converterDTO) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setJavaIdClazzSimpleName(getIDClazzSimpleName(converterDTO.getSource()));
        ProjectDTO projectDTO = converterDTO.getParent();
        itemDTO.setProjectDTO(projectDTO);
        itemDTO.setJavaClazzSimpleName(getClazzSimpleName(converterDTO.getSource()));
        itemDTO.setJavaClazzName(String.format("%s.%s", projectDTO.getJavaBasePackage(), itemDTO.getJavaClazzSimpleName()));
        for (Field field : converterDTO.getSource().getDeclaredFields()) {
            PropertyDTO propertyDTO = new JavaField2PropertyDTOConverter().convert(new ConverterDTO<>(itemDTO, field));
            itemDTO.getProperties().add(propertyDTO);
        }
        return itemDTO;
    }

    @Override
    public List<ItemDTO> convert(Collection<ConverterDTO<ProjectDTO, Class<?>>> converterDTOS) {
        return converterDTOS.stream().map(this::convert).collect(Collectors.toList());
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
