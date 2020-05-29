package com.dbr.generator.basic.converter;

import com.dbr.generator.basic.converter.dto.ConverterDTO;

import java.util.Collection;
import java.util.List;

public interface ConverterInterface<PARENT, DESTINATION, SOURCE> {

    DESTINATION convert(ConverterDTO<PARENT, SOURCE> converterDTO);

    List<DESTINATION> convert(Collection<ConverterDTO<PARENT, SOURCE>> source);

}
