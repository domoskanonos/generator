package com.dbr.generator.basic.project;

import java.io.IOException;

public interface ProjectGeneratorInterface<T> {
    void execute(T model) throws IOException, InterruptedException;
    void validate(T model);
}
