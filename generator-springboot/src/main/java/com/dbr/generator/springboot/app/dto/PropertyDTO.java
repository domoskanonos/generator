package com.dbr.generator.springboot.app.dto;

import com.dbr.generator.basic.enumeration.PropertyType;
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
    private PropertyType propertyType;
    private Boolean idProperty;
    private Boolean mainProperty;
    private Boolean nullable;
    private Boolean useJPAIdClazz;
    private Integer length;
    private Boolean deactivated;
    private String propertyTypeName;

}