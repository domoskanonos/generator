package com.dbr.generator.gen.server.entity.model;

import com.dbr.generator.basic.property.dto.PropertyDTO;
import com.dbr.generator.basic.util.GeneratorUtil;
import com.dbr.util.StringUtil;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
public class EntityVM {

    private String packageName;
    private String tableName;
    private String clazzSimpleName;
    private String extendsClazzSimpleName;
    private boolean idClazz;
    private String idClazzSimpleName;

    private List<EntityProperty> entityProperties = new ArrayList<>();

    public EntityVM(String packageName, String clazzSimpleName, Class<?> clazz) {
        this.packageName = packageName;
        this.clazzSimpleName = clazzSimpleName;
        this.tableName = StringUtil.toDatabaseName(this.clazzSimpleName);
        this.extendsClazzSimpleName = clazz.getSuperclass().getSimpleName();
        this.idClazzSimpleName = GeneratorUtil.getIDClazzSimpleName(clazz);
        if (this.idClazzSimpleName != null) {
            idClazz = true;
        }
        this.entityProperties = map(GeneratorUtil.getJavaProperties(clazz));
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EntityProperty extends PropertyDTO {
        private String columnName;
        private Integer size;
        private boolean primaryKey = false;
        private boolean nullable = true;
        private boolean idClazz = false;

        public EntityProperty(PropertyDTO javaProperty) {
            this.setPropertyType(javaProperty.getPropertyType());
            this.setName(javaProperty.getName());
            this.columnName = StringUtil.toDatabaseName(javaProperty.getName());
            this.size = javaProperty.getLength();
        }

        public String propertieAnnotations() {
            String id = "";
            if (primaryKey) {
                id = "\n    @Id";
                if (!idClazz) {
                    id += "\n    @GeneratedValue(strategy = GenerationType.IDENTITY)";
                }
            }
            String columnSize = size != null && size > 0 ? ", length=" + size : "";
            String nullableAnnotation = nullable && !primaryKey ? "" : ", nullable = false";
            String unique = primaryKey ? ", unique = true" : "";
            String column = "\n    @Column(name = \"" + columnName + "\"" + unique + columnSize + nullableAnnotation
                    + " )";
            String type = getPropertyType().getJavaTypeSimpleName();
            String oneToMany = getPropertyType().isBaseJavaType() || (!type.contains("List") && !type.contains("Set"))
                    ? "" : "\n    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )";
            return id + column + oneToMany;
        }

    }

    public static List<EntityProperty> map(List<PropertyDTO> javaProperties) {
        List<EntityProperty> list = new ArrayList<>();
        for (PropertyDTO javaProperty : javaProperties) {
            EntityProperty property = new EntityProperty(javaProperty);
            list.add(property);
        }
        return list;
    }

    public static List<EntityProperty> mapCSV(List<PropertyDTO> javaProperties) {
        List<EntityProperty> list = new ArrayList<>();
        for (PropertyDTO javaProperty : javaProperties) {
            EntityProperty property = new EntityProperty(javaProperty);
            list.add(property);
        }
        return list;
    }

}
