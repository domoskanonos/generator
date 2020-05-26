package com.dbr.generator.gen.client.typescript.model;

import com.dbr.generator.util.generator.GeneratorUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TypescriptBaseVM {

    private Class modelClazz;
    private String modelName;
    private String modelPath;
    private List<TypescriptProperty> properties;

    public TypescriptBaseVM(Class modelClazz, String modelName) {
        this.modelClazz = modelClazz;
        this.modelName = modelName;
        this.modelPath = String.format("../model/%s-model", this.modelName.toLowerCase());
        this.properties = GeneratorUtil.getTypescriptProperties(modelClazz.getDeclaredFields());
    }

}
