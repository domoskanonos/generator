package com.dbr.generator.basic.merger;

import lombok.Getter;

@Getter
public enum TemplateEnum {

    DTO_TEMPLATE("java/dto.vm"),
    ENTITY_TEMPLATE("java/entity.vm"),
    CLAZZ_MAPPING_TEMPLATE("java/springboot/clazz-mapping.vm"),
    SPRINGBOOT_JPA_REPOSITORY_TEMPLATE("java/springboot/jpa-repository.vm"),
    SPRINGBOOT_JPA_SERVICE_BASIC_TEMPLATE("java/springboot/jpa-service-basic.vm"),
    SPRINGBOOT_REST_CONTROLLER_BASIC_TEMPLATE("java/springboot/rest-controller-basic.vm"),
    TYPESCRIPT_MODEL_TEMPLATE("typescript/model.vm");

    private String templatePath;

    TemplateEnum(String templatePath) {
        this.templatePath = templatePath;

    }
}
