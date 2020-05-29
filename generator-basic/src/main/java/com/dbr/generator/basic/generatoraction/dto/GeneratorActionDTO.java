package com.dbr.generator.basic.generatoraction.dto;

import com.dbr.generator.basic.merger.TemplateModelMergerInterface;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GeneratorActionDTO<T> {

    private String destionationPath;
    private TemplateModelMergerInterface<T> templateModelMergerInterface;
    private T model;

}
