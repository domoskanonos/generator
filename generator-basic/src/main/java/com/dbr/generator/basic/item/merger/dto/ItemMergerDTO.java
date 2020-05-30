package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;
import lombok.Data;

@Data
public class ItemMergerDTO {

    private String path;
    private ItemDTO[] objects;

    public ItemMergerDTO(String path, ItemDTO... objects) {
        this.path = path;
        this.objects = objects;
    }

    public String getJavaMappingClazzSimpleName() {
        if (this.objects.length < 2) {
            throw new RuntimeException("not enough objects for mapping class, minimum is 2 items.");
        }
        return new StringBuilder().append(this.objects[0].getJavaClazzSimpleName()).append(this.objects[1].getJavaClazzSimpleName()).append("Mapping").toString();
    }

    public String getJavaMappingClazzPackageName() {
        if (this.objects.length < 1) {
            throw new RuntimeException("not enough objects for mapping class, minimum is 1 item.");
        }
        return this.objects[0].getJavaPackageName();
    }

}
