package com.dbr.generator.basic.dto;

import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.merger.TemplateEnum;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.util.StringUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDTO {

    private String name;
    private TemplateEnum template;
    private TypeEnum idTypeEnum;

    private ProjectDTO project;

    private List<ItemDTO> subItems = new ArrayList<>();
    private List<PropertyDTO> properties = new ArrayList<>();

    public ItemDTO(String name, TemplateEnum template, ProjectDTO project) {
        this.name = name;
        this.template = template;
        this.project = project;
    }

    public Boolean useJPAIdClazz() {
        return this.idTypeEnum != null;
    }

    public String getTableName() {
        return StringUtil.toDatabaseName(getJavaClazzSimpleName());
    }

    public String getJavaClazzSimpleName() {
        return GeneratorUtil.getJavaSimpleClazzName(name);
    }

    public String getJavaClazzName() {
        return name;
    }

    public String getJavaPackageName() {
        return GeneratorUtil.getJavaPackageName(name);
    }

    public String getTypescriptModelName() {
        return new StringBuilder().append(getJavaClazzSimpleName()).append("Model").toString();
    }

    public String getJavaIdClazzSimpleName() {
        return GeneratorUtil.getJavaSimpleClazzName(this.name);
    }

    public String getTypescriptModelFilename() {
        return String.format("%s.ts", getTypescriptModelFilename().toLowerCase());
    }

    public String getPackagePath() {
        return GeneratorUtil.getPackagePath(GeneratorUtil.getJavaPackageName(name));
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

    public String getFilePath() {
        return new StringBuilder().append("src/main/java/").append(getPackagePath()).append(getJavaClazzSimpleName()).append(".java").toString();

    }
}
