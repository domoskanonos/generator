package com.dbr.generator.basic.item.dto;

import com.dbr.generator.basic.property.dto.PropertyDTO;
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
    private String javaIdClazzSimpleName;

    private List<ItemDTO> referencedItems;
    private List<PropertyDTO> properties;

    public Boolean useJPAIdClazz() {
        return this.javaIdClazzSimpleName != null;
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
        if (referencedItems == null) {
            referencedItems = new ArrayList<>();
        }
        referencedItems.add(itemDTO);
    }

}
