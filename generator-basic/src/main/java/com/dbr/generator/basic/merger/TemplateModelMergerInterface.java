package com.dbr.generator.basic.merger;

public interface TemplateModelMergerInterface<T> {
    String create(T... model) throws Exception;
}
