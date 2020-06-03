package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.merger.TemplateEnum;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaClass2ItemDTOConverter {

    public ItemDTO convert(ProjectDTO projectDTO, TemplateEnum templateEnum, Class<?> clazz) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProjectDTO(projectDTO);
        itemDTO.setIdTypeEnum(TypeEnum.byJavaTypeSimpleName(getIDClazzSimpleName(clazz)));
        itemDTO.setName(clazz.getName());
        itemDTO.setTemplate(templateEnum);
        for (Field field : clazz.getDeclaredFields()) {
            PropertyDTO propertyDTO = new JavaField2PropertyDTOConverter().convert(itemDTO, field);
            itemDTO.addProperty(propertyDTO);
        }
        return itemDTO;
    }

    public List<ItemDTO> convert(ProjectDTO projectDTO, TemplateEnum templateEnum, Collection<Class<?>> clazzes) {
        List<ItemDTO> retval = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            retval.add(convert(projectDTO, templateEnum, clazz));
        }
        return retval;
    }

    public static String getIDClazzSimpleName(Class<?> clazz) {
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
