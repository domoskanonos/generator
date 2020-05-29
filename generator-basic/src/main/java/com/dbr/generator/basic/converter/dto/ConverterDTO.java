package com.dbr.generator.basic.converter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ConverterDTO<PARENT, SOURCE> {
    private PARENT parent;
    private SOURCE source;
}
