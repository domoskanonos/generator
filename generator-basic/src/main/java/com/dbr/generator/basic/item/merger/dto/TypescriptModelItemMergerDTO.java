package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.enumeration.ItemMergerEnum;

public class TypescriptModelItemMergerDTO extends ItemMergerDTO {
    public TypescriptModelItemMergerDTO(ItemDTO... objects) {
        super(objects[0].getJavaFilePathSuffix(), ItemMergerEnum.TYPESCRIPT_MODEL, objects);
    }
}
