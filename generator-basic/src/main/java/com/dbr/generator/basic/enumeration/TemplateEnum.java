package com.dbr.generator.basic.enumeration;

import lombok.Getter;

@Getter
public enum TemplateEnum {

    PROJECT_TYPESCRIPT_NIDOCA_PAGE_SERVICE("project/typescript/app/page-service.vm", "service/", "", LanguageType.TYPESCRIPT),

    ITEM_JAVA_DTO_TEMPLATE("item/java/dto.vm", "", "", LanguageType.JAVA),
    ITEM_JAVA_ENTITY_TEMPLATE("item/java/entity.vm", "", "", LanguageType.JAVA),
    ITEM_JAVA_CLAZZ_MAPPING_TEMPLATE("item/java/springboot/clazz-mapping.vm", "", "", LanguageType.JAVA),
    ITEM_JAVA_SPRINGBOOT_JPA_REPOSITORY_TEMPLATE("item/java/springboot/jpa-repository.vm", "", "", LanguageType.JAVA),
    ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE("item/java/springboot/jpa-service-basic.vm", "", "", LanguageType.JAVA),
    ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE("item/java/springboot/rest-controller-basic.vm", "", "", LanguageType.JAVA),
    ITEM_TYPESCRIPT_MODEL_TEMPLATE("item/typescript/model.vm", "model/", "-model", LanguageType.TYPESCRIPT),
    ITEM_TYPESCRIPT_REMOTE_REPOSITORY("item/typescript/remote-repository.vm", "repo/", "-repository", LanguageType.TYPESCRIPT),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_EDIT("item/typescript/nidoca/component/edit.vm", "components/", "-edit", LanguageType.TYPESCRIPT),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_LIST("item/typescript/nidoca/component/search-list.vm", "components/", "-list", LanguageType.TYPESCRIPT);

    private String templatePath;

    private String filePrefix;

    private String fileSuffix;

    private LanguageType languageType;

    TemplateEnum(String templatePath, String filePrefix, String fileSuffix, LanguageType languageType) {
        this.templatePath = templatePath;
        this.filePrefix = filePrefix;
        this.fileSuffix = fileSuffix;
        this.languageType = languageType;
    }

    public String getFileSuffix(String name) {
        StringBuilder sb = new StringBuilder();
        sb = sb.append(this.languageType.getSourceFolderPath()).append(getFilePrefix()).append(name).append(getFileSuffix()).append(".").append(this.languageType.getFileEnding());
        return sb.toString();
    }

}

