package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.converter.JavaClass2ModelDTOConverter;
import com.dbr.generator.basic.dto.ObjectDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TypescriptModelMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        TypescriptModelMerger typescriptModelMerger = new TypescriptModelMerger();
        String content = typescriptModelMerger.create(new JavaClass2ModelDTOConverter().convert(ObjectDTO.class));
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}