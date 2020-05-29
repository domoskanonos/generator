package com.dbr.generator.basic.merger.objectdto.java;

import com.dbr.generator.basic.BaseTestUtil;
import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.dto.ItemConverterDTO;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.dto.ItemMergerDTO;
import com.dbr.generator.basic.item.merger.DTOMerger;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DTOMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        DTOMerger dtoMerger = new DTOMerger();
        ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert(new ItemConverterDTO(BaseTestUtil.projectDTO, ItemDTO.class));
        String content = dtoMerger.create(new ItemMergerDTO(itemDTO));
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}