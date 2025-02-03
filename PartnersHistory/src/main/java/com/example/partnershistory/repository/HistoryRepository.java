package com.example.partnershistory.repository;

import com.example.partnershistory.utils.DatabaseConnection;
import com.example.partnershistory.entity.History;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryRepository {

    // Загрузка всех продаж
    public ObservableList<History> getAllHistorySales() {
        return getHistorySalesByPartner(null);
    }

    // Получение списка партнёров
    public ObservableList<String> getAllPartners() {
        ObservableList<String> partnerList = FXCollections.observableArrayList();
        partnerList.add("Все"); // Добавляем пункт "Все"

        String query = "SELECT DISTINCT partners_title FROM partners ORDER BY partners_title";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                partnerList.add(resultSet.getString("partners_title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partnerList;
    }

    // Загрузка продаж по конкретному партнёру (или всех, если name == null)
    public ObservableList<History> getHistorySalesByPartner(String partnerName) {
        ObservableList<History> historyList = FXCollections.observableArrayList();

        String query = """
            SELECT hs.history_sale_id, 
                   p.partners_title AS partners_title, 
                   pr.product_title AS product_name, 
                   hs.sale_date, 
                   hs.product_count
            FROM history_sale hs
            JOIN partners p ON hs.partners_id = p.partners_id
            JOIN products pr ON hs.product_id = pr.product_id
            """ + (partnerName != null && !partnerName.equals("Все") ? " WHERE p.partners_title = ?" : "");

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (partnerName != null && !partnerName.equals("Все")) {
                preparedStatement.setString(1, partnerName);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    History history = new History(
                            resultSet.getInt("history_sale_id"),
                            resultSet.getString("partners_title"),
                            resultSet.getString("product_name"),
                            resultSet.getDate("sale_date").toLocalDate(),
                            resultSet.getInt("product_count")
                    );
                    historyList.add(history);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historyList;
    }
}