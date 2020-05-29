package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.enumeration.ItemMergerEnum;

public class EntityItemMergerDTO extends ItemMergerDTO {
    public EntityItemMergerDTO(ItemDTO... objects) {
        super(ItemMergerEnum.ENTITY, objects);
    }
}
