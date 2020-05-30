package com.dbr.generator.basic.project;

public interface ProjectGeneratorInterface<T> {
    void execute(T model) throws Exception;
    void validate(T model);
}
