package com.dbr.generator.gen.server.db.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Column {

    private String name;
    private String type;
    private Integer dataType;
    private Integer size;
    private Boolean primaryKey = false;
    private Boolean nullable = false;
    private Integer decimalDigits;
    private String remarks;

    public String getJavaType() {
        switch (dataType) {
        case Types.CHAR:
        case Types.VARCHAR:
        case Types.NVARCHAR:
        case Types.LONGVARCHAR:
        case Types.LONGNVARCHAR:
        case Types.VARBINARY:
        case Types.CLOB:
            return String.class.getSimpleName();
        case Types.INTEGER:
            return Integer.class.getSimpleName();
        case Types.NUMERIC:
        case Types.DECIMAL:
            return decimalDigits != null && decimalDigits > 0 ? BigDecimal.class.getSimpleName()
                    : Long.class.getSimpleName();
        case Types.TIMESTAMP:
        case Types.OTHER:
            return Date.class.getName();
        case Types.FLOAT:
            return Float.class.getSimpleName();
        }

        throw new RuntimeException(name + " -> unknown datatype: " + dataType);

    }

}
