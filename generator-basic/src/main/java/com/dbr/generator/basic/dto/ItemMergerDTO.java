package com.dbr.generator.basic.dto;

import lombok.Data;

@Data
public class ItemMergerDTO {

    private ItemDTO[] objects;

    public ItemMergerDTO(ItemDTO... objects) {
        this.objects = objects;
    }

    public String getJavaMappingClazzSimpleName() {
        if (this.objects.length < 2) {
            throw new RuntimeException("not enough objects for mapping class, minimum is 2 items.");
        }
        return new StringBuilder().append(this.objects[0].getJavaClazzSimpleName()).append(this.objects[1].getJavaClazzSimpleName()).append("Mapping").toString();
    }

}
