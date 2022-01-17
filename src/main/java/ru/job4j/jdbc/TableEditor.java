package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;
import static java.sql.DriverManager.getConnection;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format("CREATE TABLE if not exists %s ();",
                tableName
        );
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format("DROP TABLE if exists %s;",
                tableName
        );
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type
        );
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName
        );
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName,
                             String columnName,
                             String newColumnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("app.properties");
        Properties pps = new Properties();
        pps.load(reader);
        TableEditor te = new TableEditor(pps);

        te.createTable("test");
        te.addColumn("test", "amount", "int");
        te.renameColumn("test", "amount", "price");
        System.out.println(getTableScheme(connection, "test"));
        te.dropColumn("test", "price");
        te.dropTable("test");
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}