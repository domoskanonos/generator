package com.dbr.generator.basic.item.merger;

import com.dbr.generator.basic.item.merger.dto.*;
import com.dbr.generator.basic.item.merger.java.springboot.MappingClazzMerger;
import com.dbr.generator.basic.item.merger.typescript.TypescriptModelMerger;

public class ItemMergerFactory {

    public static ItemMerger createMerger(ItemMergerDTO itemMergerDTO) {
        if (itemMergerDTO instanceof DTOItemMergerDTO) {
            return new DTOMerger(itemMergerDTO);
        }
        if (itemMergerDTO instanceof EntityItemMergerDTO) {
            return new EntityMerger(itemMergerDTO);
        }
        if (itemMergerDTO instanceof MappingClazzItemMergerDTO) {
            return new MappingClazzMerger(itemMergerDTO);
        }
        if (itemMergerDTO instanceof TypescriptModelItemMergerDTO) {
            return new TypescriptModelMerger(itemMergerDTO);
        }

        return null;
    }
}
