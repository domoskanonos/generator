package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.dto.PropertieDTO;
import com.dbr.util.DataTypes;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import java.lang.reflect.Field;

public class JavaField2PropertieDTOConverter implements ConverterInterface<Field, PropertieDTO> {
    @Override
    public PropertieDTO convert(Field source) {
        PropertieDTO propertieDTO = new PropertieDTO();
        propertieDTO.setName(source.getName());
        Class<?> type = source.getType();
        propertieDTO.setTypeName(type.getName());
        propertieDTO.setTypeSimpleName(getSimpleName(type));
        propertieDTO.setIdPropertie(isIDField(source));
        propertieDTO.setBaseType(isBaseJavaType(type));
        propertieDTO.setEnumeration(isEnumeration(type));
        return propertieDTO;
    }

    public static String getSimpleName(Class<?> fieldType) {
        String typeSimpleName = fieldType.getSimpleName();
        switch (typeSimpleName) {
            case DataTypes.TYPE_STRING:
            case DataTypes.TYPE_CHAR:
            case DataTypes.TYPE_BOOLEAN:
            case DataTypes.TYPE_SHORT:
            case DataTypes.TYPE_INTEGER:
            case DataTypes.TYPE_LONG:
            case DataTypes.TYPE_FLOAT:
            case DataTypes.TYPE_DOUBLE:
            case DataTypes.JAVA_TYPE_BYTE_ARRAY:
                return fieldType.getSimpleName();
            case DataTypes.TYPE_DATE:
            case DataTypes.TYPE_BIG_DECIMAL:
            default:
                return fieldType.getName();
        }
    }

    public boolean isIDField(Field field) {
        if (field.getAnnotation(Id.class) != null || field.getAnnotation(EmbeddedId.class) != null) {
            return true;
        }
        return false;
    }

    public static boolean isEnumeration(Class<?> type) {
        return type.isEnum();
    }

    public static boolean isBaseJavaType(Class<?> type) {
        String simpleName = type.getSimpleName();
        return isBaseJavaType(simpleName) || type.isPrimitive();
    }

    public static boolean isBaseJavaType(String typeSimpleName) {
        boolean baseJavaType = false;
        switch (typeSimpleName) {
            case DataTypes.TYPE_STRING:
            case DataTypes.TYPE_INTEGER:
            case DataTypes.TYPE_BOOLEAN:
            case DataTypes.TYPE_BIG_DECIMAL:
            case DataTypes.TYPE_LONG:
            case DataTypes.TYPE_FLOAT:
            case DataTypes.TYPE_SHORT:
            case DataTypes.TYPE_DOUBLE:
            case DataTypes.TYPE_CHAR:
            case DataTypes.JAVA_TYPE_BYTE_ARRAY:
            case DataTypes.TYPE_DATE:
            case DataTypes.TYPE_ARRAY_STRING:
                baseJavaType = true;
                break;
            default:
                break;
        }
        return baseJavaType;
    }


}
