package com.dbr.generator.jpa;

import com.dbr.generator.basic.util.VelocityUtil;
import com.dbr.util.StringUtil;
import lombok.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DatabaseJPAUtil {


    public enum DatabaseType {
        ORACLE, MYSQL, SQL, H2_IN_MEMORY, H2_FILESYSTEM,
    }

    public enum TableType {
        TABLE("TABLE"), VIEW("VIEW"), SYSTEM_TABLE("SYSTEM TABLE"), GLOBAL_TEMPORARY("GLOBAL TEMPORARY"), LOCAL_TEMPORARY("LOCAL TEMPORARY"), ALIAS("ALIAS"), SYNONYM("SYNONYM");
        private String tableType;

        TableType(String tableType) {
            this.tableType = tableType;
        }

        public String getTableType() {
            return tableType;
        }
    }

    private DatabaseType databaseType;

    private String url;

    private String username;

    private String password;

    private String catalog;

    private String schema;

    public DatabaseJPAUtil(DatabaseType databaseType, String host, int port, String username, String password, String catalog, String schema) throws ClassNotFoundException {
        this.databaseType = databaseType;
        this.username = username;
        this.password = password;
        this.catalog = catalog;
        this.schema = schema;


        switch (this.databaseType) {
            case ORACLE:
                this.url = "jdbc:oracle:thin:@";
                break;
            case MYSQL:
                this.url = new StringBuilder().append("jdbc:mysql://").append(host).append(":").append(port).append("/").append(schema).append("?serverTimezone=UTC&useSSL=false").toString();
                break;
            case SQL:
                break;
            case H2_IN_MEMORY:
                this.url = new StringBuilder().append("jdbc:h2:mem://").append(host).toString();
                break;
            case H2_FILESYSTEM:
                this.url = "jdbc:h2:file:///${APPDATA}/maven-archetype-multiproject/maven-archetype-multiproject-h2-db;AUTO_SERVER=TRUE";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.databaseType);
        }

    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, URISyntaxException {
        DatabaseJPAUtil databaseJPAUtil = new DatabaseJPAUtil(DatabaseType.MYSQL, "5.181.49.66", 3306, "root", "weight-manager-123", null, "weightmanager");
        databaseJPAUtil.createJPAEntities("com.dbr.generator.weightmanager");
    }

    public void createJPAEntities(String packageName) throws SQLException, IOException, URISyntaxException {

        List<JPAEntityReference> jpaEntityReferences = new ArrayList<>();

        //get jpa data from database
        for (TableReference tableReference : this.getTables(null, TableType.TABLE, TableType.VIEW)) {
            List<PrimaryKeyReference> primaryKeyReferences = getPrimaryKeys(tableReference.tableName);
            List<ColumnReference> columnReferences = getColumns(tableReference.tableName);
            List<ImportedKeyReference> importedKeys = getImportedKeys(tableReference.tableName);
            jpaEntityReferences.add(new JPAEntityReference(packageName, tableReference, columnReferences, primaryKeyReferences, importedKeys));
        }

        //create jpa entities
        for (JPAEntityReference jpaEntityReference : jpaEntityReferences) {
            createJPAEntity(jpaEntityReference);
        }

        //update jpa entities with relations
        //Glaube das brauchen wir gar nicht
        //for (JPAEntityReference jpaEntityReference : jpaEntityReferences) {
            //updateJPAEntityWithRelations(jpaEntityReference);
        //}

    }

    private void updateJPAEntityWithRelations(JPAEntityReference jpaEntityReference) throws IOException {
        String writeDownPath = this.getClass().getResource("/").getPath() + "../../src/main/java/" + jpaEntityReference.getPackagePath();
        File path = new File(writeDownPath);

        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        for (ImportedKeyReference importedKeyReference : jpaEntityReference.getImportedKeys()) {
            Template templateEntity = velocityEngine.getTemplate("entity-mn-relation.vm");
            VelocityContext contextEntity = new VelocityContext();
            contextEntity.put("model", jpaEntityReference);
            contextEntity.put("importedKeyReference", importedKeyReference);
            StringWriter writerEntity = new StringWriter();
            templateEntity.merge(contextEntity, writerEntity);

            File jpaPKFile = new File(path, importedKeyReference.getJavaPkFilename());
            Path pathOfJPAPKFile = Path.of(jpaPKFile.getPath());

            String content = Files.readString(pathOfJPAPKFile);

            content = new StringBuilder().append(content, 0, content.lastIndexOf("}")).append(writerEntity.toString()).toString();

            writeDown(jpaPKFile, content);
        }

    }


    public void createJPAEntity(JPAEntityReference jpaEntityReference) throws IOException {

        String writeDownPath = this.getClass().getResource("/").getPath() + "../../src/main/java/" + jpaEntityReference.getPackagePath();

        File path = new File(writeDownPath);
        if (!path.exists()) {
            path.mkdirs();
        }

        VelocityEngine velocityEngine = VelocityUtil.getEngine();

        Template templateEntity = velocityEngine.getTemplate("entity.vm");
        VelocityContext contextEntity = new VelocityContext();
        contextEntity.put("model", jpaEntityReference);
        StringWriter writerEntity = new StringWriter();
        templateEntity.merge(contextEntity, writerEntity);

        writeDown(new File(path, jpaEntityReference.getFilename()), writerEntity.toString());

        if (jpaEntityReference.isIdClazz()) {
            Template templateEntityEmbeddableClazz = velocityEngine.getTemplate("embeddable-clazz.vm");
            VelocityContext contextEntityEmbeddableClazz = new VelocityContext();
            contextEntityEmbeddableClazz.put("model", jpaEntityReference);
            StringWriter writerEntityEmbeddableClazz = new StringWriter();
            templateEntityEmbeddableClazz.merge(contextEntityEmbeddableClazz, writerEntityEmbeddableClazz);
            writeDown(new File(path, jpaEntityReference.getFilenameIDClazz()), writerEntityEmbeddableClazz.toString());
        }


    }

    public List<TableReference> getTables(String tableNamePattern, TableType... types) throws SQLException {
        if (types == null) {
            types = new TableType[]{TableType.TABLE};
        }
        Connection connection = null;

        List<TableReference> tableRefs = new ArrayList<>();

        try {
            connection = getConnection();
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet resultSet = metadata.getTables(this.catalog, this.schema, tableNamePattern, Arrays.stream(types).map(TableType::getTableType).toArray(String[]::new));
            while (resultSet.next()) {
                TableReference tableReference = new TableReference();
                tableReference.setTableName(resultSet.getString("TABLE_NAME"));
                tableReference.setTableCat(resultSet.getString("TABLE_CAT"));
                tableReference.setTableSchema(resultSet.getString("TABLE_SCHEM"));
                tableReference.setTableType(resultSet.getString("TABLE_TYPE"));
                tableReference.setRemarks(resultSet.getString("REMARKS"));
                tableReference.setTypeSchema(resultSet.getString("TYPE_SCHEM"));
                tableReference.setSelfReferencingColName(resultSet.getString("SELF_REFERENCING_COL_NAME"));
                tableReference.setRefGeneration(resultSet.getString("REF_GENERATION"));
                tableRefs.add(tableReference);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return tableRefs;

    }

    public List<PrimaryKeyReference> getPrimaryKeys(String tableName) throws SQLException {
        Connection connection = null;
        List<PrimaryKeyReference> primaryKeyDTOS = new ArrayList<>();

        try {
            connection = getConnection();

            DatabaseMetaData metadata = connection.getMetaData();

            ResultSet primaryKeys = metadata.getPrimaryKeys(this.catalog, this.schema, tableName);

            if (primaryKeys != null) {
                while (primaryKeys.next()) {
                    PrimaryKeyReference primaryKeyReference = new PrimaryKeyReference();
                    primaryKeyReference.setTableCat(primaryKeys.getString("TABLE_CAT"));
                    primaryKeyReference.setTableSchema(primaryKeys.getString("TABLE_SCHEM"));
                    primaryKeyReference.setTableName(primaryKeys.getString("TABLE_NAME"));
                    primaryKeyReference.setColumnName(primaryKeys.getString("COLUMN_NAME"));
                    primaryKeyReference.setKeySequence(primaryKeys.getString("KEY_SEQ"));
                    primaryKeyReference.setPkName(primaryKeys.getString("PK_NAME"));
                    primaryKeyDTOS.add(primaryKeyReference);
                }
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return primaryKeyDTOS;
    }

    public List<ImportedKeyReference> getImportedKeys(String tableName) throws SQLException {
        Connection connection = null;
        List<ImportedKeyReference> importedKeyReferences = new ArrayList<>();

        try {
            connection = getConnection();

            DatabaseMetaData metadata = connection.getMetaData();

            ResultSet importedKeys = metadata.getImportedKeys(this.catalog, this.schema, tableName);

            if (importedKeys != null) {
                while (importedKeys.next()) {
                    ImportedKeyReference importedKeyReference = new ImportedKeyReference();
                    importedKeyReference.setPkName(importedKeys.getString("FK_NAME"));
                    importedKeyReference.setPkTableCat(importedKeys.getString("PKTABLE_CAT"));
                    importedKeyReference.setPkTableName(importedKeys.getString("PKTABLE_NAME"));
                    importedKeyReference.setPkTableSchema(importedKeys.getString("PKTABLE_SCHEM"));
                    importedKeyReference.setPkColumnName(importedKeys.getString("PKCOLUMN_NAME"));
                    importedKeyReference.setFkName(importedKeys.getString("PK_NAME"));
                    importedKeyReference.setFkTableCat(importedKeys.getString("FKTABLE_CAT"));
                    importedKeyReference.setFkTableName(importedKeys.getString("FKTABLE_NAME"));
                    importedKeyReference.setFkTableSchema(importedKeys.getString("FKTABLE_SCHEM"));
                    importedKeyReference.setFkColumnName(importedKeys.getString("FKCOLUMN_NAME"));
                    importedKeyReference.setKeySeq(importedKeys.getShort("KEY_SEQ"));
                    importedKeyReference.setUpdateRule(importedKeys.getShort("UPDATE_RULE"));
                    importedKeyReference.setDeleteRule(importedKeys.getShort("DELETE_RULE"));
                    importedKeyReference.setDeferrability(importedKeys.getShort("DEFERRABILITY"));
                    importedKeyReferences.add(importedKeyReference);
                }
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return importedKeyReferences;
    }

    protected List<ColumnReference> getColumns(String tableName) throws SQLException {

        Connection connection = null;
        List<ColumnReference> columnReferences = new ArrayList<>();

        try {
            connection = getConnection();

            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet resultSet = metadata.getColumns(this.catalog, this.schema, tableName, null);

            while (resultSet.next()) {

                ColumnReference columnReference = new ColumnReference();
                columnReference.setColumnName(resultSet.getString("COLUMN_NAME"));
                columnReference.setDataType(resultSet.getInt("DATA_TYPE"));
                columnReference.setColumnSize(resultSet.getInt("COLUMN_SIZE"));
                columnReference.setNullable(resultSet.getInt("NULLABLE") == 1);
                columnReference.setDecimalDigits(resultSet.getInt("DECIMAL_DIGITS"));
                columnReference.setRemarks(resultSet.getString("REMARKS"));
                columnReference.setType(resultSet.getString("TYPE_NAME"));
                columnReferences.add(columnReference);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return columnReferences;

    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.username, this.password);
    }

    @Data
    @ToString
    @AllArgsConstructor
    public static class JPAEntityReference {

        private String packageName;
        private TableReference tableReference;
        private List<ColumnReference> columnReferences;
        private List<PrimaryKeyReference> primaryKeyReferences;
        private List<ImportedKeyReference> importedKeys;

        public boolean isNotIdClazz() {
            return !this.isIdClazz();
        }

        public boolean isGenerateIdAnnotation() {
            return !this.isView() && this.isNotIdClazz();
        }

        public boolean isView() {
            return TableType.VIEW.equals(this.tableReference.getType());
        }

        public boolean isIdClazz() {
            return this.primaryKeyReferences.size() > 1;
        }

        public boolean isAssociationOverrides() {
            return this.isIdClazz() && getColumnImportedKeyReferencesIdFields().size() > 0;
        }


        public boolean isIdColumn(ColumnReference columnReference) {
            for (PrimaryKeyReference primaryKeyReference : this.primaryKeyReferences) {
                if (primaryKeyReference.columnName.equals(columnReference.columnName)) {
                    return true;
                }
            }
            return false;
        }

        public ImportedKeyReference getImportedKeyReference(ColumnReference columnReference) {
            for (ImportedKeyReference importedKeyReference : this.importedKeys) {
                if (importedKeyReference.getFkColumnName().equals(columnReference.getColumnName())) {
                    return importedKeyReference;
                }
            }
            return null;
        }

        public List<ColumnReference> getColumnReferencesFiltered() {
            List<ColumnReference> retval = new ArrayList<>();
            for (ColumnReference columnReference : this.getColumnReferences()) {
                if (isIdClazz() && isIdColumn(columnReference)) {
                    continue;
                }
                retval.add(columnReference);
            }

            return retval;
        }

        public List<ImportedKeyReference> getColumnImportedKeyReferencesIdFields() {
            List<ImportedKeyReference> retval = new ArrayList<>();
            for (ColumnReference columnReference : this.getColumnReferences()) {
                ImportedKeyReference importedKeyReference = this.getImportedKeyReference(columnReference);
                if (this.isIdColumn(columnReference) && importedKeyReference != null) {
                    retval.add(importedKeyReference);
                }
            }
            return retval;
        }

        public String getPackagePath() {
            return this.packageName.replace(".", "/");
        }

        public String getFilename() {
            return new StringBuilder().append(getJavaClassName()).append(".java").toString();
        }

        public String getJavaIdClassName() {
            return new StringBuilder().append(getJavaClassName()).append("Id").toString();
        }

        public String getJavaClassName() {
            return new StringBuilder().append(String.valueOf(getTableName().charAt(0)).toUpperCase()).append(StringUtil.underscoreToUpperLetter(getTableName().substring(1).toLowerCase())).append(this.isView() ? "View" : "").toString();
        }

        public String getTableName() {
            return this.tableReference.tableName;
        }

        public String getFilenameIDClazz() {
            return new StringBuilder().append(getJavaIdClassName()).append(".java").toString();
        }
    }

    @Data
    @ToString
    public static class TableReference {
        private String tableCat;
        private String tableSchema;
        private String tableName;
        private String tableType;
        private String remarks;
        private String typeSchema;
        private String typeName;
        private String selfReferencingColName;
        private String refGeneration;

        public TableType getType() {
            return TableType.valueOf(this.tableType);
        }

    }

    @Data
    @ToString
    public class PrimaryKeyReference {

        private String columnName;
        private String keySequence;
        private String pkName;
        private String tableCat;
        private String tableSchema;
        private String tableName;

    }

    @Data
    @ToString
    public class ImportedKeyReference {

        private String pkName;
        private String pkColumnName;
        private String pkTableCat;
        private String pkTableSchema;
        private String pkTableName;
        private String fkName;
        private String fkColumnName;
        private String fkTableCat;
        private String fkTableSchema;
        private String fkTableName;
        private short updateRule;
        private short deleteRule;
        private short keySeq;
        private short deferrability;

        public String getJavaPkClassName() {
            return new StringBuilder().append(String.valueOf(getPkTableName().charAt(0)).toUpperCase()).append(StringUtil.underscoreToUpperLetter(getPkTableName().substring(1).toLowerCase())).toString();
        }

        public String getJavaPkFieldName() {
            return StringUtil.toPropertieName(getPkTableName());
        }

        public String getMappedByFieldName(JPAEntityReference jpaEntityReference) {
            return jpaEntityReference.isIdClazz() ? new StringBuilder().append("id.").append(getJavaPkFieldName()).toString() : StringUtil.toPropertieName(getFkColumnName());
        }

        public String getJavaFkClassName() {
            return new StringBuilder().append(String.valueOf(getFkTableName().charAt(0)).toUpperCase()).append(StringUtil.underscoreToUpperLetter(getFkTableName().substring(1).toLowerCase())).toString();
        }

        public String getJavaFkFieldName() {
            return StringUtil.toPropertieName(getFkTableName());
        }

        public String getJavaPkFilename() {
            return new StringBuilder().append(this.getJavaPkClassName()).append(".java").toString();
        }
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public class ColumnReference {

        private String columnName;
        private String type;
        private Integer dataType;
        private Integer columnSize;
        private Boolean primaryKey = false;
        private Boolean nullable = false;
        private Integer decimalDigits;
        private String remarks;

        public boolean isGenerateColumnLength() {
            return this.columnSize != null && this.columnSize.intValue() != 255;
        }

        public String getJavaFieldName() {
            return StringUtil.toPropertieName(columnName);
        }

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
                case Types.BIGINT:
                    return decimalDigits != null && decimalDigits > 0 ? BigDecimal.class.getName() : Long.class.getSimpleName();
                case Types.TIMESTAMP:
                case Types.OTHER:
                case Types.DATE:
                    return Date.class.getName();
                case Types.FLOAT:
                    return Float.class.getSimpleName();
                case Types.BIT:
                    return Boolean.class.getSimpleName();
            }

            throw new RuntimeException(columnName + " -> unknown datatype: " + dataType);

        }
    }

    private void writeDown(File file, String content) throws IOException {
        Path path = Paths.get(file.getPath());
        if (file.exists()) {
            Files.delete(path);
        }
        Files.writeString(path, content, StandardOpenOption.CREATE);
    }
}
