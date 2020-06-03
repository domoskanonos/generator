package com.dbr.generator.basic.item.merger.java.springboot;

import com.dbr.generator.GeneratorProjectMetaData;
import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.PropertyDTO;
import com.dbr.generator.basic.entity.Property;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.merger.TemplateEnum;
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
        clazzMappingItemDTO = new JavaClass2ItemDTOConverter().convert(GeneratorProjectMetaData.SPRING_BOOT_JAVA_PROJECT_DTO, TemplateEnum.CLAZZ_MAPPING_TEMPLATE, ItemDTO.class);
        clazzMappingItemDTO.setName("com.dbr.generator.PropertyRepository");
        clazzMappingItemDTO.addItemDTO(new JavaClass2ItemDTOConverter().convert(GeneratorProjectMetaData.SPRING_BOOT_JAVA_PROJECT_DTO, TemplateEnum.ENTITY_TEMPLATE, Property.class));
        clazzMappingItemDTO.addItemDTO(new JavaClass2ItemDTOConverter().convert(GeneratorProjectMetaData.SPRING_BOOT_JAVA_PROJECT_DTO, TemplateEnum.DTO_TEMPLATE, PropertyDTO.class));
        ItemTemplateMerger itemTemplateMerger = new ItemTemplateMerger(clazzMappingItemDTO);
        String content = itemTemplateMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}