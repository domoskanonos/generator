package com.dbr.generator.basic.merger.objectdto.java.springboot;

import com.dbr.generator.basic.BaseTestUtil;
import com.dbr.generator.basic.item.converter.JavaClass2ItemDTOConverter;
import com.dbr.generator.basic.item.dto.ItemConverterDTO;
import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.dto.ItemMergerDTO;
import com.dbr.generator.basic.item.merger.java.springboot.MappingClazzMerger;
import com.dbr.generator.basic.project.dto.ProjectDTO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MappingClazzMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        MappingClazzMerger mappingClazzMerger = new MappingClazzMerger();
        ItemDTO itemDTO1 = new JavaClass2ItemDTOConverter().convert(new ItemConverterDTO(BaseTestUtil.projectDTO, ProjectDTO.class));
        ItemDTO itemDTO2 = new JavaClass2ItemDTOConverter().convert(new ItemConverterDTO(BaseTestUtil.projectDTO, ItemDTO.class));
        String content = mappingClazzMerger.create(new ItemMergerDTO(itemDTO1, itemDTO2));
        log.info(content);
        assertNotNull(content);
        assertFalse(content.contains("${"));
    }
}