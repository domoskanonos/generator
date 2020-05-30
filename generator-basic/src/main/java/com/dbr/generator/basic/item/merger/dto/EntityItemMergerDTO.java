package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;

public class EntityItemMergerDTO extends ItemMergerDTO {
    public EntityItemMergerDTO(ItemDTO... objects) {
        super(objects[0].getJavaFilePathSuffix(), objects);
    }
}
