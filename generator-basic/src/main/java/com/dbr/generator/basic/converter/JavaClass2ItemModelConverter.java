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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JavaClass2ItemModelConverter {

    public ItemModel convert(ProjectModel projectModel, Template template, Class<?> clazz) {
        ItemModel itemModel = new ItemModel(projectModel, clazz.getName(), PropertyType.byJavaTypeSimpleName(getIDClazzSimpleName(clazz)), template);
        itemModel.getTemplate().addAll(Arrays.asList(template));
        for (Field field : clazz.getDeclaredFields()) {
            PropertyModel propertyModel = new JavaField2PropertyModelConverter().convert(itemModel, field);
            itemModel.addProperty(propertyModel);
        }
        return itemModel;
    }

    public List<ItemModel> convert(ProjectModel projectModel, Template template, Collection<Class<?>> clazzes) {
        List<ItemModel> retval = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            retval.add(convert(projectModel, template, clazz));
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
