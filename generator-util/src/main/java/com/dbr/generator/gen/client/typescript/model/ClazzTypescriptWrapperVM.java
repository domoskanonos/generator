package com.dbr.generator.gen.client.typescript.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClazzTypescriptWrapperVM {

    private String templatePath;
    private String folderName;
    private String filename;
    private ClazzTypescriptWrapper clazzTypescriptWrapper;

    public ClazzTypescriptWrapperVM(String templatePath, String folderName, String filename, ClazzTypescriptWrapper clazzTypescriptWrapper) {
        this.templatePath = templatePath;
        this.folderName = folderName;
        this.filename = filename;
        this.clazzTypescriptWrapper = clazzTypescriptWrapper;
    }
}
