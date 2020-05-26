package com.dbr.generator.gen.common.csv.compound.model;

import com.dbr.util.CSVUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.File;
import java.io.IOException;

@Data
@ToString
@AllArgsConstructor
public class CSV2JavaCVM {

    public static int FILE_IMPORT_MAX_ROW_COUNT = 250;

    private String content;
    private String columnSplit;
    private String rowSplit;
    private boolean withHeaderRow;
    private Integer idColumnIndex;
    private String clazzSimpleBaseName;
    private String basePackageName;
    private File file;

    private boolean generateModel = true;
    private boolean generateJPAEntity = true;
    private boolean generateCSVImporter = true;
    private boolean generateCSVImporterTest = true;

    public CSV2JavaCVM(File file, String columnSplit, String rowSplit, boolean withHeaderRow, Integer idColumnIndex,
            String clazzSimpleBaseName, String basePackageName, Integer maxRowCount) throws IOException {
        this.file = file;
        this.content = CSVUtil.readContent(file, maxRowCount == null ? FILE_IMPORT_MAX_ROW_COUNT : maxRowCount,
                rowSplit);
        this.columnSplit = columnSplit;
        this.rowSplit = rowSplit;
        this.withHeaderRow = withHeaderRow;
        this.idColumnIndex = idColumnIndex;
        this.clazzSimpleBaseName = clazzSimpleBaseName;
        this.basePackageName = basePackageName;
    }

}
