package com.dbr.generator.gen.common.csv.model;

import com.dbr.util.DataTypes;
import com.dbr.util.StringUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class CSVClazzVM {

    private static final Logger log = LoggerFactory.getLogger(CSVClazzVM.class);

    private String packageName;
    private String clazzSimpleName;
    private String content;
    private String columnSplit;
    private String rowSplit;
    private Boolean withHeaderRow;
    private Integer columnSize;
    private Integer rowSize;
    private List<String> headers = new ArrayList<>();
    private Integer[] lengths;

    private List<CSVJavaProperty> properties = new ArrayList<>();

    public CSVClazzVM(String packageName, String clazzSimpleName, String content, String columnSplit, String rowSplit,
            Boolean withHeaderRow) {
        this.packageName = packageName;
        this.clazzSimpleName = clazzSimpleName;
        this.content = content;
        this.columnSplit = columnSplit;
        this.rowSplit = rowSplit;
        this.withHeaderRow = withHeaderRow;

        List<List<String>> rows = new ArrayList<>();

        List<String> rowsContent = Lists.newArrayList(Splitter.on(rowSplit).split(content));
        log.info("create rows from content...");
        if (withHeaderRow) {
            headers = new ArrayList<>();

            String rowOne = rowsContent.get(0);
            Splitter.on(columnSplit).split(rowOne).forEach(header -> {
                headers.add(header.trim());
            });
            columnSize = headers.size();
            for (int i = 1; i < rowsContent.size(); i++) {
                String row = rowsContent.get(i);
                if (row.length() == 0) {
                    log.info("Row with zero entry, ignored, index: {}", i);
                    continue;
                }
                rows.add(Lists.newArrayList(Splitter.on(columnSplit).split(row)));
                if (i % 100 == 0) {
                    log.info(String.format("read 100 rows, total rows: %d", i));
                }
            }
        } else {
            for (int i = 0; i < rowsContent.size(); i++) {
                String row = rowsContent.get(i);
                if (row.length() == 0) {
                    log.info("Row with zero entry, ignored, index: {}", i);
                    continue;
                }
                List<String> colums = Lists.newArrayList(Splitter.on(columnSplit).split(row));

                if (columnSize == null) {
                    columnSize = colums.size();
                }

                rows.add(colums);
                if (i % 100 == 0) {
                    log.info(String.format("read 100 rows, total rows: %d", i));
                }

            }
            for (int i = 0; i < columnSize; i++) {
                headers.add("column" + i);
            }
        }

        this.rowSize = rows.size();

        log.info("create contentMatrix");
        String[][] contentMatrix = new String[columnSize][rows.size()];
        for (int i = 0; i < rows.size(); i++) {
            List<String> currentRow = rows.get(i);
            for (int j = 0; j < currentRow.size(); j++) {
                String columnContent = currentRow.get(j);
                contentMatrix[j][i] = columnContent;
            }
        }

        log.info("calculate length array...");
        lengths = new Integer[columnSize];
        for (int i = 0; i < rows.size(); i++) {
            if (i % 100 == 0) {
                log.info(String.format("read 100 rows, total rows: %d", i));
            }
            List<String> currentRow = rows.get(i);
            for (int j = 0; j < currentRow.size(); j++) {
                String columnContent = currentRow.get(j);
                String columnType = StringUtil.getType(contentMatrix[j]);
                if (DataTypes.TYPE_STRING.equals(columnType)) {
                    int length = columnContent.length();
                    if (lengths[j] == null || lengths[j].intValue() < length) {
                        lengths[j] = length;
                    }
                }
            }
        }

        log.info("create CSVJavaProperties...");
        for (int i = 0; i < columnSize; i++) {
            properties.add(new CSVJavaProperty(StringUtil.toPropertieName(headers.get(i).toLowerCase()),
                    StringUtil.getType(contentMatrix[i]), lengths[i]));
        }

    }

}