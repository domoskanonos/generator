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
    BOOLEAN(Boolean.class),
    BIG_DECIMAL(BigDecimal.class),
    LONG(Long.class),
    FLOAT(Float.class),
    SHORT(Short.class),
    DOUBLE(Double.class),
    TYPE_CHAR(char.class),
    DATE(Date.class),
    DATE_ISO8601(Date.class),
    ARRAY_STRING(String[].class),
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
            case BOOLEAN:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
            case TYPE_CHAR:
            case DATE:
            case DATE_ISO8601:
                return true;
            case LIST:
            case ENUMERATION:
            case OBJECT:
            case BYTE_ARRAY:
            case ARRAY_STRING:
            default:
                return false;

        }
    }

    public boolean isSearchable() {
        switch (this) {
            case STRING:
            case INTEGER:
            case BOOLEAN:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
            case TYPE_CHAR:
                return true;
            case DATE:
            case DATE_ISO8601:
            case LIST:
            case ENUMERATION:
            case OBJECT:
            case BYTE_ARRAY:
            case ARRAY_STRING:
            default:
                return false;

        }
    }

    public String getTypescriptType() {

        switch (this) {
            case STRING:
            case TYPE_CHAR:
                return "string";
            case INTEGER:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
                return "number";
            case BOOLEAN:
                return "boolean";
            case DATE:
            case DATE_ISO8601:
                return "Date";
            case ARRAY_STRING:
                return "string[]";
            case BYTE_ARRAY:
            case LIST:
                return "any[]";
            case ENUMERATION:
                return "string ";
            case OBJECT:
            default:
                return "any";
        }


    }

    public String getTypescriptInitValue() {
        switch (this) {
            case TYPE_CHAR:
            case BYTE_ARRAY:
            case ENUMERATION:
            case STRING:
                return "''";
            case BOOLEAN:
                return "false";
            case INTEGER:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
                return "0";
            case DATE:
            case DATE_ISO8601:
                return "new Date()";
            case ARRAY_STRING:
            case LIST:
                return "[]";
            case OBJECT:
            default:
                return "{}";
        }
    }

    public String getTypescriptNidocaInputfieldType() {
        switch (this) {
            case TYPE_CHAR:
            case BYTE_ARRAY:
            case ENUMERATION:
            case STRING:
                return "${InputfieldType.TEXT}";
            case BOOLEAN:
                return "${InputfieldType.SWITCH}";
            case INTEGER:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
                return "${InputfieldType.NUMBER}";
            case DATE:
            case DATE_ISO8601:
                return "${InputfieldType.DATE}";
            case LIST:
            case OBJECT:
                return "${InputfieldType.COMBOBOX}";
            case ARRAY_STRING:
            default:
                return "${InputfieldType.TEXT}";
        }
    }
    public String getTypescriptNidocaInputfieldValueFieldName() {
        switch (this) {
             case BOOLEAN:
                return ".checked";
            case ARRAY_STRING:
            case OBJECT:
            case BYTE_ARRAY:
                return ".options";
            case INTEGER:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
            case DATE:
            case DATE_ISO8601:
            case TYPE_CHAR:
            case ENUMERATION:
            case STRING:
            case LIST:
            default:
                return ".value";
        }
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
