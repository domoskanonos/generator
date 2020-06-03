package com.dbr.generator.basic.enumeration;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public enum TypeEnum {

    BYTE_ARRAY(byte[].class),
    STRING(String.class),
    INTEGER(Integer.class),
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
    ENUMERATION(Enum.class),
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

    TypeEnum(Class<?> type) {
    this.type = type;
    }

    public boolean isEnumeration(Class<?> type) {
        return type.isEnum();
    }

    public boolean isBaseJavaType() {
        return isBaseJavaType(this) || this.type.isPrimitive();
    }

    public boolean isBaseJavaType(TypeEnum typeEnum) {
        switch (typeEnum) {
            case STRING:
            case INTEGER:
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
            case LIST:
            case ENUMERATION:
            case OBJECT:
            case BYTE_ARRAY:
            case TYPE_ARRAY_STRING:default:return false;

        }
    }

    public String getTypescriptType() {

        switch (this) {
            case STRING:
            case TYPE_CHAR:
                return "string";
            case INTEGER:
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
            case ENUMERATION:
                return "enum ";
            case OBJECT:
            default:
                return "any";
        }


    }

    public String getTypescriptInitValue() {
        switch (this) {
            case TYPE_CHAR:
            case BYTE_ARRAY:
            case STRING:
                return "''";
            case INTEGER:
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
            case ENUMERATION:
            case OBJECT:
            default:
                return "{}";
        }
        return null;
    }


    public static TypeEnum byJavaTypeSimpleName(String javaTypeSimpleName) {
        for (TypeEnum typeEnum : TypeEnum.values()) {
            if (typeEnum.getJavaTypeSimpleName().equals(javaTypeSimpleName)) {
                return typeEnum;
            }
        }
        return TypeEnum.OBJECT;
    }

    public static TypeEnum byTypescriptType(String typescriptType) {
        for (TypeEnum typeEnum : TypeEnum.values()) {
            if (typeEnum.getTypescriptType().equals(typescriptType)) {
                return typeEnum;
            }
        }
        return TypeEnum.OBJECT;
    }

    public static TypeEnum byField(Field source) {
        if (source.getType().isEnum()) {
            return TypeEnum.ENUMERATION;
        }
        for (TypeEnum typeEnum : TypeEnum.values()) {
            Class<?> type = typeEnum.getType();
            if (Objects.equals(type.getSimpleName().toLowerCase(), source.getType().getSimpleName().toLowerCase())) {
                return typeEnum;
            }
        }
        return TypeEnum.OBJECT;
    }
}
