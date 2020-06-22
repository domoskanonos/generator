package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.enumeration.PropertyType;
import com.dbr.generator.basic.enumeration.Template;
import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.PropertyModel;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.jpa.DatabaseJPAUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JPAEntityReference2ItemModelConverter {

    public ItemModel convert(ProjectModel projectModel, DatabaseJPAUtil.JPAEntityReference jpaEntityReference, Template... template) {
        ItemModel itemModel = new ItemModel(projectModel, jpaEntityReference.getJavaClassName(), PropertyType.byJavaTypeSimpleName(jpaEntityReference.getIdColumn().getJavaTypeSimpleName()), template);
        itemModel.getTemplate().addAll(Arrays.asList(template));
        for (DatabaseJPAUtil.ColumnReference columnReference : jpaEntityReference.getColumnReferences()) {
            PropertyModel propertyModel = new PropertyModel(itemModel, columnReference.getJavaFieldName());
            propertyModel.setUseJPAIdClazz(itemModel.getIdPropertyType() != null);
            propertyModel.setPropertyType(PropertyType.byJavaTypeSimpleName(columnReference.getJavaTypeSimpleName()));
            propertyModel.setMainProperty(true);
            propertyModel.setIdProperty(jpaEntityReference.isIdColumn(columnReference));
            propertyModel.setPropertyTypeSimpleName(columnReference.getJavaTypeSimpleName());
            propertyModel.setPropertyTypeName(columnReference.getJavaTypeSimpleName());
            itemModel.addProperty(propertyModel);
        }
        return itemModel;
    }


    public Collection<ItemModel> convert(ProjectModel projectModel, List<DatabaseJPAUtil.JPAEntityReference> jpaEntityReferences, Template... template) {
        List<ItemModel> retval = new ArrayList<>();
        for (DatabaseJPAUtil.JPAEntityReference jpaEntityReference : jpaEntityReferences) {
            retval.add(convert(projectModel, jpaEntityReference, template));
        }
        return retval;
    }


}
