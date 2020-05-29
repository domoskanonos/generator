package com.dbr.generator.basic.property.dto;

import com.dbr.generator.basic.item.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

@AllArgsConstructor
@Data
public class PropertyConverterDTO {
    private ItemDTO parent;
    private Field source;
}
