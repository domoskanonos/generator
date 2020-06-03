package com.dbr.generator.basic.item.merger.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DTOMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
/**
    @Test
    public void create() {
        ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert("", ItemTemplates.DTO_TEMPLATE, PropertyDTO.class);
        ItemTemplateMerger itemTemplateMerger = new ItemTemplateMerger(itemDTO);
        String content = itemTemplateMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }*/
}