package com.example.modul2.repository;

import com.example.modul2.entity.Partner;
import com.example.modul2.databaseTools.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartnerRepository {

    /**
     * Возвращает список всех партнеров из таблицы partners.
     *
     * @return список объектов Partner
     */
    public ObservableList<Partner> getAllPartnersObservable() {
        ObservableList<Partner> partners = FXCollections.observableArrayList();
        String query = "SELECT * FROM partners";
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Создаем объект Partner
                Partner partner = new Partner(
                        resultSet.getInt("partners_id"),
                        resultSet.getInt("partners_type"),
                        resultSet.getString("partners_title"),
                        resultSet.getString("full_name"),
                        resultSet.getString("partners_email"),
                        resultSet.getString("partners_phone"),
                        resultSet.getString("address_partners"),
                        resultSet.getLong("partners_inn"),
                        resultSet.getInt("partners_rating")
                );

                // Рассчитываем скидку для текущего партнера
                double totalSales = calculateTotalSalesForPartner(connection, partner.getPartnersId());
                double discount = calculateDiscount(totalSales);
                partner.setDiscount(discount);

                // Добавляем партнера в список
                partners.add(partner);
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных из таблицы partners: " + e.getMessage());
        }
        return partners;
    }


    /**
     * Рассчитывает общий объем продаж для указанного партнера.
     *
     * @param connection соединение с базой данных
     * @param partnerId  идентификатор партнера
     * @return общий объем продаж
     */
    private double calculateTotalSalesForPartner(Connection connection, int partnerId) throws SQLException {
        String query = "SELECT SUM(product_count) AS total_sales FROM history_sale WHERE partners_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, partnerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("total_sales");
            }
        }
        return 0.0;
    }

    /**
     * Рассчитывает скидку на основе общего объема продаж.
     *
     * @param totalSales общий объем продаж
     * @return значение скидки в процентах
     */
    private double calculateDiscount(double totalSales) {
        if (totalSales < 10000) {
            return 0.0; // Скидка 0%
        } else if (totalSales < 50000) {
            return 5.0; // Скидка 5%
        } else if (totalSales < 300000) {
            return 10.0; // Скидка 10%
        } else {
            return 15.0; // Скидка 15%
        }
    }

    /**
     * Добавляет нового партнера в базу данных.
     *
     * @param partner объект Partner
     * @return true, если добавление прошло успешно
     */
    public boolean addPartner(Partner partner) {
        String query = "INSERT INTO partners (partners_type, partners_title, full_name, " +
                "partners_email, partners_phone, address_partners, partners_inn, partners_rating) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, partner.getPartnersType());
            statement.setString(2, partner.getPartnerTitle());
            statement.setString(3, partner.getFullName());
            statement.setString(4, partner.getPartnersEmail());
            statement.setString(5, partner.getPartnersPhone());
            statement.setString(6, partner.getAddressPartners());
            statement.setLong(7, partner.getPartnersInn());
            statement.setDouble(8, partner.getPartnersRating());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении партнера: " + e.getMessage());
        }
        return false;
    }

    /**
     * Удаляет партнера из базы данных.
     *
     * @param partnerId идентификатор партнера
     * @return true, если удаление прошло успешно
     */
    public boolean deletePartner(String partnerId) {
        String query = "DELETE FROM partners WHERE partners_id = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, partnerId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении партнера: " + e.getMessage());
        }
        return false;
    }

    /**
     * Обновляет данные партнера в базе данных.
     *
     * @param partner объект Partner с обновленными данными
     * @return true, если обновление прошло успешно
     */
    public boolean updatePartner(Partner partner) {
        String query = "UPDATE partners SET partners_type = ?, partners_title = ?, full_name = ?, " +
                "partners_email = ?, partners_phone = ?, address_partners = ?, partners_inn = ?, partners_rating = ? " +
                "WHERE partners_id = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, partner.getPartnersType());
            statement.setString(2, partner.getPartnerTitle());
            statement.setString(3, partner.getFullName());
            statement.setString(4, partner.getPartnersEmail());
            statement.setString(5, partner.getPartnersPhone());
            statement.setString(6, partner.getAddressPartners());
            statement.setLong(7, partner.getPartnersInn());
            statement.setInt(8, partner.getPartnersRating());
            statement.setInt(9, partner.getPartnersId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении партнера: " + e.getMessage());
        }
        return false;
    }

    public ObservableList<Pair<Integer, String>> getAllPartnerTypes() {
        ObservableList<Pair<Integer, String>> partnerTypes = FXCollections.observableArrayList();
        String query = "SELECT partners_type_id, partners_title FROM partners_type";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("partners_type_id");
                String title = resultSet.getString("partners_title");
                partnerTypes.add(new Pair<>(id, title)); // Сохраняем ID + Title
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении данных из таблицы partners_type: " + e.getMessage());
        }

        return partnerTypes;
    }
}
