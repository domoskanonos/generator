package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;

public class DTOItemMergerDTO extends ItemMergerDTO {
    public DTOItemMergerDTO(ItemDTO... objects) {
        super(objects[0].getJavaFilePathSuffix(), objects);
    }
}
