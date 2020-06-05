package com.dbr.generator.basic.enumeration;

import lombok.Getter;

@Getter
public enum LanguageType {

    JAVA("java", "src/main/java/"), TYPESCRIPT("ts", "source/");

    private String fileEnding;
    private String sourceFolderPath;

    LanguageType(String fileEnding, String sourceFolderPath) {
        this.fileEnding = fileEnding;
        this.sourceFolderPath = sourceFolderPath;
    }

}
