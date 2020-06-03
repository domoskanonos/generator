package com.dbr.generator.basic.item.merger.java;

import com.dbr.generator.GeneratorProjectMetaData;
import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.merger.TemplateEnum;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DTOMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        ItemDTO itemDTO = new JavaClass2ItemDTOConverter().convert(GeneratorProjectMetaData.SPRING_BOOT_JAVA_PROJECT_DTO, TemplateEnum.DTO_TEMPLATE, PropertyDTO.class);
        ItemTemplateMerger itemTemplateMerger = new ItemTemplateMerger(itemDTO);
        String content = itemTemplateMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}