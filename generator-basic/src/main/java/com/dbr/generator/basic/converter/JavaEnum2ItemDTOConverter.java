package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.enumeration.PropertyType;
import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.PropertyModel;
import com.dbr.generator.basic.model.project.ProjectModel;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.lang.reflect.Field;
import java.util.*;

public class JavaEnum2ItemDTOConverter {

    public ItemModel convert(ProjectModel projectModel, Class<?> enumClazz, Template... template) {
        ItemModel itemModel = new ItemModel(projectModel, enumClazz.getSimpleName(), PropertyType.ENUMERATION, template);
        itemModel.getTemplate().addAll(Arrays.asList(template));
        for (Object enumConstant : enumClazz.getEnumConstants()) {
            PropertyModel propertyModel = new PropertyModel(itemModel, enumConstant.toString());
            propertyModel.setPropertyType(PropertyType.STRING);
            propertyModel.setPropertyTypeSimpleName("String");
            propertyModel.setPropertyTypeSimpleName("java.lang.String");
            itemModel.addProperty(propertyModel);
        }
        return itemModel;
    }

    public List<ItemModel> convert(ProjectModel projectModel, Template template, Collection<Class<?>> enumClazzes) {
        List<ItemModel> retval = new ArrayList<>();
        for (Class<?> clazz : enumClazzes) {
            retval.add(convert(projectModel, clazz, template));
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
