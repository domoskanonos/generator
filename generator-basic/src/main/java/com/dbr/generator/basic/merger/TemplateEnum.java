package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.enumeration.ItemType;
import lombok.Getter;

@Getter
public enum TemplateEnum {

    DTO_TEMPLATE("java/dto.vm", ItemType.JAVA),
    ENTITY_TEMPLATE("java/entity.vm", ItemType.JAVA),
    CLAZZ_MAPPING_TEMPLATE("java/springboot/clazz-mapping.vm", ItemType.JAVA),
    SPRINGBOOT_JPA_REPOSITORY_TEMPLATE("java/springboot/jpa-repository.vm", ItemType.JAVA),
    SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE("java/springboot/jpa-service-basic.vm", ItemType.JAVA),
    SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE("java/springboot/rest-controller-basic.vm", ItemType.JAVA),
    TYPESCRIPT_MODEL_TEMPLATE("typescript/model.vm", ItemType.TYPESCRIPT);

    private String templatePath;

    private ItemType itemType;

    TemplateEnum(String templatePath, ItemType itemType) {
        this.templatePath = templatePath;
        this.itemType = itemType;
    }
}
