package com.dbr.generator.basic.property.enumeration;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public enum PropertyTypeEnum {

    BYTE_ARRAY(byte[].class),
    TYPE_STRING(String.class),
    TYPE_INTEGER(Integer.class),
    TYPE_BOOLEAN(Boolean.class),
    TYPE_BIG_DECIMAL(BigDecimal.class),
    TYPE_LONG(Long.class),
    TYPE_FLOAT(Float.class),
    TYPE_SHORT(Short.class),
    TYPE_DOUBLE(Double.class),
    TYPE_CHAR(char.class),
    TYPE_DATE(Date.class),
    TYPE_DATE_ISO8601(Date.class),
    TYPE_ARRAY_STRING(String[].class),
    LIST(List.class),
    OBJECT(Object.class);

    private Class<?> type;

    public Class<?> getType() {
        return type;
    }

    public String getJavaTypeName() {
        return type.getName();
    }

    public String getJavaTypeSimpleName() {
        return isBaseJavaType() ? type.getSimpleName() : getJavaTypeName();
    }

    public Boolean getJavaBaseType() {
        return isBaseJavaType();
    }

    public Boolean getJavaEnumeration() {
        return type.isEnum();
    }

    PropertyTypeEnum(Class<?> type) {
    this.type = type;
    }

    public boolean isEnumeration(Class<?> type) {
        return type.isEnum();
    }

    public boolean isBaseJavaType() {
        return isBaseJavaType(this) || this.type.isPrimitive();
    }

    public boolean isBaseJavaType(PropertyTypeEnum propertyTypeEnum) {
        switch (propertyTypeEnum) {
            case TYPE_STRING:
            case TYPE_INTEGER:
            case TYPE_BOOLEAN:
            case TYPE_BIG_DECIMAL:
            case TYPE_LONG:
            case TYPE_FLOAT:
            case TYPE_SHORT:
            case TYPE_DOUBLE:
            case TYPE_CHAR:
            case TYPE_DATE:
            case TYPE_DATE_ISO8601:
                return true;
            case BYTE_ARRAY:
            case TYPE_ARRAY_STRING:default:return false;

        }
    }

    public String getTypescriptType() {

        switch (this) {
            case TYPE_STRING:
            case TYPE_CHAR:
                return "string";
            case TYPE_INTEGER:
            case TYPE_BIG_DECIMAL:
            case TYPE_LONG:
            case TYPE_FLOAT:
            case TYPE_SHORT:
            case TYPE_DOUBLE:
                return "number";
            case TYPE_BOOLEAN:
                return "boolean";
            case TYPE_DATE:
            case TYPE_DATE_ISO8601:
                return "Date";
            case TYPE_ARRAY_STRING:
                return "string[]";
            case BYTE_ARRAY:
            case LIST:
                return "array";
            default:
                return "any";
        }


    }

    public String getTypescriptInitValue() {
        switch (this) {
            case TYPE_CHAR:
            case BYTE_ARRAY:
            case TYPE_STRING:
                return "''";
            case TYPE_INTEGER:
                break;
            case TYPE_BOOLEAN:
                return "false";
            case TYPE_BIG_DECIMAL:
            case TYPE_LONG:
            case TYPE_FLOAT:
            case TYPE_SHORT:
            case TYPE_DOUBLE:
                return "0";
            case TYPE_DATE:
            case TYPE_DATE_ISO8601:
                return "new Date()";
            case TYPE_ARRAY_STRING:
            case LIST:
                return "[]";
            default:
                return "{}";
        }
        return null;
    }


    public static PropertyTypeEnum byJavaTypeSimpleName(String javaTypeSimpleName) {
        for (PropertyTypeEnum propertyTypeEnum : PropertyTypeEnum.values()) {
            if (propertyTypeEnum.getJavaTypeSimpleName().equals(javaTypeSimpleName)) {
                return propertyTypeEnum;
            }
        }
        return PropertyTypeEnum.OBJECT;
    }

    public static PropertyTypeEnum byTypescriptType(String typescriptType) {
        for (PropertyTypeEnum propertyTypeEnum : PropertyTypeEnum.values()) {
            if (propertyTypeEnum.getTypescriptType().equals(typescriptType)) {
                return propertyTypeEnum;
            }
        }
        return PropertyTypeEnum.OBJECT;
    }

    public static PropertyTypeEnum byField(Field source) {
        for (PropertyTypeEnum propertyTypeEnum : PropertyTypeEnum.values()) {
            Class<?> type = propertyTypeEnum.getType();
            if (Objects.equals(type.getSimpleName(), source.getType().getSimpleName())) {
                return propertyTypeEnum;
            }
        }
        return PropertyTypeEnum.OBJECT;
    }
}
