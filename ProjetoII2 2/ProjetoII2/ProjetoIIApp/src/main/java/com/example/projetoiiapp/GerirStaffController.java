package com.example.projetoiiapp;

import baseDados.bll.*;
import baseDados.entity.StaffEntity;
import baseDados.entity.VeiculoEntity;
import baseDados.entity.ViagemEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GerirStaffController {

    @FXML
    private Button voltarButton;
    @FXML
    private Button registarButton;
    @FXML
    private Button eliminarButton;
    @FXML
    private TextField numeroField;
    @FXML
    private Button pesquisarButton;
    @FXML
    private TableView<StaffEntity> tableView;
    @FXML
    private TableColumn<StaffEntity, String> numeroColumn;
    @FXML
    private TableColumn<StaffEntity, String> nomeColumn;
    @FXML
    private TableColumn<StaffEntity, String> funcaoColumn;


    public void preencher(List<StaffEntity> staff) {
        numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        funcaoColumn.setCellValueFactory(cellData -> {
            int idTipoStaff = cellData.getValue().getIdTipoStaff();
            String funcao = TipoStaffbll.listarWithId(idTipoStaff).getTipo(); // Obtém o estado do pedido
            return new SimpleStringProperty(funcao);
        });
        tableView.setItems(FXCollections.observableArrayList(staff));
    }

    @FXML
    public  void setPesquisarButton(){
        if(numeroField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, insira um numero para restringir a pesquisa.");
            alert.showAndWait();
        }
        else{
            preencher(Staffbll.listarPrefix(numeroField.getText()));
        }
    }
    @FXML
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("painel-admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setEliminarButton(){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            StaffEntity staff = tableView.getSelectionModel().getSelectedItem();
            Staffbll.apagar(staff);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Staff eliminado com sucesso");
            alert.setHeaderText(null);
            alert.showAndWait();
            tableView.refresh();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Necessária");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um staff.");
            alert.showAndWait();
        }
    }

    @FXML
    private void setRegistarButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registar-staff.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Registar Staff");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void setFeedBacksViagensButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("feedbacks-viagens.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1484, 664);
            Stage stage = (Stage) registarButton.getScene().getWindow();
            stage.setTitle("FeedBacks Viagens");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void carregarDados(){
        preencher((Staffbll.listar()));
    }

}
