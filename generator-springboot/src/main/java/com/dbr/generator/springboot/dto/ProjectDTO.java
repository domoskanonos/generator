package com.dbr.generator.springboot.dto;

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

    private java.lang.Object processDTO;
    private java.util.List itemMergerDTOS;
    private java.util.List downloadUrls;
    private String technicalDescriptor;

}