package com.dbr.generator.basic.item.merger.java.springboot;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.merger.ItemTemplateTemplateMerger;
import com.dbr.generator.basic.merger.ItemTemplates;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MappingClazzMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        ItemDTO clazzMappingItemDTO;
        ItemDTO subitemDTO1;
        clazzMappingItemDTO = new JavaClass2ItemDTOConverter().convert("", ItemTemplates.CLAZZ_MAPPING_TEMPLATE, ItemDTO.class);
        clazzMappingItemDTO.setJavaClazzName("com.dbr.generator.PropertyRepository");
        subitemDTO1 = new JavaClass2ItemDTOConverter().convert("", ItemTemplates.DTO_TEMPLATE, PropertyDTO.class);
        clazzMappingItemDTO.addItemDTO(subitemDTO1);
        ItemTemplateTemplateMerger itemTemplateMerger = new ItemTemplateTemplateMerger(clazzMappingItemDTO);
        String content = itemTemplateMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}