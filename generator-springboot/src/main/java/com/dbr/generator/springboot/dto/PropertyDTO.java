package com.dbr.generator.springboot.dto;

import com.dbr.generator.basic.property.enumeration.PropertyTypeEnum;
import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PropertyDTO {

    private String name;
    private PropertyTypeEnum propertyType;
    private Boolean idProperty;
    private Boolean searchable;
    private Boolean nullable;
    private Boolean useJPAIdClazz;
    private Integer length;

}