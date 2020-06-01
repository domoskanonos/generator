package com.dbr.generator.basic.item.merger.java;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.merger.ItemMerger;
import com.dbr.generator.basic.merger.MergerTemplates;
import com.dbr.generator.basic.dto.PropertyDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DTOMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.DTO_TEMPLATE, PropertyDTO.class);
        ItemMerger itemMerger = new ItemMerger(itemDTO);
        String content = itemMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}