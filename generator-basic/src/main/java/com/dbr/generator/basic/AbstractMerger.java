package com.dbr.generator.basic;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public abstract class AbstractMerger {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public abstract String create();

    public void writeDown(String fileAbsolutePath) throws IOException {
        log.info("merger, write down content to file: {}", fileAbsolutePath);
        File file = new File(fileAbsolutePath);
        String content = create();
        FileUtils.writeStringToFile(file, content, Charset.defaultCharset());
    }


}
