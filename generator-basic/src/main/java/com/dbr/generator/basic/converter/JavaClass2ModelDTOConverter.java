package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ModelDTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JavaClass2ModelDTOConverter implements ConverterInterface<Class<?>, ModelDTO> {

    @Override
    public ModelDTO convert(Class<?> source) {
        ModelDTO modelDTO = new ModelDTO();
        for (Field field : source.getDeclaredFields()) {
            modelDTO.getProperties().add(new JavaField2PropertyDTOConverter().convert(field));
        }
        modelDTO.setIdClazzSimpleName(getIDClazzSimpleName(source));
        modelDTO.setPackageName(source.getPackage().getName());
        modelDTO.setClazzSimpleName(source.getSimpleName());
        modelDTO.setClazzName(String.format("%s.%s", modelDTO.getPackageName(), modelDTO.getClazzSimpleName()));
        return modelDTO;
    }

    @Override
    public List<ModelDTO> convert(Collection<Class<?>> source) {
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
