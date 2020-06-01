package com.dbr.generator.basic.dto;

import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.util.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDTO {

    private String templatePath;
    private String filePath;
    private String javaClazzName;
    private String javaIdClazzName;

    private List<ItemDTO> subItems;
    private List<PropertyDTO> properties;

    public Boolean useJPAIdClazz() {
        return this.javaIdClazzName != null;
    }

    public String getTableName() {
        return StringUtil.toDatabaseName(getJavaClazzSimpleName());
    }

    public String getJavaClazzSimpleName() {
        return GeneratorUtil.getJavaSimpleClazzName(javaClazzName);
    }

    public String getJavaPackageName() {
        return GeneratorUtil.getJavaPackageName(javaClazzName);
    }

    public String getTypescriptModelName() {
        return new StringBuilder().append(getJavaClazzSimpleName()).append("Model").toString();
    }

    public String getTypescriptModelFilename() {
        return String.format("%s.ts", getTypescriptModelFilename().toLowerCase());
    }

    public String getPackagePath() {
        return GeneratorUtil.getPackagePath(GeneratorUtil.getJavaPackageName(javaClazzName));
    }

    public void addProperty(PropertyDTO propertyDTO) {
        if (properties == null) {
            properties = new ArrayList<>();
        }
        properties.add(propertyDTO);
    }


    public void addItemDTO(ItemDTO itemDTO) {
        if (subItems == null) {
            subItems = new ArrayList<>();
        }
        subItems.add(itemDTO);
    }

}
