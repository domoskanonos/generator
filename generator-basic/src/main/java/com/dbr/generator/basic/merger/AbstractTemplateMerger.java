package com.dbr.generator.basic.merger;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class AbstractTemplateMerger {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public abstract String create();

    public void writeDown(File file) throws IOException {
        log.info("merger, write down content to file: {}", file.getAbsolutePath());
        String content = create();
        FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
    }


}
