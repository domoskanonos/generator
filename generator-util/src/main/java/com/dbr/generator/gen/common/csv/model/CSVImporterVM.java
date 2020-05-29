package com.dbr.generator.gen.common.csv.model;

import com.dbr.generator.basic.property.dto.PropertyDTO;
import lombok.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CSVImporterVM {

    private String packageName;
    private String clazzSimpleName;
    private String modelClazzPackageName;
    private String modelClazzSimpleName;
    private String columnSplit;
    private String rowSplit;
    private Boolean withHeaderRow;
    private File file;
    private String content;
    private int rowCount;

    private List<PropertyDTO> properties = new ArrayList<>();

}
