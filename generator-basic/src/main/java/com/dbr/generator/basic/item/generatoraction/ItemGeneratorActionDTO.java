package com.dbr.generator.basic.item.generatoraction;

import com.dbr.generator.basic.generatoraction.dto.GeneratorActionDTO;
import com.dbr.generator.basic.item.dto.ItemMergerDTO;
import com.dbr.generator.basic.item.merger.DTOMerger;

public class ItemGeneratorActionDTO extends GeneratorActionDTO<ItemMergerDTO> {
    public ItemGeneratorActionDTO(String destionationPath, ItemMergerDTO model) {
        super(destionationPath, new DTOMerger(), model);
    }
}
