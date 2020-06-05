package com.dbr.generator.gen.client.typescript.model;

import com.dbr.generator.gen.client.typescript.compound.model.TypescriptClientCVM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClazzTypescriptWrappersVM {

    private TypescriptClientCVM typescriptClientCVM;
    private String templatePath;
    private String folderName;
    private String filename;
    private Boolean d = true;
    private ClazzTypescriptWrapper[] clazzTypescriptWrappers;

    public ClazzTypescriptWrappersVM(TypescriptClientCVM typescriptClientCVM, String templatePath, String folderName,
            String filename, ClazzTypescriptWrapper[] clazzTypescriptWrappers) {
        this.typescriptClientCVM = typescriptClientCVM;
        this.templatePath = templatePath;
        this.folderName = folderName;
        this.typescriptRemoteRepositoryFilename = filename;
        this.clazzTypescriptWrappers = clazzTypescriptWrappers;
    }

}
