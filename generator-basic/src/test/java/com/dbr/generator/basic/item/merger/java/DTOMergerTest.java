package com.dbr.generator.basic.item.merger.java;

import com.dbr.generator.basic.BaseTestUtil;
import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.converter.dto.ItemConverterDTO;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.DTOMerger;
import com.dbr.generator.basic.item.merger.dto.DTOItemMergerDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DTOMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        DTOMerger dtoMerger = new DTOMerger(new DTOItemMergerDTO(new JavaClass2ItemDTOConverter().convert(new ItemConverterDTO(BaseTestUtil.projectDTO,BaseTestUtil.projectDTO.getJavaBasePackage(), ItemDTO.class))));
        String content = dtoMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}