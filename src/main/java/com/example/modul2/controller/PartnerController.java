package com.example.modul2.controller;

import com.example.modul2.entity.Partner;
import com.example.modul2.repository.PartnerRepository;
import com.example.partnershistory.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


import java.util.ResourceBundle;

import javafx.util.Pair;

public class PartnerController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private ListView<Partner> partnerListView;

    @FXML
    private TextField idField;

    @FXML
    private Button historyButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField innField;

    @FXML
    private TextField ratingField;

    @FXML
    private TextField typeField;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<Pair<Integer, String>> typeComboBox;
    private final PartnerRepository partnerRepository = new PartnerRepository();
    private ObservableList<Partner> partnerList = FXCollections.observableArrayList();
    private ObservableList<Pair<Integer, String>> partnerTypes = FXCollections.observableArrayList();
    private Partner selectedPartner;

    @FXML
    private void initialize() {

        loadPartnersFromDatabase();
        loadPartnerTypes();

        // Обработка кликов в ListView
        partnerListView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Partner selected = partnerListView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    populateFieldsForEditing(selected);
                }
            }
        });

        // Отображение только названия типов в ComboBox
        typeComboBox.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(Pair<Integer, String> pair) {
                return pair == null ? "" : pair.getValue();
            }

            @Override
            public Pair<Integer, String> fromString(String string) {
                return partnerTypes.stream().filter(p -> p.getValue().equals(string)).findFirst().orElse(null);
            }
        });

        // Обработка двойного клика для редактирования
        partnerListView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                Partner selected = partnerListView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    populateFieldsForEditing(selected);
                }
            }
        });

        // Устанавливаем отображение карточек в ListView
        partnerListView.setCellFactory(lv -> {
            ListCell<Partner> cell = new ListCell<>() {
                @Override
                protected void updateItem(Partner partner, boolean empty) {
                    super.updateItem(partner, empty);
                    if (empty || partner == null) {
                        setGraphic(null);
                    } else {
                        VBox card = new VBox(5);
                        card.setStyle("-fx-border-color: black; -fx-background-color: white; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");

                        Text typeTitle = new Text(partner.getPartnersType() + " | " + partner.getPartnerTitle());
                        typeTitle.setStyle("-fx-font-weight: bold;");

                        Text fullName = new Text(partner.getFullName());
                        Text phone = new Text(partner.getPartnersPhone());
                        Text rating = new Text("Rating: " + partner.getPartnersRating());
                        Text discount = new Text("Discount: " + partner.getDiscount() + "%");

                        card.getChildren().addAll(typeTitle, fullName, phone, rating, discount);
                        setGraphic(card);
                    }
                }
            };

            // Контекстное меню для редактирования партнера
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editItem = new MenuItem("Edit");

            editItem.setOnAction(event -> {
                Partner partner = cell.getItem();
                if (partner != null) {
                    populateFieldsForEditing(partner);
                }
            });

            contextMenu.getItems().add(editItem);

            cell.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && !cell.isEmpty()) {
                    contextMenu.show(cell, event.getScreenX(), event.getScreenY());
                } else {
                    contextMenu.hide();
                }
            });

            return cell;
        });
    }

    private void loadPartnersFromDatabase() {
        partnerList = partnerRepository.getAllPartnersObservable();
        partnerListView.setItems(partnerList);
    }

    private void loadPartnerTypes() {
        partnerTypes = partnerRepository.getAllPartnerTypes();
        typeComboBox.setItems(partnerTypes);
    }

    @FXML
    private void handleAddPartner(ActionEvent event) {
        try {
            Pair<Integer, String> selectedType = typeComboBox.getSelectionModel().getSelectedItem();
            if (selectedType == null) {
                showAlert("Input Error", "Please select a valid Partner Type.");
                return;
            }

            Partner partner = new Partner(
                    selectedPartner != null ? selectedPartner.getPartnersId() : null,
                    selectedType.getKey(),  // Сохраняем ID типа
                    titleField.getText(),
                    fullNameField.getText(),
                    emailField.getText(),
                    phoneField.getText(),
                    addressField.getText(),
                    Long.parseLong(innField.getText()),
                    Integer.parseInt(ratingField.getText())
            );

            boolean success;
            if (selectedPartner != null) {
                success = partnerRepository.updatePartner(partner);
            } else {
                success = partnerRepository.addPartner(partner);
            }

            if (success) {
                loadPartnersFromDatabase();
                clearInputFields();
                selectedPartner = null;
            } else {
                showAlert("Error", "Failed to save partner data.");
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numeric values for INN and Rating.");
        }
    }

    private void populateFieldsForEditing(Partner partner) {
        if (partner != null) {
            selectedPartner = partner;

            idField.setText(String.valueOf(partner.getPartnersId()));
            titleField.setText(partner.getPartnerTitle());
            fullNameField.setText(partner.getFullName());
            emailField.setText(partner.getPartnersEmail());
            phoneField.setText(partner.getPartnersPhone());
            addressField.setText(partner.getAddressPartners());
            innField.setText(String.valueOf(partner.getPartnersInn()));
            ratingField.setText(String.valueOf(partner.getPartnersRating()));

            // Выбираем соответствующий ID типа
            typeComboBox.getSelectionModel().select(
                    partnerTypes.stream()
                            .filter(p -> p.getKey().equals(partner.getPartnersType()))
                            .findFirst()
                            .orElse(null)
            );
        }
    }

    private void clearInputFields() {
        idField.clear();
        typeComboBox.getSelectionModel().clearSelection();
        titleField.clear();
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        innField.clear();
        ratingField.clear();
    }

    @FXML
    void toHisotryEvent(MouseEvent event) {
        try {
            HelloApplication historyApp = new HelloApplication();
            historyApp.start(new javafx.stage.Stage());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ошибка", "Не удалось открыть окно истории.");
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
