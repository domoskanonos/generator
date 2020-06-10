package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.PropertyTypeEnum;
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
    private PropertyTypeEnum propertyType;
    private Boolean idProperty;
    private Boolean mainProperty;
    private Boolean nullable;
    private Boolean useJPAIdClazz;
    private Integer length;
    private Boolean deactivated;
    private String propertyTypeName;

}