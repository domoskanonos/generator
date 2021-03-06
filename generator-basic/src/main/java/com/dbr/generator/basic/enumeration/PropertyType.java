package com.dbr.generator.basic.enumeration;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public enum PropertyType {

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
    ARRAY_ENUMERATION(Enum[].class),
    LIST(List.class),
    SET(Set.class),
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

    PropertyType(Class<?> type) {
        this.type = type;
    }

    public boolean isEnumeration(Class<?> type) {
        return type.isEnum();
    }

    public boolean isBaseJavaType() {
        return isBaseJavaType(this) || this.type.isPrimitive();
    }

    public boolean isBaseJavaType(PropertyType propertyType) {
        switch (propertyType) {
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
            case SET:
            case ENUMERATION:
            case OBJECT:
            case BYTE_ARRAY:
            case ARRAY_STRING:
            default:
                return false;

        }
    }

    public String getTypescriptBaseType() {

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
            default:
                return null;
        }


    }

    public String getTypescriptInitValue() {
        switch (this) {
            case TYPE_CHAR:
            case BYTE_ARRAY:
            case STRING:
                return "= ''";
            case BOOLEAN:
                return "= false";
            case INTEGER:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
                return "= 0";
            case DATE:
            case DATE_ISO8601:
                return "= new Date()";
            case ARRAY_STRING:
            case LIST:
            case SET:
                return "= []";
            case ENUMERATION:
                return "| undefined = undefined";
            case OBJECT:
            default:
                return "= {}";
        }
    }

    public String getTypescriptNidocaInputfieldType() {
        switch (this) {
            case TYPE_CHAR:
            case BYTE_ARRAY:
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
            case SET:
            case OBJECT:
            case ENUMERATION:
                return "${InputfieldType.COMBOBOX}";
            case ARRAY_STRING:
            default:
                return "${InputfieldType.TEXT}";
        }
    }

    public boolean isListType() {
        switch (this) {
            case LIST:
            case SET:
            case OBJECT:
            case ARRAY_STRING:
                return true;
            case ENUMERATION:
            case TYPE_CHAR:
            case BYTE_ARRAY:
            case STRING:
            case BOOLEAN:
            case INTEGER:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
            case DATE:
            case DATE_ISO8601:
            default:
                return false;
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
            case ENUMERATION:
                return "value";
            case INTEGER:
            case BIG_DECIMAL:
            case LONG:
            case FLOAT:
            case SHORT:
            case DOUBLE:
            case DATE:
            case DATE_ISO8601:
            case TYPE_CHAR:
            case STRING:
            case LIST:
            case SET:
            default:
                return ".value";
        }
    }

    public static PropertyType byJavaTypeSimpleName(String javaTypeSimpleName) {
        for (PropertyType propertyType : PropertyType.values()) {
            if (propertyType.getJavaTypeSimpleName().equals(javaTypeSimpleName)) {
                return propertyType;
            }
        }
        return null;
    }

    public static PropertyType byTypescriptType(String typescriptType) {
        for (PropertyType propertyType : PropertyType.values()) {
            if (propertyType.getTypescriptBaseType().equals(typescriptType)) {
                return propertyType;
            }
        }
        return null;
    }

    public static PropertyType byField(Field source) {
        if (source.getType().isEnum()) {
            return PropertyType.ENUMERATION;
        }
        for (PropertyType propertyType : PropertyType.values()) {
            Class<?> type = propertyType.getType();
            if (Objects.equals(type.getSimpleName().toLowerCase(), source.getType().getSimpleName().toLowerCase())) {
                return propertyType;
            }
        }
        return PropertyType.OBJECT;
    }
}
