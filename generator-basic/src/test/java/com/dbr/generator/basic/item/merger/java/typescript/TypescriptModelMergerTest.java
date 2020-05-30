package com.dbr.generator.basic.item.merger.java.typescript;

import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.dto.TypescriptModelItemMergerDTO;
import com.dbr.generator.basic.item.merger.typescript.TypescriptModelMerger;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class TypescriptModelMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        TypescriptModelMerger typescriptModelMerger = new TypescriptModelMerger(new TypescriptModelItemMergerDTO(new JavaClass2ItemDTOConverter().convert(ItemDTO.class)));
        String content = typescriptModelMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}