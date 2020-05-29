package com.dbr.generator.basic.item.merger;

import com.dbr.generator.basic.item.merger.dto.ItemMergerDTO;
import com.dbr.generator.basic.item.merger.java.springboot.MappingClazzMerger;
import com.dbr.generator.basic.item.merger.typescript.TypescriptModelMerger;

public class ItemMergerFactory {

    public static ItemMerger createMerger(ItemMergerDTO itemMergerDTO) {
        switch (itemMergerDTO.getItemMergerEnum()) {
            case DTO:
                return new DTOMerger(itemMergerDTO);
            case ENTITY:
                return new EntityMerger(itemMergerDTO);
            case MAPPING_CLAZZ:
                return new MappingClazzMerger(itemMergerDTO);
            case TYPESCRIPT_MODEL:
                return new TypescriptModelMerger(itemMergerDTO);
        }
        return null;
    }
}
