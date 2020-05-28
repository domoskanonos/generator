package com.dbr.generator.basic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertieDTO {

    private String name;
    private String typeName;
    private String typeSimpleName;
    private Boolean searchable;
    private Boolean idPropertie;
    private Boolean baseType;
    private Boolean enumeration;
    private Integer length;

    public PropertieDTO(String name, String typeSimpleName) {
        this.name = name;
        this.typeSimpleName = typeSimpleName;
    }

    public PropertieDTO(String name, String typeSimpleName, Integer length) {
        this.name = name;
        this.typeSimpleName = typeSimpleName;
        this.length = length;
    }
}
