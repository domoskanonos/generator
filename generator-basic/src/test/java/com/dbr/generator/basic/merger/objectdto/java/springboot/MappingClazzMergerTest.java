package com.dbr.generator.basic.merger.objectdto.java.springboot;

import com.dbr.generator.basic.BaseTestUtil;
import com.dbr.generator.basic.converter.JavaClass2ModelDTOConverter;
import com.dbr.generator.basic.dto.ConverterDTO;
import com.dbr.generator.basic.dto.ItemMergerDTO;
import com.dbr.generator.basic.dto.ItemDTO;
import com.dbr.generator.basic.dto.ProjectDTO;
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
        ItemDTO itemDTO1 = new JavaClass2ModelDTOConverter().convert(new ConverterDTO<>(BaseTestUtil.projectDTO, ProjectDTO.class));
        ItemDTO itemDTO2 = new JavaClass2ModelDTOConverter().convert(new ConverterDTO<>(BaseTestUtil.projectDTO, ItemDTO.class));
        String content = mappingClazzMerger.create(new ItemMergerDTO(itemDTO1, itemDTO2));
        log.info(content);
        assertNotNull(content);
        //assertFalse(content.contains("${"));
    }
}