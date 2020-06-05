package com.dbr.generator.gen.client.typescript.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class RemoteServiceVM extends TypescriptBaseVM {

    private Class clazz;
    private String remoteServiceName;
    private String filename;

    public RemoteServiceVM(Class<?> modelClazz, String modelName) {
        super(modelClazz, modelName);
        this.typescriptRemoteRepositoryFilename = String.format("%s-remote-service.ts", this.getModelName().toLowerCase());
        this.remoteServiceName = String.format("%sRemoteService", this.getModelName());
    }
}
