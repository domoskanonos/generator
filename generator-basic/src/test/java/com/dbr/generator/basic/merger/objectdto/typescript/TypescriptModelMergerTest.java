package com.dbr.generator.basic.merger.objectdto.typescript;

import com.dbr.generator.basic.BaseTestUtil;
import com.dbr.generator.basic.dto.ConverterDTO;
import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemMergerDTO;
import com.dbr.generator.basic.dto.ItemDTO;
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
        ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert(new ConverterDTO<>(BaseTestUtil.projectDTO, ItemDTO.class));
        String content = typescriptModelMerger.create(new ItemMergerDTO(itemDTO));
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}