package com.dbr.generator.springboot.app.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProcessDTO {

    private Long id;
    private String processTempPath;
    private String processParentPath;
    private String technicalDescriptor;

    private List<ProjectDTO> projects = new ArrayList<>();


}