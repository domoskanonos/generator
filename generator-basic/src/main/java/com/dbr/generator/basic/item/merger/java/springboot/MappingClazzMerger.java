package com.dbr.generator.basic.item.merger.java.springboot;

import com.dbr.generator.basic.item.dto.ItemMergerDTO;
import com.dbr.generator.basic.item.merger.ItemMerger;

public class MappingClazzMerger extends ItemMerger {

    public MappingClazzMerger(ItemMergerDTO dto) {
        super(dto, "java/springboot/clazz-mapping.vm");
    }

}
