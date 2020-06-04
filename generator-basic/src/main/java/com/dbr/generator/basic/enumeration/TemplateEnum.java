package com.dbr.generator.basic.enumeration;

import lombok.Getter;

@Getter
public enum TemplateEnum {

    DTO_TEMPLATE("java/dto.vm", "", ItemType.JAVA),
    ENTITY_TEMPLATE("java/entity.vm", "", ItemType.JAVA),
    CLAZZ_MAPPING_TEMPLATE("java/springboot/clazz-mapping.vm", "", ItemType.JAVA),
    SPRINGBOOT_JPA_REPOSITORY_TEMPLATE("java/springboot/jpa-repository.vm", "", ItemType.JAVA),
    SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE("java/springboot/jpa-service-basic.vm", "", ItemType.JAVA),
    SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE("java/springboot/rest-controller-basic.vm", "", ItemType.JAVA),
    TYPESCRIPT_MODEL_TEMPLATE("typescript/model.vm", "model/", ItemType.TYPESCRIPT),
    TYPESCRIPT_REMOTE_REPOSITORY("typescript/remote-repository.vm", "repo/", ItemType.TYPESCRIPT);

    private String templatePath;

    private String filePathPrefix;

    private ItemType itemType;

    TemplateEnum(String templatePath, String filePathPrefix, ItemType itemType) {
        this.templatePath = templatePath;
        this.filePathPrefix = filePathPrefix;
        this.itemType = itemType;
    }
}
