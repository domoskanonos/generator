package com.dbr.generator.basic.item.dto.generatoraction;

import com.dbr.generator.basic.item.dto.ItemConverterDTO;
import com.dbr.generator.basic.item.merger.ItemMerger;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemGeneratorActionDTO {

    private String destionationPath;
    private ItemMerger itemMerger;
    private ItemConverterDTO itemConverterDTO;


}
