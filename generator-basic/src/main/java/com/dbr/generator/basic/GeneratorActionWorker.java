package com.dbr.generator.basic;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;

public class GeneratorActionWorker {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public static <T> void generate(String destionationPath, TemplateModelMergerInterface<T> mergerInterface, T model) throws Exception {
        String content = mergerInterface.create(model);
        FileUtils.writeStringToFile(new File(destionationPath), content, Charset.defaultCharset());
    }

}
