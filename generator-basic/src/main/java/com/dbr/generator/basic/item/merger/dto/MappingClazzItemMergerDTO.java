package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.enumeration.ItemMergerEnum;

public class MappingClazzItemMergerDTO extends ItemMergerDTO {
    public MappingClazzItemMergerDTO(ItemDTO... objects) {
        super(objects[0].getJavaFilePathSuffix(), ItemMergerEnum.MAPPING_CLAZZ, objects);
    }
}
