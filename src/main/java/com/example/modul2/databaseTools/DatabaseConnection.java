package com.example.modul2.databaseTools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Конфигурация подключения к базе данных
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123321";
    private static final String SCHEMA = "thirst";

    /**
     * Устанавливает подключение к базе данных PostgreSQL с указанной схемой.
     *
     * @return объект Connection для выполнения SQL-запросов
     * @throws SQLException если не удается подключиться к базе данных
     */
    public static Connection connect() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        // Установим схему для текущего подключения
        try (var statement = connection.createStatement()) {
            statement.execute("SET search_path TO " + SCHEMA);
        }

        return connection;
    }

    /**
     * Закрывает подключение к базе данных.
     *
     * @param connection объект Connection, который нужно закрыть
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Ошибка при закрытии подключения: " + e.getMessage());
            }
        }
    }
}
