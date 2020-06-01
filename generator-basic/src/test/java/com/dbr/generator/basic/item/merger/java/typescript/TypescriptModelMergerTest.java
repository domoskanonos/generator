package com.dbr.generator.basic.item.merger.java.typescript;

import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.ItemMerger;
import com.dbr.generator.basic.item.merger.MergerTemplates;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class TypescriptModelMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        ItemDTO subitemTypescript = new JavaClass2ItemDTOConverter().convert("", MergerTemplates.TYPESCRIPT_MODEL_TEMPLATE, ItemDTO.class);


        ItemMerger itemMerger = new ItemMerger(subitemTypescript);
        String content = itemMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}