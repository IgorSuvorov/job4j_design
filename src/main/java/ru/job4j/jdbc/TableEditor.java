package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;
import static java.sql.DriverManager.getConnection;

public class TableEditor implements AutoCloseable {

    private static Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private static Connection initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("CREATE TABLE if not exists %s ();",
                tableName
        );
        statementConnection(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("DROP TABLE if exists %s;",
                tableName
        );
        statementConnection(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type
        );
        statementConnection(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName
        );
        statementConnection(sql);
    }

    public void renameColumn(String tableName,
                             String columnName,
                             String newColumnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        statementConnection(sql);
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

    private void statementConnection(String sql) throws Exception {
        try (Connection connection = initConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Properties pps;
        try (FileReader reader = new FileReader("app.properties")) {
            pps = new Properties();
            pps.load(reader);
        }
        TableEditor te = new TableEditor(pps);
        te.dropTable("test");
        te.createTable("test");
        te.addColumn("test", "amount", "int");
        te.renameColumn("test", "amount", "price");
        System.out.println(getTableScheme(initConnection(), "test"));
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