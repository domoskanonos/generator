package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ObjectDTO;
import com.dbr.generator.basic.dto.PropertyDTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaClass2ModelDTOConverter implements ConverterInterface<Class<?>, ObjectDTO> {

    @Override
    public ObjectDTO convert(Class<?> source) {
        ObjectDTO objectDTO = new ObjectDTO();
        objectDTO.setIdClazzSimpleName(getIDClazzSimpleName(source));
        objectDTO.setPackageName(source.getPackage().getName());
        objectDTO.setClazzSimpleName(getClazzSimpleName(source));
        objectDTO.setClazzName(String.format("%s.%s", objectDTO.getPackageName(), objectDTO.getClazzSimpleName()));
        for (Field field : source.getDeclaredFields()) {
            PropertyDTO propertyDTO = new JavaField2PropertyDTOConverter().convert(field);
            propertyDTO.setUseIdClazz(objectDTO.useJPAIdClazz());
            objectDTO.getProperties().add(propertyDTO);
        }
        return objectDTO;
    }

    private String getClazzSimpleName(Class<?> source) {
        return source.getSimpleName().replace("DTO", "").replace("Model", "").replace("Entity", "");
    }

    @Override
    public List<ObjectDTO> convert(Collection<Class<?>> source) {
        return source.stream().map(this::convert).collect(Collectors.toList());
    }

    public static String getIDClazzSimpleName(Class<?> clazz) {
        String idClazzSimpleName = null;
        IdClass idClassAnnotation = clazz.getAnnotation(IdClass.class);
        if (idClassAnnotation != null) {
            idClazzSimpleName = idClassAnnotation.value().getSimpleName();
        } else {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getAnnotation(Id.class) != null) {
                    idClazzSimpleName = field.getType().getSimpleName();
                    break;
                }
                if (field.getAnnotation(EmbeddedId.class) != null) {
                    idClazzSimpleName = field.getType().getName();
                    break;
                }
            }
        }
        return idClazzSimpleName;
    }

}
