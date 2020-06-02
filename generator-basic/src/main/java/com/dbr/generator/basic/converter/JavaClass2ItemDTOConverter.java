package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.PropertyDTO;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaClass2ItemDTOConverter {

    public ItemDTO convert(String projectPath, String templatePath, Class<?> clazz) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setJavaIdClazzName(getIDClazzName(clazz));
        itemDTO.setJavaClazzName(clazz.getName());
        itemDTO.setFilePath(getJavaFilePathSuffix(projectPath, itemDTO));
        itemDTO.setTemplatePath(templatePath);
        for (Field field : clazz.getDeclaredFields()) {
            PropertyDTO propertyDTO = new JavaField2PropertyDTOConverter().convert(itemDTO, field);
            itemDTO.addProperty(propertyDTO);
        }
        return itemDTO;
    }

    private String getJavaFilePathSuffix(String projectPath, ItemDTO itemDTO) {
        return new StringBuilder().append(projectPath).append("/").append("src/main/java/").append(itemDTO.getPackagePath()).append(itemDTO.getJavaClazzSimpleName()).append(".java").toString();
    }

    public List<ItemDTO> convert(String filePath, String templatePath, Collection<Class<?>> clazzes) {
        List<ItemDTO> retval = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            retval.add(convert(filePath, templatePath, clazz));
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
