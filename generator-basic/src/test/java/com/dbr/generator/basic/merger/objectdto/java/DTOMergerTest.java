package com.dbr.generator.basic.merger.objectdto.java;

import com.dbr.generator.basic.BaseTestUtil;
import com.dbr.generator.basic.dto.ConverterDTO;
import com.dbr.generator.basic.converter.JavaClass2ModelDTOConverter;
import com.dbr.generator.basic.dto.ItemMergerDTO;
import com.dbr.generator.basic.dto.ItemDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class DTOMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        DTOMerger dtoMerger = new DTOMerger();
        ItemDTO itemDTO = new JavaClass2ModelDTOConverter().convert(new ConverterDTO<>(BaseTestUtil.projectDTO, ItemDTO.class));
        String content = dtoMerger.create(new ItemMergerDTO(itemDTO));
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}