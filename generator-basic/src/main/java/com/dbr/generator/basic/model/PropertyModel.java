package com.dbr.generator.basic.model;

import com.dbr.generator.basic.enumeration.TypeEnum;
import com.dbr.util.StringUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyModel {

    private String name;
    private TypeEnum propertyType;
    private boolean idProperty;
    private boolean searchable;
    private boolean nullable;
    private boolean useJPAIdClazz;
    private Integer length;

    public PropertyModel(String name) {
        this.name = name;
    }

    public PropertyModel(String name, Integer length) {
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