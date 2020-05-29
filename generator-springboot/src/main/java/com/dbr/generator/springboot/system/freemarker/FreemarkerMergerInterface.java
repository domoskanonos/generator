package com.dbr.generator.springboot.system.freemarker;

import freemarker.template.TemplateException;

import java.io.IOException;

public interface FreemarkerMergerInterface<T> {

    String create(T model) throws IOException, TemplateException;

}
