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
public class ItemDTO {

    private Long id;
    private java.lang.Object projectEntity;
    private String name;
    private java.lang.Enum idTypeEnum;
    private java.lang.Object template;
    private java.lang.Object properties;

}