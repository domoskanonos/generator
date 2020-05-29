package com.dbr.generator.gen.server.springboot.csv.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class SpringBootCSVServiceVM {

    String clazzSimpleName;
    String packageName;
    String jpaClazzSimpleName;
    String csvImporterPackageName;
    String csvImporterClazzSimpleName;
    String dtoPackageName;
    String dtoClazzSimpleName;
    String jpaRepositoryPackageName;
    String jpaRepositoryClazzSimpleName;
    String mappingPackageName;
    String mappingClazzSimpleName;

}
