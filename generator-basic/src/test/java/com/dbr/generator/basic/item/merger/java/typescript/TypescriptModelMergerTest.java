package com.dbr.generator.basic.item.merger.java.typescript;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.merger.ItemTemplateTemplateMerger;
import com.dbr.generator.basic.merger.ItemTemplates;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class TypescriptModelMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        ItemDTO subitemTypescript = new JavaClass2ItemDTOConverter().convert("", ItemTemplates.TYPESCRIPT_MODEL_TEMPLATE, ItemDTO.class);


        ItemTemplateTemplateMerger itemTemplateMerger = new ItemTemplateTemplateMerger(subitemTypescript);
        String content = itemTemplateMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}