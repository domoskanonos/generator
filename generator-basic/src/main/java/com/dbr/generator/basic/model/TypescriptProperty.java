package com.dbr.generator.basic.model;

import com.dbr.util.DataTypes;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.lang.reflect.Field;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TypescriptProperty {

    private Field field;
    private String fieldName;
    private String typeName;
    private String wcAtomicInputfieldType;
    private String wcAtomicInputfieldValueProperty;
    private String defaultValue;
    private Boolean idField;
    private KeyValue i18nKeyValue;

    public TypescriptProperty(Field field) {
        this.idField = field.isAnnotationPresent(Id.class);
        this.field = field;
        this.fieldName = field.getName();
        String typeSimpleName = field.getType().getSimpleName();
        this.typeName = GeneratorUtil.toTypescriptType(typeSimpleName);
        this.wcAtomicInputfieldType = toWCAtomicInputfieldType(typeSimpleName);
        this.wcAtomicInputfieldValueProperty = toWCAtomicInputfieldValueProperty(typeSimpleName);
        this.defaultValue = GeneratorUtil.getSampleDataTypescript(typeSimpleName);
    }

    private String toWCAtomicInputfieldType(String javaType) {
        switch (javaType) {
        case DataTypes.TYPE_BOOLEAN:
            return "${InputfieldType.CHECKBOX}";
        case DataTypes.JAVA_TYPE_BYTE_ARRAY:
            return "${InputfieldType.TEXTAREA}";
        case DataTypes.TYPE_SHORT:
        case DataTypes.TYPE_INTEGER:
        case DataTypes.TYPE_LONG:
        case DataTypes.TYPE_FLOAT:
        case DataTypes.TYPE_BIG_DECIMAL:
        case DataTypes.TYPE_DOUBLE:
            return "${InputfieldType.NUMBER}";
        case DataTypes.TYPE_DATE:
            return "${InputfieldType.DATE}";
        case DataTypes.TYPE_CHAR:
        case DataTypes.TYPE_STRING:
        default:
            return "${InputfieldType.TEXT}";
        }
    }

    private String toWCAtomicInputfieldValueProperty(String javaType) {
        switch (javaType) {
        case DataTypes.TYPE_BOOLEAN:
            return ".checked";
        case DataTypes.JAVA_TYPE_BYTE_ARRAY:
        case DataTypes.TYPE_SHORT:
        case DataTypes.TYPE_INTEGER:
        case DataTypes.TYPE_LONG:
        case DataTypes.TYPE_FLOAT:
        case DataTypes.TYPE_BIG_DECIMAL:
        case DataTypes.TYPE_DOUBLE:
        case DataTypes.TYPE_DATE:
        case DataTypes.TYPE_CHAR:
        case DataTypes.TYPE_STRING:
        default:
            return ".value";
        }
    }

    public String getI18nKey() {
        return i18nKeyValue.getKey();
    }

    public boolean isSearchable() {
        return GeneratorUtil.isSearchableType(typeName) && !GeneratorUtil.isIdField(field);
    }

}
