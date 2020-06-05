package com.dbr.generator.basic.enumeration;

public enum LanguageType {
    JAVA, TYPESCRIPT;

    public String getSourceFolderPath() {
        switch (this) {
            case JAVA:
                return "src/main/java/";
            case TYPESCRIPT:
                return "source/";
            default:
                return "";
        }
    }
}
