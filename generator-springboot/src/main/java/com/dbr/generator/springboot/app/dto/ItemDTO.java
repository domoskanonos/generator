package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.LanguageType;
import com.dbr.generator.basic.enumeration.TypeEnum;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ItemDTO {

    private Long id;
    private String name;
    private TypeEnum idTypeEnum;
    private LanguageType languageType;
    private String namespace;
    private java.lang.Object template;
    private java.lang.Object properties;

}