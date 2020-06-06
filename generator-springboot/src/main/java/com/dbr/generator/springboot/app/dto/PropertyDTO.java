package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.TypeEnum;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class PropertyDTO {

    private Long id;
    private String name;
    private TypeEnum propertyType;
    private Boolean idProperty;
    private Boolean searchable;
    private Boolean nullable;
    private Boolean useJPAIdClazz;
    private Integer length;

}