package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;

public class MappingClazzItemMergerDTO extends ItemMergerDTO {
    public MappingClazzItemMergerDTO(ItemDTO... objects) {
        super(objects[0].getJavaFilePathSuffix(), objects);
    }
}
