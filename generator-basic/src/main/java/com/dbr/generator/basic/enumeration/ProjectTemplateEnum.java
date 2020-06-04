package com.dbr.generator.basic.enumeration;

import lombok.Getter;

@Getter
public enum ProjectTemplateEnum {
    TYPESCRIPT_NIDOCA_PAGE_SERVICE("project/typescript/app/page-service.vm");

    private String templatePath;

    ProjectTemplateEnum(String templatePath) {
        this.templatePath = templatePath;
    }
}
