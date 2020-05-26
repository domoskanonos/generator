package com.dbr.generator.gen.server.db;

import com.dbr.generator.gen.server.db.model.Column;
import com.dbr.generator.gen.server.db.model.PrimaryKey;
import com.dbr.generator.gen.server.db.model.TableVM;
import com.dbr.generator.gen.server.entity.EntityGenerator;
import com.dbr.generator.gen.server.entity.model.EntityVM;
import com.dbr.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableGenerator extends AbstractDatabaseGenerator {

    private static final Logger log = LoggerFactory.getLogger(TableGenerator.class);

    private TableVM tableVM;

    public TableGenerator(TableVM tableVM) throws ClassNotFoundException {
        super(tableVM.getDatabase(), tableVM.getJavaName(), tableVM.getPackageName());
        this.tableVM = tableVM;
    }

    @Override
    public String create() throws SQLException {

        String tableName = tableVM.getTableName();
        log.info("create entity from table: {}", tableName);

        EntityVM.EntityVMBuilder builder = EntityVM.builder();
        builder.packageName(getPackageName());
        builder.tableName(tableName);
        builder.clazzSimpleName(tableVM.getJavaName());

        List<PrimaryKey> primaryKeys = getPrimaryKeys(tableName);

        boolean idClass = primaryKeys != null && primaryKeys.size() > 1;
        String idClazzSimpleName = tableVM.getJavaName() + "PK";

        builder.idClazz(idClass);
        builder.idClazzSimpleName(idClazzSimpleName);

        List<Column> columns = getColumns(this.tableVM.getTableName());
        if (primaryKeys != null) {
            for (PrimaryKey primaryKey : primaryKeys) {
                for (Column column : columns) {
                    if (primaryKey.getColumnName().equals(column.getName())) {
                        column.setPrimaryKey(true);
                    }
                }
            }
        }

        List<EntityVM.EntityProperty> entityProperties = new ArrayList<>();
        columns.forEach(column -> {
            EntityVM.EntityProperty entityProperty = new EntityVM.EntityProperty();
            entityProperty.setIdClazz(idClass);
            entityProperty.setPrimaryKey(column.getPrimaryKey());
            entityProperty.setName(StringUtil.toPropertieName(column.getName()));
            entityProperty.setColumnName(column.getName());
            entityProperty.setSize(column.getSize());
            entityProperty.setTypeSimpleName(column.getJavaType());
            entityProperty.setNullable(column.getNullable());
            entityProperties.add(entityProperty);
        });
        builder.entityProperties(entityProperties);

        EntityVM entityVM = builder.build();
        EntityGenerator entityGenerator = new EntityGenerator(entityVM);
        return entityGenerator.create();

    }

}
