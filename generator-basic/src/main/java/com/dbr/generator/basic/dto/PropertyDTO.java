package com.dbr.generator.basic.dto;

import com.dbr.generator.basic.enumeration.PropertyTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyDTO {

    private String name;
    private PropertyTypeEnum propertyType;
    private Boolean idProperty;
    private Boolean searchable;
    private Integer length;

    public PropertyDTO(String name, String javaTypeSimpleName) {
        this.name = name;
    }

    public PropertyDTO(String name, String javaTypeSimpleName, Integer length) {
        this.name = name;
        this.length = length;
    }
}
