package com.dbr.generator.basic.merger;

public interface TemplateDTOMergerInterface<T> {
    String create(T model) throws Exception;
}
