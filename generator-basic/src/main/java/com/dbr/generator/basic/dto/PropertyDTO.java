package com.dbr.generator.basic.dto;

import com.dbr.generator.basic.enumeration.PropertyTypeEnum;
import com.dbr.util.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyDTO {

    private String name;
    private PropertyTypeEnum propertyType;
    private boolean idProperty;
    private boolean searchable;
    private boolean nullable;
    private boolean useJPAIdClazz;
    private Integer length;

    public PropertyDTO(String name) {
        this.name = name;
    }

    public PropertyDTO(String name, Integer length) {
        this.name = name;
        this.length = length;
    }

    public String getJpaPropertyAnnotations() {
        String id = "";
        if (idProperty) {
            id = "\n    @Id";
            if (!useJPAIdClazz) {
                id += "\n    @GeneratedValue(strategy = GenerationType.IDENTITY)";
            }
        }
        String columnSize = length != null && length > 0 ? ", length=" + length : "";
        String nullableAnnotation = nullable && !idProperty ? "" : ", nullable = false";
        String unique = idProperty ? ", unique = true" : "";
        String column = "\n    @Column(name = \"" + getColumnName() + "\"" + unique + columnSize + nullableAnnotation
                + " )";
        String type = getPropertyType().getJavaTypeSimpleName();
        String oneToMany = getPropertyType().isBaseJavaType() || (!type.contains("List") && !type.contains("Set"))
                ? "" : "\n    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )";
        return id + column + oneToMany;
    }

    private String getColumnName() {
        return StringUtil.toDatabaseName(name);
    }

}
