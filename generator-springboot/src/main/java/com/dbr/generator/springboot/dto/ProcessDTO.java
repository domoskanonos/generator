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
public class ProcessDTO {

    private String processTempPath;
    private String processParentPath;
    private String technicalDescriptor;
    private java.util.List projectDTOS;

}