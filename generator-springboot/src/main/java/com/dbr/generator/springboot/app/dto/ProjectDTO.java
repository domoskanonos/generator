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
public class ProjectDTO {

    private Long id;
    private java.util.List itemEntities;
    private java.lang.Object template;
    private String technicalDescriptor;
    private String javaBasePackage;

}