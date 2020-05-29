package com.dbr.generator.basic.generatoraction;

import com.dbr.generator.basic.generatoraction.dto.GeneratorActionDTO;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;

public class GeneratorActionWorker {

    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public static <T> void generate(GeneratorActionDTO<T> dto) throws Exception {
        String content = dto.getTemplateModelMergerInterface().create(dto.getModel());
        FileUtils.writeStringToFile(new File(dto.getDestionationPath()), content, Charset.defaultCharset());
    }

}
