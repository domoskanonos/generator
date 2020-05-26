package com.dbr.generator.gen.client.typescript.model;

import com.dbr.generator.basic.util.GeneratorUtil;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RemoteRepositoryVM extends TypescriptBaseVM {

    private String remoteRepositoryName;
    private String filename;
    private String idClazz;

    public RemoteRepositoryVM(Class modelClazz, String modelName) {
        super(modelClazz, modelName);
        this.remoteRepositoryName = String.format("%sRemoteRepository", this.getModelName());
        this.filename = String.format("%s-remote-repository.ts", this.getModelName().toLowerCase());
        String idClazzSimpleName = GeneratorUtil.getIDClazzSimpleName(modelClazz);
        if (idClazzSimpleName != null) {
            this.idClazz = GeneratorUtil.toTypescriptType(idClazzSimpleName);
        } else {
            this.idClazz = "number";
        }
    }
}
