package com.dbr.generator.basic.item.merger.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;
import com.dbr.generator.basic.item.merger.enumeration.ItemMergerEnum;
import lombok.Data;

@Data
public class ItemMergerDTO {

    private String path;
    private ItemMergerEnum itemMergerEnum;
    private ItemDTO[] objects;

    public ItemMergerDTO(String path, ItemMergerEnum itemMergerEnum, ItemDTO... objects) {
        this.path = new StringBuilder().append(objects[0].getProjectDTO().getProjectFolder().getAbsolutePath()).append("/").append(path).toString();
        this.itemMergerEnum = itemMergerEnum;
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
