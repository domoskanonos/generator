package com.dbr.generator.basic.generator.project;

public interface ProjectGeneratorInterface<T> {
    void execute(T model) throws Exception;
    void validate(T model);
}
