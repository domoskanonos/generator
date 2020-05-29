package com.dbr.generator.basic.dto;

import lombok.Data;

@Data
public class ItemMergerDTO {

    private ItemDTO[] objects;

    public ItemMergerDTO(ItemDTO... objects) {
        this.objects = objects;
    }

}
