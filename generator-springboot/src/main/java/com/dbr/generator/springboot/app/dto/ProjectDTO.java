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
public class ProjectDTO {

    private Long id;
    private String technicalDescriptor;
    private String javaBasePackage;

    private List<ItemDTO> projects = new ArrayList<>();

}