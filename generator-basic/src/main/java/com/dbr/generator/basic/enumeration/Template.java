package com.dbr.generator.basic.enumeration;

import com.dbr.generator.basic.util.GeneratorUtil;
import lombok.Getter;

@Getter
public enum Template {

    PROJECT_TYPESCRIPT_NIDOCA_I18N("project/typescript/nidoca/i18n.vm", "i18n/", "message-de", LanguageType.JSON, false),
    PROJECT_TYPESCRIPT_NIDOCA_INDEX("project/typescript/nidoca/index.vm", "", "index", LanguageType.TYPESCRIPT, false),
    PROJECT_TYPESCRIPT_NIDOCA_SERVICE_PAGE("project/typescript/nidoca/page-service.vm", "service/", "page-service", LanguageType.TYPESCRIPT, false),
    PROJECT_TYPESCRIPT_NIDOCA_PAGE_DEFAULT("project/typescript/nidoca/page-default.vm", "pages/", "page-default", LanguageType.TYPESCRIPT, false),

    ITEM_JAVA_DTO_TEMPLATE("item/java/dto.vm", "", "", LanguageType.JAVA, false),
    ITEM_JAVA_ENTITY_TEMPLATE("item/java/entity.vm", "", "", LanguageType.JAVA, false),
    ITEM_JAVA_CLAZZ_MAPPING_TEMPLATE("item/java/springboot/clazz-mapping.vm", "", "", LanguageType.JAVA, false),
    ITEM_JAVA_SPRINGBOOT_JPA_REPOSITORY_TEMPLATE("item/java/springboot/jpa-repository.vm", "", "", LanguageType.JAVA, false),
    ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE("item/java/springboot/jpa-service-basic.vm", "", "", LanguageType.JAVA, false),
    ITEM_JAVA_SPRINGBOOT_JPA_SERVICE_SEARCH_TEMPLATE("item/java/springboot/jpa-service-search.vm", "", "", LanguageType.JAVA, false),
    ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE("item/java/springboot/rest-controller-basic.vm", "", "", LanguageType.JAVA, false),
    ITEM_JAVA_SPRINGBOOT_REST_CONTROLLER_SEARCH_TEMPLATE("item/java/springboot/rest-controller-search.vm", "", "", LanguageType.JAVA, false),
    ITEM_TYPESCRIPT_MODEL_TEMPLATE("item/typescript/model.vm", "model/", "model", LanguageType.TYPESCRIPT, true),
    ITEM_TYPESCRIPT_MODEL_ENUM_TEMPLATE("item/typescript/model-enum.vm", "model/", "model", LanguageType.TYPESCRIPT, true),
    ITEM_TYPESCRIPT_REMOTE_REPOSITORY("item/typescript/remote-repository.vm", "repo/", "-repository", LanguageType.TYPESCRIPT, false),
    ITEM_TYPESCRIPT_ENUM_REPOSITORY("item/typescript/enum-repository.vm", "repo/", "-enum-repository", LanguageType.TYPESCRIPT, false),
    ITEM_TYPESCRIPT_REMOTE_SERVICE("item/typescript/remote-service.vm", "service/", "-service", LanguageType.TYPESCRIPT, false),
    ITEM_TYPESCRIPT_NIDOCA_PAGE_EDIT("item/typescript/nidoca/page/edit.vm", "pages/page-", "-edit", LanguageType.TYPESCRIPT, false),
    ITEM_TYPESCRIPT_NIDOCA_PAGE_LIST("item/typescript/nidoca/page/search-list.vm", "pages/page-", "-list", LanguageType.TYPESCRIPT, false),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_EDIT("item/typescript/nidoca/component/edit.vm", "components/", "-edit", LanguageType.TYPESCRIPT, false),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_LIST("item/typescript/nidoca/component/search-list.vm", "components/", "-list", LanguageType.TYPESCRIPT, false),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_COMBOBOX_MULTI("item/typescript/nidoca/component/combobox.vm", "components/", "-combobox", LanguageType.TYPESCRIPT, false),
    ITEM_TYPESCRIPT_NIDOCA_COMPONENT_COMBOBOX_MULTI_ENUM("item/typescript/nidoca/component/combobox-enum.vm", "components/", "-enum-combobox", LanguageType.TYPESCRIPT, false);

    private String templatePath;

    private String filePrefix;

    private String fileSuffix;

    private LanguageType languageType;

    private boolean appendToFile;

    Template(String templatePath, String filePrefix, String fileSuffix, LanguageType languageType, boolean appendToFile) {
        this.templatePath = templatePath;
        this.filePrefix = filePrefix;
        this.fileSuffix = fileSuffix;
        this.languageType = languageType;
        this.appendToFile = appendToFile;
    }

    public String getProjectFilePath(String name) {
        return new StringBuffer().append(this.languageType.getSourceFolderPath()).append(getFilenameWithSuffix(name)).toString();
    }

    public String getFilenameWithSuffix(String name) {
        return new StringBuffer().append(getFilename(name)).append(".").append(this.languageType.getFileEnding()).toString();
    }

    public String getFilename(String name) {
        switch (languageType) {
            case JAVA:
                return new StringBuffer().append(filePrefix).append(name).append(fileSuffix).toString();
            case TYPESCRIPT:
            case JSON:
                return new StringBuffer().append(filePrefix).append(GeneratorUtil.upperCaseToHyphen(name)).append(fileSuffix).toString();
        }
        return name;
    }

}

