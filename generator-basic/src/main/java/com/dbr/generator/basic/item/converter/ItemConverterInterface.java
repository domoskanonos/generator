package com.dbr.generator.basic.item.converter;

import com.dbr.generator.basic.item.dto.ItemConverterDTO;
import com.dbr.generator.basic.item.dto.ItemDTO;

import java.util.Collection;
import java.util.List;

public interface ItemConverterInterface {

    ItemDTO convert(ItemConverterDTO source);

    List<ItemDTO> convert(Collection<ItemConverterDTO> source);

}
