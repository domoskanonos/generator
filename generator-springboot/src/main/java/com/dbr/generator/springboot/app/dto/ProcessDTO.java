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
public class ProcessDTO {

    private Long id;
    private java.util.List projectEntities;
    private String processTempPath;
    private String processParentPath;
    private String technicalDescriptor;

}