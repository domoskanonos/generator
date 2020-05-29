package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.enumeration.ItemMergerEnum;

public class DTOItemMergerDTO extends ItemMergerDTO {
    public DTOItemMergerDTO(ItemDTO... objects) {
        super(ItemMergerEnum.DTO, objects);
    }
}
