package com.example.partnershistory.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.partnershistory.entity.History;
import com.example.partnershistory.repository.HistoryRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HistoryController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button findButton;

    @FXML
    private TableView<History> historySale;

    @FXML
    private TableColumn<History, Integer> idHistorySale;

    @FXML
    private TableColumn<History, String> partnerName;

    @FXML
    private TableColumn<History, String> productName;

    @FXML
    private TableColumn<History, String> saleDate;

    @FXML
    private TableColumn<History, Integer> productCount;

    @FXML
    private ComboBox<String> partnersCombobox;

    private final HistoryRepository historyRepository = new HistoryRepository();

    @FXML
    void backEvent(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void findEvent(MouseEvent event) {
        String selectedPartner = partnersCombobox.getValue();
        loadHistorySales(selectedPartner);
    }

    @FXML
    void initialize() {
        configureTable();
        loadPartners();
        loadHistorySales(null);
    }

    private void configureTable() {
        idHistorySale.setCellValueFactory(new PropertyValueFactory<>("history_sale_id"));
        partnerName.setCellValueFactory(new PropertyValueFactory<>("partnerName"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        saleDate.setCellValueFactory(new PropertyValueFactory<>("sale_date"));
        productCount.setCellValueFactory(new PropertyValueFactory<>("product_count"));
    }

    private void loadHistorySales(String partnerName) {
        ObservableList<History> historyList = historyRepository.getHistorySalesByPartner(partnerName);
        historySale.setItems(historyList);
    }

    private void loadPartners() {
        ObservableList<String> partners = historyRepository.getAllPartners();
        partnersCombobox.setItems(partners);
        partnersCombobox.getSelectionModel().selectFirst(); // ✅ Выбираем "Все" по умолчанию
    }
}
