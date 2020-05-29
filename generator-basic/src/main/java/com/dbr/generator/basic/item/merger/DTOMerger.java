package com.dbr.generator.basic.item.merger;

import com.dbr.generator.basic.item.dto.ItemMergerDTO;

public class DTOMerger extends ItemMerger {
    public DTOMerger(ItemMergerDTO dto) {
        super(dto,"java/dto.vm");
    }
}
