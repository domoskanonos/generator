package com.dbr.generator.gen.server.typescript;

import com.dbr.generator.basic.property.enumeration.PropertyTypeEnum;
import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.entity.EntityGenerator;
import com.dbr.generator.gen.server.entity.model.EntityVM;
import com.dbr.util.StringUtil;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class IF2JavaEntityGenerator extends AbstractGeneratorJava {

    private static final Logger log = LoggerFactory.getLogger(IF2JavaEntityGenerator.class);

    private String interfaceString;

    public IF2JavaEntityGenerator(String packageName, String interfaceString) {
        super(StringUtil.getStringBetween(interfaceString, "interface", "extends", "{"), packageName);
        this.interfaceString = interfaceString;
    }

    public static void main(String[] args) throws Exception {
        IF2JavaEntityGenerator if2JavaEntityGenerator = new IF2JavaEntityGenerator("com.dbr.generator.entity",
                "export interface ECommerceTileListIF extends AbstractInterfaceIF {\n" + "   gridClazz: string;\n"
                        + "   columnFlexBasisValue?: string;\n" + "   items: ECommerceItemIF[];\n" + "}");

        String entity = if2JavaEntityGenerator.create();
        log.info(entity);
    }

    @Override
    public String create() throws Exception {

        EntityVM.EntityVMBuilder builder = EntityVM.builder();
        builder.packageName(getPackageName());
        builder.tableName(StringUtil.toDatabaseName(getClazzSimpleName()));
        builder.clazzSimpleName(getClazzSimpleName());
        builder.extendsClazzSimpleName(StringUtil.getStringBetween(interfaceString, getClazzSimpleName(), "{"));

        String properties = StringUtil.getStringBetween(interfaceString, "{", "}");
        String[] propertiesAsArray = properties.split(";");

        List<EntityVM.EntityProperty> propertieList = new ArrayList<>();

        EntityVM.EntityProperty idProperty = new EntityVM.EntityProperty();
        idProperty.setPrimaryKey(true);
        idProperty.setName("id");
        idProperty.setColumnName("ID");
        idProperty.setPropertyType(PropertyTypeEnum.TYPE_LONG);
        idProperty.setNullable(false);
        idProperty.setIdClazz(false);
        propertieList.add(idProperty);

        for (String propertie : propertiesAsArray) {
            String cleanedPropertie = StringUtil.cleanString(propertie);
            String[] splitCleanedPropertie = cleanedPropertie.split(":");
            if (cleanedPropertie.length() > 1) {
                String typescriptName = splitCleanedPropertie[0];
                String typescriptType = splitCleanedPropertie[1];

                EntityVM.EntityProperty property = new EntityVM.EntityProperty();
                String propertieName = typescriptName.replaceAll("\\?", "");
                property.setName(propertieName);
                property.setColumnName(StringUtil.toDatabaseName(propertieName));
                property.setPropertyType(PropertyTypeEnum.byTypescriptType(typescriptType));
                property.setNullable(typescriptName.contains("?"));
                property.setPrimaryKey(false);
                propertieList.add(property);

            }
        }
        builder.entityProperties(propertieList);

        EntityVM entityVM = builder.build();

        EntityGenerator entityGenerator = new EntityGenerator(entityVM);
        return entityGenerator.create();

    }

    private String toJavaType(String typescriptType) {
        String javaType = typescriptType.trim();
        switch (javaType) {
        case "string":
            javaType = String.class.getSimpleName();
            break;
        case "number":
            javaType = Integer.class.getSimpleName();
            break;
        case "any":
            javaType = Object.class.getSimpleName();
            break;
        case "boolean":
            javaType = Boolean.class.getSimpleName();
            break;
        case "string[]":
            javaType = String.class.getSimpleName() + "[]";
            break;
        default:
            break;
        }

        javaType = StringUtil.arrayToList(javaType);

        return javaType;
    }

}
