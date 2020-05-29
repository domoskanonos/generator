package com.dbr.generator.basic.item.merger.typescript;

import com.dbr.generator.basic.item.dto.ItemMergerDTO;
import com.dbr.generator.basic.item.merger.ItemMerger;

public class TypescriptModelMerger extends ItemMerger {
    public TypescriptModelMerger(ItemMergerDTO model) {
        super(model,"typescript/model.vm");
    }
}

