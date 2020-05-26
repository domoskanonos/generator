package com.dbr.generator.basic.model;

import lombok.Data;

@Data
public class CSVJavaProperty extends JavaProperty {
    private Integer length;

    public CSVJavaProperty(String name, String typeSimpleName, Integer length) {
        super(name, typeSimpleName);
        this.length = length;
    }
}
