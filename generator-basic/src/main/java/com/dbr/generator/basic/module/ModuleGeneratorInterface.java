package com.dbr.generator.basic.module;

import java.io.IOException;

public interface ModuleGeneratorInterface<T> {
    void execute(T model) throws IOException, InterruptedException;
    void validate(T model);
}
