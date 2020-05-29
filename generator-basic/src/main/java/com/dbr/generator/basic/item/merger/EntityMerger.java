package com.dbr.generator.basic.item.merger;

import com.dbr.generator.basic.item.dto.ItemMergerDTO;

public class EntityMerger extends ItemMerger {
    public EntityMerger(ItemMergerDTO dto) {
        super(dto, "java/entity.vm");
    }
}

