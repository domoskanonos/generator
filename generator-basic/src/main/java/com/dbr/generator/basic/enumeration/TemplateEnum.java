package com.dbr.generator.basic.enumeration;

import lombok.Getter;

@Getter
public enum TemplateEnum {

    PROJECT_TYPESCRIPT_NIDOCA_I18N("project/typescript/nidoca/i18n.vm", "i18n/", "message-de", LanguageTypeEnum.JSON),
    PROJECT_TYPESCRIPT_NIDOCA_INDEX("project/typescript/nidoca/index.vm", "", "index", LanguageTypeEnum.TYPESCRIPT),
    PROJECT_TYPESCRIPT_NIDOCA_SERVICE_PAGE("project/typescript/nidoca/page-service.vm", "service/", "page-service", LanguageTypeEnum.TYPESCRIPT),
    PROJECT_TYPESCRIPT_NIDOCA_PAGE_DEFAULT("project/typescript/nidoca/page-default.vm", "pages/", "page-default", LanguageTypeEnum.TYPESCRIPT),

    ITEM_JAVA_DTO_TEMPLATE("item/java/dto.vm", "", "", LanguageTypeEnum.JAVA),
    ITEM_JAVA_ENTITY_TEMPLATE("item/java/entity.vm", "", "", LanguageTypeEnum.JAVA),
    ITEM_JAVA_CLAZZ_MAPPING_TEMPLATE("item/java/springboot/clazz-mapping.vm", "", "", LanguageTypeEnum.JAVA),
    ITEM_JAVA_SPRINGBOOT_JPA_REPOSITORY_TEMPLATE("item/java/springboot/jpa-repository.vm", "", "", LanguageTypeEnum.JAVA),
    ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE("item/java/springboot/jpa-service-basic.vm", "", "", LanguageTypeEnum.JAVA),
    ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_SEARCH_TEMPLATE("item/java/springboot/jpa-service-search.vm", "", "", LanguageTypeEnum.JAVA),
    ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE("item/java/springboot/rest-controller-basic.vm", "", "", LanguageTypeEnum.JAVA),
    ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_SEARCH_TEMPLATE("item/java/springboot/rest-controller-search.vm", "", "", LanguageTypeEnum.JAVA),
    ITEM_TYPESCRIPT_MODEL_TEMPLATE("item/typescript/model.vm", "model/", "-model", LanguageTypeEnum.TYPESCRIPT),
    ITEM_TYPESCRIPT_REMOTE_REPOSITORY("item/typescript/remote-repository.vm", "repo/", "-repository", LanguageTypeEnum.TYPESCRIPT),
    ITEM_TYPESCRIPT_REMOTE_SERVICE("item/typescript/remote-service.vm", "service/", "-service", LanguageTypeEnum.TYPESCRIPT),
    ITEM_TYPESCRIPT_NIDOCA_PAGE_EDIT("item/typescript/nidoca/page/edit.vm", "pages/page-", "-edit", LanguageTypeEnum.TYPESCRIPT),
    ITEM_TYPESCRIPT_NIDOCA_PAGE_LIST("item/typescript/nidoca/page/search-list.vm", "pages/page-", "-list", LanguageTypeEnum.TYPESCRIPT),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_EDIT("item/typescript/nidoca/component/edit.vm", "components/", "-edit", LanguageTypeEnum.TYPESCRIPT),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_LIST("item/typescript/nidoca/component/search-list.vm", "components/", "-list", LanguageTypeEnum.TYPESCRIPT),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_COMBOBOX("item/typescript/nidoca/component/combobox.vm", "components/", "-combobox", LanguageTypeEnum.TYPESCRIPT);

    private String templatePath;

    private String filePrefix;

    private String fileSuffix;

    private LanguageTypeEnum languageTypeEnum;

    TemplateEnum(String templatePath, String filePrefix, String fileSuffix, LanguageTypeEnum languageTypeEnum) {
        this.templatePath = templatePath;
        this.filePrefix = filePrefix;
        this.fileSuffix = fileSuffix;
        this.languageTypeEnum = languageTypeEnum;
    }

    public String getProjectFilePath(String name) {
        return new StringBuffer().append(this.languageTypeEnum.getSourceFolderPath()).append(getFilenameWithSuffix(name)).toString();
    }

    public String getFilenameWithSuffix(String name) {
        return new StringBuffer().append(getFilename(name)).append(".").append(this.languageTypeEnum.getFileEnding()).toString();
    }

    public String getFilename(String name) {
        return new StringBuffer().append(filePrefix).append(name).append(fileSuffix).toString();
    }

}

