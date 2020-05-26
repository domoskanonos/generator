package com.dbr.generator.basic.model;

import lombok.*;

import java.lang.reflect.Field;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JavaProperty {

    private Field field;
    private String name;
    private String typeSimpleName;

    public JavaProperty(String name, String typeSimpleName) {
        this.name = name;
        this.typeSimpleName = typeSimpleName;
    }

    public JavaProperty(Field field) {
        this.field = field;
        this.name = field.getName();
        this.typeSimpleName = GeneratorUtil.getSimpleName(field.getType());
    }

    public boolean isSearchable() {
        return GeneratorUtil.isSearchableType(typeSimpleName) && !GeneratorUtil.isIdField(field);
    }

}
