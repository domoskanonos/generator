package com.dbr.generator.gen.server.db;

import com.dbr.generator.gen.AbstractGeneratorJava;
import com.dbr.generator.gen.server.db.model.Column;
import com.dbr.generator.gen.server.db.model.Database;
import com.dbr.generator.gen.server.db.model.PrimaryKey;
import com.dbr.generator.gen.server.db.model.TableVM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * See documentation: https://docs.oracle.com/javase/7/docs/api/java/sql/DatabaseMetaData.html
 */
public abstract class AbstractDatabaseGenerator extends AbstractGeneratorJava {

    private Database database;

    public AbstractDatabaseGenerator(Database database, String clazzSimpleName, String packageName)
            throws ClassNotFoundException {
        super(clazzSimpleName, packageName);
        this.database = database;
        Class.forName(this.database.getDriverClazz());
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.database.getUrl(), this.database.getUsername(),
                this.database.getPassword());
    }

    /**
     * get tables by type:
     * <p>
     * TABLE_TYPE String => table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL
     * TEMPORARY", "ALIAS", "SYNONYM".
     *
     * @return
     * @throws SQLException
     */
    protected List<TableVM> getTables(String[] types) throws SQLException {
        if (types == null) {
            types = new String[] { "TABLE" };
        }
        Connection connection = null;

        List<TableVM> tableVMS = new ArrayList<>();

        try {
            connection = getConnection();
            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet resultSet = metadata.getTables(null, database.getSchema(), null, types);
            while (resultSet.next()) {
                tableVMS.add(new TableVM(getPackageName(), resultSet.getString("TABLE_NAME"), database));
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return tableVMS;

    }

    protected List<PrimaryKey> getPrimaryKeys(String tableName) throws SQLException {

        Connection connection = null;
        List<PrimaryKey> primaryKeyDTOS = new ArrayList<>();

        try {
            connection = getConnection();

            DatabaseMetaData metadata = connection.getMetaData();

            ResultSet primaryKeys = metadata.getPrimaryKeys(null, this.database.getSchema(), tableName);

            if (primaryKeys != null) {
                while (primaryKeys.next()) {
                    primaryKeyDTOS.add(new PrimaryKey(primaryKeys.getString("COLUMN_NAME"),
                            primaryKeys.getString("KEY_SEQ"), primaryKeys.getString("PK_NAME")));
                }
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return primaryKeyDTOS;
    }

    protected List<Column> getColumns(String tableName) throws SQLException {

        Connection connection = null;
        List<Column> columns = new ArrayList<>();

        try {
            connection = getConnection();

            DatabaseMetaData metadata = connection.getMetaData();
            ResultSet resultSet = metadata.getColumns(null, this.database.getSchema(), tableName, null);

            while (resultSet.next()) {

                columns.add(new Column(resultSet.getString("COLUMN_NAME"), resultSet.getString("TYPE_NAME"),
                        resultSet.getInt("DATA_TYPE"), resultSet.getInt("COLUMN_SIZE"), false,
                        resultSet.getShort("NULLABLE") == 1, resultSet.getInt("DECIMAL_DIGITS"),
                        resultSet.getString("REMARKS")));
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        return columns;

    }

}
