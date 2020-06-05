package com.dbr.generator.springboot.app.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class PropertyDTO {

    private Long id;
    private String name;
    private java.lang.Enum propertyType;
    private Boolean idProperty;
    private Boolean searchable;
    private Boolean nullable;
    private Boolean useJPAIdClazz;
    private Integer length;

}