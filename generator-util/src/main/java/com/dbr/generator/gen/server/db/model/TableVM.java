package com.dbr.generator.gen.server.db.model;

import com.dbr.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TableVM {

    private String packageName;
    private String tableName;
    private Database database;

    public String getJavaName() {
        return String.valueOf(tableName.charAt(0)).toUpperCase()
                + StringUtil.underscoreToUpperLetter(tableName.substring(1).toLowerCase());
    }

}
