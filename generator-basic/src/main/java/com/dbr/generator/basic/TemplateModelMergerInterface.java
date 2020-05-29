package com.dbr.generator.basic;

public interface TemplateModelMergerInterface<T> {
    String create(T model) throws Exception;
}
