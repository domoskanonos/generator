package com.dbr.generator.basic.dto;

import com.dbr.generator.basic.dto.project.ProjectDTO;
import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.merger.TemplateEnum;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.util.StringUtil;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDTO {

    private String name;
    private TemplateEnum template;
    private TypeEnum idTypeEnum;

    private ProjectDTO project;

    private List<PropertyDTO> properties = new ArrayList<>();

    public ItemDTO(String name, TemplateEnum template, TypeEnum idTypeEnum, ProjectDTO project) {
        this.name = name;
        this.template = template;
        this.idTypeEnum = idTypeEnum;
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

    public String getJavaPackageName() {
        return project.getJavaBasePackage();
    }

    public String getJavaMappingClazzName() {
        return new StringBuilder().append(getJavaMappingPackageName()).append(".").append(getJavaMappingClazzSimpleName()).toString();
    }

    public String getJavaMappingPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".mapping").toString();
    }

    public String getJavaMappingClazzSimpleName() {
        return new StringBuilder().append(name).append("Mapping").toString();
    }

    public String getJavaJPAClazzName() {
        return new StringBuilder().append(getJavaJPAPackageName()).append(".").append(getJavaJPAClazzSimpleName()).toString();
    }

    public String getJavaJPAPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".entity").toString();
    }

    public String getJavaJPAClazzSimpleName() {
        return new StringBuilder().append(name).append("Entity").toString();
    }

    public String getJavaJPARepositoryClazzName() {
        return new StringBuilder().append(getJavaJPARepositoryPackageName()).append(".").append(getJavaJPARepositoryClazzSimpleName()).toString();
    }

    public String getJavaJPARepositoryPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".repository").toString();
    }

    public String getJavaJPARepositoryClazzSimpleName() {
        return new StringBuilder().append(name).append("Repository").toString();
    }

    public String getJavaDTOClazzName() {
        return new StringBuilder().append(getJavaDTOPackageName()).append(".").append(getJavaDTOClazzSimpleName()).toString();
    }

    public String getJavaDTOPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".dto").toString();
    }

    public String getJavaDTOClazzSimpleName() {
        return new StringBuilder().append(name).append("DTO").toString();
    }

    public String getJavaServiceClazzName() {
        return new StringBuilder().append(getJavaServicePackageName()).append(".").append(getJavaServiceClazzSimpleName()).toString();
    }

    public String getJavaServicePackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".service").toString();
    }

    public String getJavaServiceClazzSimpleName() {
        return new StringBuilder().append(name).append("Service").toString();
    }

    public String getTypescriptModelName() {
        return new StringBuilder().append(getJavaClazzSimpleName()).append("Model").toString();
    }

    public String getJavaIdClazzSimpleName() {
        return this.idTypeEnum.getJavaTypeSimpleName();
    }

    public String getTypescriptModelFilename() {
        return String.format("%s.ts", getTypescriptModelFilename().toLowerCase());
    }

    public void addProperty(PropertyDTO propertyDTO) {
        if (properties == null) {
            properties = new ArrayList<>();
        }
        properties.add(propertyDTO);
    }

    public File getFilePath() {
        return new File(this.project.getProjectFolder(), getFileSuffix());
    }

    private String getFileSuffix() {
        StringBuilder sb = new StringBuilder();
        if (this.project.getJavaBasePackage() != null) {
            sb = sb.append("src/main/java/");
        }
        switch (this.template) {
            case DTO_TEMPLATE:
                return new StringBuilder().append(GeneratorUtil.getPackagePath(getJavaDTOClazzName())).append(".java").toString();
            case ENTITY_TEMPLATE:
                return new StringBuilder().append(GeneratorUtil.getPackagePath(getJavaJPAClazzName())).append(".java").toString();
            case CLAZZ_MAPPING_TEMPLATE:
                return new StringBuilder().append(GeneratorUtil.getPackagePath(getJavaMappingClazzName())).append(".java").toString();
            case SPRINGBOOT_JPA_REPOSITORY_TEMPLATE:
                return new StringBuilder().append(GeneratorUtil.getPackagePath(getJavaJPARepositoryClazzName())).append(".java").toString();
            case SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE:
                return new StringBuilder().append(GeneratorUtil.getPackagePath(getJavaServiceClazzName())).append(".java").toString();
            case TYPESCRIPT_MODEL_TEMPLATE:
                return new StringBuilder().append(GeneratorUtil.getPackagePath(getTypescriptModelFilename())).toString();
            default:
                throw new RuntimeException("error determinate file path...");
        }

    }

}
