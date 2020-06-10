package com.dbr.generator.basic.model;

import com.dbr.generator.basic.enumeration.PropertyType;
import com.dbr.util.StringUtil;
import lombok.Data;

@Data
public class PropertyModel {

    private ItemModel itemModel;

    private String name;
    private PropertyType propertyType;
    private String propertyTypeName;
    private boolean idProperty;
    private boolean mainProperty;
    private boolean nullable;
    private boolean useJPAIdClazz;
    private Integer length;

    private Boolean deactivated;

    public PropertyModel(ItemModel itemModel, String name) {
        this.itemModel = itemModel;
        this.name = name;
    }

    public PropertyModel(ItemModel itemModel, String name, Integer length) {
        this(itemModel, name);
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

    public String getI18nEditName() {
        return new StringBuilder().append(getI18nPropertyPrefix()).append(this.name).toString();
    }

    private String getI18nPropertyPrefix() {
        return new StringBuilder().append(this.itemModel.getNameToLowerCase()).append("_property_").toString();
    }

    public String getTypescriptType() {
        String typescriptType = this.propertyType.getTypescriptBaseType();
        return typescriptType != null ? typescriptType : getTypescriptTypeByPropertyTypeName();
    }

    public String getTypescriptNidocaInputfieldTag() {
        return propertyType.getTypescriptBaseType() != null ? "nidoca-inputfield" : getPropertyTypeNameToLowerCase() + "-combobox-component";
    }

    public String getPropertyTypeNameToLowerCase() {
        return this.propertyTypeName.toLowerCase();
    }

    public String getTypescriptTypeByPropertyTypeName() {
        if (this.getPropertyTypeName() == null || this.propertyTypeName.length() == 0) {
            return "any";
        }
        switch (this.propertyType) {
            case ARRAY_STRING:
                return "string[] array string";
            case BYTE_ARRAY:
            case LIST:
            case SET:
                return this.getPropertyTypeName() + "[]";
            case ENUMERATION:
            case OBJECT:
            default:
                return this.getPropertyTypeName();
        }
    }

}
