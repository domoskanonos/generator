package com.dbr.generator.basic.merger;

import com.dbr.generator.basic.converter.JavaClass2ModelDTOConverter;
import com.dbr.generator.basic.dto.ObjectDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class DTOMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        DTOMerger dtoMerger = new DTOMerger();
        String content = dtoMerger.create(new JavaClass2ModelDTOConverter().convert(ObjectDTO.class));
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}