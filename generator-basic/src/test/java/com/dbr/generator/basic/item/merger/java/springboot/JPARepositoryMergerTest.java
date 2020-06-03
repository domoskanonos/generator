package com.dbr.generator.basic.item.merger.java.springboot;

import com.dbr.generator.basic.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.entity.Property;
import com.dbr.generator.basic.merger.ItemTemplateMerger;
import com.dbr.generator.basic.merger.TemplateEnum;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JPARepositoryMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        ItemDTO repositoryItemDTO;
        ItemDTO entityItemDTO;
        repositoryItemDTO = new JavaClass2ItemDTOConverter().convert(TemplateEnum.DTO_TEMPLATE, "", Property.class);
        repositoryItemDTO.setJavaClazzName("com.dbr.generator.springboot.repository.PropertyJPARepository");
        entityItemDTO = new JavaClass2ItemDTOConverter().convert(TemplateEnum.ENTITY_TEMPLATE, "", Property.class);
        repositoryItemDTO.addItemDTO(entityItemDTO);
        ItemTemplateMerger itemTemplateMerger = new ItemTemplateMerger(repositoryItemDTO);
        String content = itemTemplateMerger.create();
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }


}