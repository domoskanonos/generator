package com.dbr.generator.basic.enumeration;

import lombok.Getter;

@Getter
public enum LanguageTypeEnum {

    JAVA("java", "src/main/java/"), TYPESCRIPT("ts", "source/"), JSON("json", "source/");

    private String fileEnding;
    private String sourceFolderPath;

    LanguageTypeEnum(String fileEnding, String sourceFolderPath) {
        this.fileEnding = fileEnding;
        this.sourceFolderPath = sourceFolderPath;
    }

}
