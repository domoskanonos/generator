package com.dbr.generator.basic.item.merger.java.springboot;

import com.dbr.generator.basic.BasicTestUtil;
import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.converter.dto.ItemConverterDTO;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.dto.MappingClazzItemMergerDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MappingClazzMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        MappingClazzMerger mappingClazzMerger = new MappingClazzMerger(new MappingClazzItemMergerDTO(new JavaClass2ItemDTOConverter().convert(new ItemConverterDTO(BasicTestUtil.projectDTO, BasicTestUtil.projectDTO.getJavaBasePackage(), ItemDTO.class)), new JavaClass2ItemDTOConverter().convert(new ItemConverterDTO(BasicTestUtil.projectDTO, BasicTestUtil.projectDTO.getJavaBasePackage(), ItemDTO.class))));
        String content = mappingClazzMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}