package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.model.ItemModel;
import com.dbr.generator.basic.model.PropertyModel;
import com.dbr.generator.basic.enumeration.TypeEnum;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JavaField2PropertyDTOConverter {

    public PropertyModel convert(ItemModel itemModel, Field field) {
        PropertyModel propertyModel = new PropertyModel();
        propertyModel.setUseJPAIdClazz(itemModel.getIdTypeEnum() != null);
        propertyModel.setName(field.getName());
        propertyModel.setPropertyType(TypeEnum.byField(field));
        propertyModel.setIdProperty(isIDField(field));
        return propertyModel;
    }

    public List<PropertyModel> convert(ItemModel itemModel, Collection<Field> fields) {
        List<PropertyModel> retval = new ArrayList<>();
        for (Field field : fields) {
            retval.add(convert(itemModel, field));
        }
        return retval;
    }

    public boolean isIDField(Field field) {
        if (field.getAnnotation(Id.class) != null || field.getAnnotation(EmbeddedId.class) != null) {
            return true;
        }
        return false;
    }

}
