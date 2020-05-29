package com.dbr.generator.gen.client.typescript.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class Clazz2TypescriptModelVM extends TypescriptBaseVM {

    private String filename;

    public Clazz2TypescriptModelVM(Class<?> modelClazz, String modelName) {
        super(modelClazz, modelName);
        this.filename = String.format("%s-model[0].ts", this.getModelName().toLowerCase());
    }

}
