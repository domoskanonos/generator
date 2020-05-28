package com.dbr.generator.basic.enumeration;

import com.dbr.util.DataTypes;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
    LIST(List.class);

    private Class<?> type;

    public Class<?> getType() {
        return type;
    }

    public String getJavaTypeName() {


        System.out.println(type.getTypeParameters()[0]);


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
                return "string";
            case TYPE_INTEGER:
                return "number";
            case TYPE_BOOLEAN:
                return "boolean";
            case TYPE_BIG_DECIMAL:
                return "";
            case TYPE_LONG:
                return "";
            case TYPE_FLOAT:
                return "";
            case TYPE_SHORT:
                return "";
            case TYPE_DOUBLE:
                return "";
            case TYPE_CHAR:
                return "";
            case TYPE_DATE:
                return "";
            case TYPE_DATE_ISO8601:
                return "";
            case BYTE_ARRAY:
                return "array";
            case TYPE_ARRAY_STRING:
                return "string[]";
            case LIST:
                return "[]";
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
        return null;
    }

    public static PropertyTypeEnum byTypescriptType(String typescriptType){
        for (PropertyTypeEnum propertyTypeEnum : PropertyTypeEnum.values()) {
            if(propertyTypeEnum.getTypescriptType().equals(typescriptType)){
                return propertyTypeEnum;
            }
        }return null;
    }

    public static PropertyTypeEnum byField(Field source) {
        for (PropertyTypeEnum propertyTypeEnum : PropertyTypeEnum.values()) {
            Class<?> type = propertyTypeEnum.getType();
            if (type.getSimpleName().equals(source.getType().getSimpleName())) {
                return propertyTypeEnum;
            }
        }
        return null;
    }
}
