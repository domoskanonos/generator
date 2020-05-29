package com.dbr.generator.basic.merger.objectdto.java.springboot;

import com.dbr.generator.basic.BaseTestUtil;
import com.dbr.generator.basic.converter.JavaClass2ModelDTOConverter;
import com.dbr.generator.basic.dto.ConverterDTO;
import com.dbr.generator.basic.dto.ObjectDTO;
import com.dbr.generator.basic.dto.ProjectDTO;
import com.dbr.generator.basic.merger.objectdto.java.DTOMerger;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ClazzMappingMergerMergerTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void create() {
        ClazzMappingMerger clazzMappingMerger = new ClazzMappingMerger();
        ObjectDTO objectDTO1 = new JavaClass2ModelDTOConverter().convert(new ConverterDTO<>(BaseTestUtil.projectDTO, ProjectDTO.class));
        ObjectDTO objectDTO2 = new JavaClass2ModelDTOConverter().convert(new ConverterDTO<>(BaseTestUtil.projectDTO, ObjectDTO.class));
        String content = clazzMappingMerger.create(objectDTO1,objectDTO2);
        log.info(content);
        assertNotNull(content);
        //assertFalse(content.contains("${"));
    }
}