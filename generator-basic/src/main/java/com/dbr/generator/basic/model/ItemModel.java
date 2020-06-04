package com.dbr.generator.basic.model;

import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.generator.basic.enumeration.TemplateEnum;
import com.dbr.generator.basic.model.project.ProjectModel;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.util.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.*;

@Data
@NoArgsConstructor
public class ItemModel {

    private ProjectModel projectModel;

    private String name;

    private Set<TemplateEnum> template = new HashSet<>();

    private TypeEnum idTypeEnum;

    private List<PropertyModel> properties = new ArrayList<>();

    public ItemModel(ProjectModel projectModel, String name, TypeEnum idTypeEnum, TemplateEnum... template) {
        this.projectModel = projectModel;
        this.name = name;
        this.template.addAll(Arrays.asList(template));
        this.idTypeEnum = idTypeEnum;
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
        return projectModel.getNamespase();
    }

    public String getJavaMappingClazzName() {
        return new StringBuilder().append(getJavaMappingPackageName()).append(".").append(getJavaMappingClazzSimpleName()).toString();
    }

    public String getJavaMappingPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".mapping").toString();
    }

    public String getJavaMappingClazzSimpleName() {
        return new StringBuilder().append(getjavaJPAClazzSimpleName()).append(getJavaDTOClazzSimpleName()).append("Mapping").toString();
    }

    public String getJavaJPAClazzName() {
        return new StringBuilder().append(getJavaJPAPackageName()).append(".").append(getjavaJPAClazzSimpleName()).toString();
    }

    public String getJavaJPAPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".entity").toString();
    }

    public String getJavaDTOClazzSimpleName() {
        return new StringBuilder().append(name).append("DTO").toString();
    }

    public String getJavaJPARepositoryClazzName() {
        return new StringBuilder().append(getJavaJPARepositoryPackageName()).append(".").append(getJavaJPARepositoryClazzSimpleName()).toString();
    }

    public String getJavaJPARepositoryPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".repository").toString();
    }

    public String getJavaJPARepositoryClazzSimpleName() {
        return new StringBuilder().append(name).append("JPARepository").toString();
    }

    public String getJavaDTOClazzName() {
        return new StringBuilder().append(getJavaDTOPackageName()).append(".").append(getJavaDTOClazzSimpleName()).toString();
    }

    public String getJavaDTOPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".dto").toString();
    }

    public String getjavaJPAClazzSimpleName() {
        return new StringBuilder().append(name).append("Entity").toString();
    }

    public String getJavaServiceBasicClazzName() {
        return new StringBuilder().append(getJavaServiceBasicPackageName()).append(".").append(getJavaServiceBasicClazzSimpleName()).toString();
    }

    public String getJavaServiceBasicPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".service").toString();
    }

    public String getJavaServiceBasicClazzSimpleName() {
        return new StringBuilder().append(name).append("BasicService").toString();
    }

    public String getJavaRestControllerBasicClazzName() {
        return new StringBuilder().append(getJavaRestControllerBasicPackageName()).append(".").append(getJavaRestControllerBasicClazzSimpleName()).toString();
    }

    public String getJavaRestControllerBasicPackageName() {
        return new StringBuilder().append(getJavaPackageName()).append(".rest").toString();
    }

    public String getJavaRestControllerBasicClazzSimpleName() {
        return new StringBuilder().append(name).append("RestBasicController").toString();
    }

    public String getJavaRestControllerPrefix() {
        return new StringBuilder().append(name.toUpperCase()).toString();
    }

    public String getJavaIdClazzSimpleName() {
        return this.idTypeEnum.getJavaTypeSimpleName();
    }

    public String getTypescriptType() {
        return this.idTypeEnum.getTypescriptType();
    }

    public String getTypescriptModelName() {
        return new StringBuilder().append(name).append("Model").toString();
    }

    public String getTypescriptModelNameToLowerCase() {
        return getTypescriptModelName().toLowerCase();
    }

    public String getNameToLowerCase() {
        return name.toLowerCase();
    }

    public String getTypescriptModelFilenameWithSuffix() {
        return new StringBuilder().append(getTypescriptModelFilename()).append(".ts").toString();
    }

    public String getTypescriptModelFilename() {
        return new StringBuilder().append(getNameToLowerCase()).append("-model").toString();
    }

    public String getTypescriptModelPath() {
        return "../" + TemplateEnum.TYPESCRIPT_MODEL_TEMPLATE.getFilePathPrefix() + getTypescriptModelFilename();
    }


    public String getTypescriptRemoteRepositoryFilenameWithSuffix() {
        return new StringBuilder().append(getTypescriptRemoteRepositoryFilename()).append(".ts").toString();
    }

    public String getTypescriptRemoteRepositoryFilename() {
        return new StringBuilder().append(getNameToLowerCase()).append("-repository").toString();
    }

    public String getTypescriptRemoteRepositoryName() {
        return new StringBuilder().append(name).append("RemoteRepository").toString();
    }


    public String getIdFieldName() {
        for (PropertyModel typescriptProperty : properties) {
            if (typescriptProperty.isIdProperty()) {
                return typescriptProperty.getName();
            }
        }
        return null;
    }

    private String getTypescriptNidocaComponentEditFilename() {
        return new StringBuilder().append(getNameToLowerCase()).append("-edit.ts").toString();
    }


    public String getComboboxComponentTagName() {
        return new StringBuilder().append(this.getNameToLowerCase()).append("-combobox-component").toString();
    }

    public String getEditComponentTagName() {
        return new StringBuilder().append(this.getNameToLowerCase()).append("-edit-component").toString();
    }

    public String getSearchNidocaListTagName() {
        return new StringBuilder().append(this.getNameToLowerCase()).append("-search-list-component").toString();
    }

    public String getEditPageTagName() {
        return new StringBuilder().append(this.getNameToLowerCase()).append("-edit-page").toString();
    }

    public String getSearchListPageTagName() {
        return new StringBuilder().append(this.getNameToLowerCase()).append("-search-list-page").toString();
    }

    public void addProperty(PropertyModel propertyModel) {
        if (properties == null) {
            properties = new ArrayList<>();
        }
        properties.add(propertyModel);
    }

    public File getFilePath(File projectFolder, TemplateEnum templateEnum) {
        return new File(projectFolder, getFileSuffix(templateEnum));
    }

    private String getFileSuffix(TemplateEnum templateEnum) {
        StringBuilder sb = new StringBuilder();
        switch (templateEnum.getItemType()) {
            case JAVA:
                sb = sb.append("src/main/java/");
                break;
            case TYPESCRIPT:
                sb = sb.append("source/");
                break;
        }
        String filePathPrefix = templateEnum.getFilePathPrefix();
        switch (templateEnum) {
            case DTO_TEMPLATE:
                return sb.append(GeneratorUtil.getPackagePath(getJavaDTOClazzName())).append(".java").toString();
            case ENTITY_TEMPLATE:
                return sb.append(GeneratorUtil.getPackagePath(getJavaJPAClazzName())).append(".java").toString();
            case CLAZZ_MAPPING_TEMPLATE:
                return sb.append(GeneratorUtil.getPackagePath(getJavaMappingClazzName())).append(".java").toString();
            case SPRINGBOOT_JPA_REPOSITORY_TEMPLATE:
                return sb.append(GeneratorUtil.getPackagePath(getJavaJPARepositoryClazzName())).append(".java").toString();
            case SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE:
                return sb.append(GeneratorUtil.getPackagePath(getJavaServiceBasicClazzName())).append(".java").toString();
            case SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE:
                return sb.append(GeneratorUtil.getPackagePath(getJavaRestControllerBasicClazzName())).append(".java").toString();
            case TYPESCRIPT_MODEL_TEMPLATE:
                return sb.append(filePathPrefix).append(getTypescriptModelFilenameWithSuffix()).toString();
            case TYPESCRIPT_REMOTE_REPOSITORY:
                return sb.append(filePathPrefix).append(getTypescriptRemoteRepositoryFilenameWithSuffix()).toString();
            case TYPESCRIPT_NIDOCA_COMPONENT_EDIT:
                return sb.append(filePathPrefix).append(getTypescriptNidocaComponentEditFilename()).toString();
            default:
                throw new RuntimeException("error determinate file path...");
        }

    }

}
