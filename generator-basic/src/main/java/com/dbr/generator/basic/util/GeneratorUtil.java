package com.dbr.generator.basic.util;

import com.dbr.generator.basic.converter.JavaField2PropertieDTOConverter;
import com.dbr.generator.basic.dto.PropertieDTO;
import com.dbr.generator.basic.dto.PropertieDTO;
import com.dbr.generator.basic.model.TypescriptProperty;
import com.dbr.util.DataTypes;
import com.dbr.util.resource.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.Email;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratorUtil {

    private static final Logger _log = LoggerFactory.getLogger(GeneratorUtil.class);

    public static String getIDClazzSimpleName(Class<?> clazz) {
        String idClazzSimpleName = null;
        IdClass idClassAnnotation = clazz.getAnnotation(IdClass.class);
        if (idClassAnnotation != null) {
            idClazzSimpleName = idClassAnnotation.value().getSimpleName();
        } else {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getAnnotation(Id.class) != null) {
                    idClazzSimpleName = field.getType().getSimpleName();
                    break;
                }
                if (field.getAnnotation(EmbeddedId.class) != null) {
                    idClazzSimpleName = field.getType().getName();
                    break;
                }
            }
        }
        return idClazzSimpleName;
    }

    public static String getIDFieldName(Class<?> clazz) {
        String retval = getIDClazzSimpleName(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null) {
                retval = field.getName();
                break;
            }
            if (field.getAnnotation(EmbeddedId.class) != null) {
                retval = field.getType().getName();
                break;
            }
        }
        return retval;
    }

    public static List<Field> getPrimitivesOnly(Class<?> clazz, String... ignoreFields) {
        return getPrimitivesOnly(clazz, false, ignoreFields);
    }

    /**
     * get only field with primitives datatypes, also fields with enumerations are included
     *
     * @param clazz
     * @param withSuperClasses
     * @return
     */
    public static List<Field> getPrimitivesOnly(Class<?> clazz, boolean withSuperClasses, String... ignoreFields) {
        List<Field> primitiveFields = new ArrayList<>();
        for (Field field : getDeclaredFields(clazz, withSuperClasses)) {
            boolean ignoreField = false;
            if (ignoreFields != null) {
                for (String ignoreFiled : ignoreFields) {
                    if (field.getName().equals(ignoreFiled)) {
                        ignoreField = true;
                        break;
                    }
                }
            }
            if (ignoreField) {
                continue;
            }
            if (isBaseJavaType(field.getType()) || (isEnumeration(field.getType()))) {
                primitiveFields.add(field);
            }
        }
        return primitiveFields;
    }

    private static List<Field> getDeclaredFields(Class<?> clazz, boolean withSuperClasses) {
        List<Field> retval;
        Class<?> superclass = clazz.getSuperclass();
        if (!withSuperClasses || superclass.equals(Object.class)) {
            retval = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        } else {
            retval = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
            retval.addAll(getDeclaredFields(superclass, withSuperClasses));
        }
        return retval;
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
                _log.info("no baseJavaType: {}", typeSimpleName);
                break;
        }
        return baseJavaType;
    }

    public static Field[] getFieldsByTypeName(Class<?> clazz, String typeName) {
        List<Field> fields = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getType().getSimpleName().equals(typeName)) {
                fields.add(field);
            }
        }
        return fields.toArray(new Field[fields.size()]);
    }

    public static List<PropertieDTO> getJavaProperties(Class<?> clazz) {
        return getJavaProperties(clazz, false);
    }

    public static List<PropertieDTO> getJavaProperties(Class<?> clazz, boolean withSuperClasses) {
        List<PropertieDTO> javaProperties = new ArrayList<>();
        for (Field field : getPrimitivesOnly(clazz, withSuperClasses)) {
            javaProperties.add(new JavaField2PropertieDTOConverter().convert(field));
        }
        return javaProperties;
    }

    public static boolean isSearchableType(String typeSimpleName) {
        return isBaseJavaType(typeSimpleName) && !typeSimpleName.contains("[")
                && !typeSimpleName.contains(DataTypes.TYPE_DATE);
    }

    public static List<TypescriptProperty> getTypescriptProperties(Field[] fields) {
        List<TypescriptProperty> properties = new ArrayList<>();
        for (Field field : fields) {
            properties.add(new TypescriptProperty(field));
        }
        return properties;
    }

    public static String toTypescriptType(String javaType) {
        switch (javaType) {
            case DataTypes.TYPE_CHAR:
            case DataTypes.TYPE_STRING:
            case DataTypes.TYPE_BOOLEAN:
                return javaType.toLowerCase();
            case DataTypes.JAVA_TYPE_BYTE_ARRAY:
                return "string";
            case DataTypes.TYPE_SHORT:
            case DataTypes.TYPE_INTEGER:
            case DataTypes.TYPE_LONG:
            case DataTypes.TYPE_FLOAT:
            case DataTypes.TYPE_BIG_DECIMAL:
            case DataTypes.TYPE_DOUBLE:
                return "number";
            case DataTypes.TYPE_DATE:
            default:
                return javaType;
        }
    }

    public static String getSampleDataTypescript(String javaType) {
        switch (javaType) {
            case DataTypes.TYPE_CHAR:
            case DataTypes.TYPE_STRING:
            case DataTypes.JAVA_TYPE_BYTE_ARRAY:
                return "''";
            case DataTypes.TYPE_BOOLEAN:
                return "false";
            case DataTypes.TYPE_DATE:
                return "new Date()";
            case DataTypes.TYPE_SHORT:
            case DataTypes.TYPE_INTEGER:
            case DataTypes.TYPE_LONG:
            case DataTypes.TYPE_FLOAT:
            case DataTypes.TYPE_BIG_DECIMAL:
            case DataTypes.TYPE_DOUBLE:
                return "0";
            default:
                return "null";
        }
    }

    public static boolean isIdField(Field field) {
        return field != null && field.getAnnotation(Id.class) != null;
    }

    public static String getSampleDataSwaggerAPI(Field field) {
        String sampleData = getSampleDataTypescript(field);

        if (field.getAnnotation(Id.class) != null) {
            return ", example = \"null\"";
        }

        String typeSimpleName = field.getType().getSimpleName();

        if (isEnumeration(field.getType())) {
            return ", example = \"\"";
        }

        switch (typeSimpleName) {
            case DataTypes.JAVA_TYPE_BYTE_ARRAY:
                return ", example = \"null\"";
            case DataTypes.TYPE_DATE:
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm.ssZ");
                String dateAsString = "\"" + simpleDateFormat.format(new Date()) + "\"";
                return ", example = " + dateAsString + "";
            default:
                String value = sampleData.startsWith("\"") ? sampleData : "\"" + sampleData + "\"";
                return ", example = " + value + "";
        }

    }

    public static String getSampleDataTypescript(Field field) {
        Integer maxLength = null;
        Column columnAnnotation = field.getAnnotation(Column.class);
        if (columnAnnotation != null && columnAnnotation.length() > 0) {
            maxLength = columnAnnotation.length();
        }

        Class<?> fieldType = field.getType();
        String typeSimpleName = fieldType.getSimpleName();
        switch (typeSimpleName) {
            case DataTypes.TYPE_STRING:
                return "\"\"";
            case DataTypes.TYPE_DATE:
                return "new Date()";
            case DataTypes.TYPE_CHAR:
                return "X";
            case DataTypes.TYPE_BOOLEAN:
                return "true";
            case DataTypes.TYPE_SHORT:
            case DataTypes.TYPE_INTEGER:
            case DataTypes.TYPE_LONG:
            case DataTypes.TYPE_FLOAT:
            case DataTypes.TYPE_DOUBLE:
                return String.format("%d", ThreadLocalRandom.current().nextInt(0, 10000));
            case DataTypes.TYPE_BIG_DECIMAL:
                return "BigDecimal.ZERO";
            case DataTypes.JAVA_TYPE_BYTE_ARRAY:
                return "\"\".getBytes()";
            default:
                if (isEnumeration(fieldType)) {
                    return typeSimpleName + ".values()[0]";
                }

        }
        return field.getAnnotation(Id.class) != null ? "" : field.getName();
    }


    public static List<PropertieDTO> toJavaProperties(List<PropertieDTO> javaProperties) {
        List<PropertieDTO> retval = new ArrayList<>();
        javaProperties.forEach(csvPropertieDTO -> {
            retval.add(csvPropertieDTO);
        });
        return retval;
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

    public static void makeDir(File folder) {
        String absolutePath = folder.getAbsolutePath();
        if (!folder.exists()) {
            _log.info("create folder: {}", absolutePath);
            folder.mkdir();
        } else {
            _log.info("folder already exists: {}", absolutePath);
        }
    }
}