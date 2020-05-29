package com.dbr.generator.basic.property.converter;


import com.dbr.generator.basic.property.dto.PropertyConverterDTO;
import com.dbr.generator.basic.property.dto.PropertyDTO;

import java.util.Collection;
import java.util.List;

public interface PropertyConverterInterface {

    PropertyDTO convert(PropertyConverterDTO source);

    List<PropertyDTO> convert(Collection<PropertyConverterDTO> source);

}
