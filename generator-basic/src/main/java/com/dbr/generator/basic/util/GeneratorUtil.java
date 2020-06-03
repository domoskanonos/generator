package com.dbr.generator.basic.util;

import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.util.DataTypes;
import com.dbr.util.SystemUtil;
import com.dbr.util.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratorUtil {

    private static final Logger _log = LoggerFactory.getLogger(GeneratorUtil.class);

    public static String getIDClazzSimpleName(Class<?> clazz) {
        String javaIdClazzSimpleName = null;
        IdClass idClassAnnotation = clazz.getAnnotation(IdClass.class);
        if (idClassAnnotation != null) {
            javaIdClazzSimpleName = idClassAnnotation.value().getSimpleName();
        } else {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getAnnotation(Id.class) != null) {
                    javaIdClazzSimpleName = field.getType().getSimpleName();
                    break;
                }
                if (field.getAnnotation(EmbeddedId.class) != null) {
                    javaIdClazzSimpleName = field.getType().getName();
                    break;
                }
            }
        }
        return javaIdClazzSimpleName;
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

    public static List<PropertyDTO> getJavaProperties(Class<?> clazz) {
        return getJavaProperties(clazz, false);
    }

    public static List<PropertyDTO> getJavaProperties(Class<?> clazz, boolean withSuperClasses) {
        List<PropertyDTO> javaProperties = new ArrayList<>();
        for (Field field : getPrimitivesOnly(clazz, withSuperClasses)) {
            //javaProperties.add(new JavaField2PropertyDTOConverter().convert(new ConverterDTO<>(new JavaClass2ItemDTOConverter().convert(new ConverterDTO<>(new ProjectDTO(), clazz)), field)));
        }
        return javaProperties;
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

    public static String getPackagePath(String packageName) {
        return new StringBuilder().append(packageName.replace(".", "/")).toString();
    }

    public static void deleteFile(File file) throws IOException {
        String absolutePath = file.getAbsolutePath();
        if (file.exists()) {

            if (file.isDirectory()) {
                _log.info("delete directory recursive: {}", absolutePath);
                FileUtils.deleteDirectory(file);
                return;
            }

            _log.info("delete file: {}", absolutePath);
            if (file.delete()) {
                _log.info("file deleted: {}", absolutePath);
            }
        } else {
            _log.info("file not exist: {}", absolutePath);
        }
    }

    public static void createFromArchetype(File folder, String artifactId, String groupId, String archetypeArtifactId,
                                     String archetypeGroupId) throws IOException, InterruptedException {
        _log.info("execute create from maven archetype command, path: {}", folder.getAbsolutePath());
        String createFromArchetypeCommand = new StringBuilder().append("mvn -DgroupId=").append(groupId).append(" -DartifactId=").append(artifactId)
                .append(" -Dversion=1.0.0 archetype:generate -B -DarchetypeGroupId=").append(archetypeGroupId)
                .append(" -DarchetypeArtifactId=").append(archetypeArtifactId)
                .append(" -DarchetypeVersion=1.0.0 -DarchetypeRepository=local").toString();
        _log.info("execute create from maven archetype command, command: {}", createFromArchetypeCommand);
        SystemUtil.executeCommand(folder, "cmd.exe", "/C",
                createFromArchetypeCommand);
    }

    public static void createMavenArchetype(File folder) throws IOException, InterruptedException {
        _log.info("execute create maven archetype command, path: {}", folder.getAbsolutePath());
        SystemUtil.executeCommand(folder, "cmd.exe", "/C", "create-maven-archetype.bat");
    }

    public static void unzipFile(File file, File destination) throws IOException {
        _log.info("unzip file, file: {}", file.getAbsolutePath());
        ZipUtil.unzipFile(file, destination);
    }

    public static File copyUrlToTempFolder(String url, File destination) throws IOException {
        _log.info("copy url to temp folder, url: {}", url);
        FileUtils.copyURLToFile(new URL(url), destination, 3000, 3000);
        return destination;
    }

    public static String getJavaSimpleClazzName(String javaClazzName) {
        return javaClazzName.substring(javaClazzName.lastIndexOf(".") + 1);
    }

    public static String getJavaPackageName(String javaClazzName) {
        return javaClazzName.substring(0, javaClazzName.lastIndexOf("."));
    }
}