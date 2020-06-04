package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.merger.TemplateEnum;
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

public class JavaClass2ItemDTOConverter {

    public ItemModel convert(ProjectModel projectModel, TemplateEnum templateEnum, Class<?> clazz) {
        ItemModel itemModel = new ItemModel(projectModel, clazz.getName(), TypeEnum.byJavaTypeSimpleName(getIDClazzSimpleName(clazz)), templateEnum);
        itemModel.getTemplate().addAll(Arrays.asList(templateEnum));
        for (Field field : clazz.getDeclaredFields()) {
            PropertyModel propertyModel = new JavaField2PropertyDTOConverter().convert(itemModel, field);
            itemModel.addProperty(propertyModel);
        }
        return itemModel;
    }

    public List<ItemModel> convert(ProjectModel projectModel, TemplateEnum templateEnum, Collection<Class<?>> clazzes) {
        List<ItemModel> retval = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            retval.add(convert(projectModel, templateEnum, clazz));
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
