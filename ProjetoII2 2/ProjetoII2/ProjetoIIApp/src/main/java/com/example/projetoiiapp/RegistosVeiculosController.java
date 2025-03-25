package com.example.projetoiiapp;

import baseDados.bll.*;

import baseDados.entity.*;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class RegistosVeiculosController implements Initializable {

    @FXML
    private ComboBox novoEstadoComboBox;
    @FXML
    private Button voltarButton;
    @FXML
    private TextField matriculaField;
    @FXML
    private Button pesquisarButton;

    @FXML
    private TableView<VeiculoEntity> tableView;

    @FXML
    private TableColumn<VeiculoEntity, String> estadoPedidoColumn;
    @FXML
    private TableColumn<VeiculoEntity, String> nomeColumn;
    @FXML
    private TableColumn<VeiculoEntity, String> marcaColumn;
    @FXML
    private TableColumn<VeiculoEntity, String> modeloColumn;
    @FXML
    private TableColumn<VeiculoEntity, String> matriculaColumn;
    @FXML
    private TableColumn<VeiculoEntity,String> anoFabricoColumn;
    @FXML
    private Button menuUserButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preencher();
        ObservableList<String> itemsEstado = FXCollections.observableArrayList(EstadoPedidobll.listar());
        novoEstadoComboBox.setItems(itemsEstado);

    }
    @FXML
    public void preencher(){
        nomeColumn.setCellValueFactory(cellData -> {
            String nome = Condutorbll.getWithId(cellData.getValue().getIdCondutor()).getNome();
            return new SimpleStringProperty(nome);
        });
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        matriculaColumn.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        anoFabricoColumn.setCellValueFactory(new PropertyValueFactory<>("anoFabrico"));
        estadoPedidoColumn.setCellValueFactory(cellData -> {
            int idEstado = cellData.getValue().getIdEstado();
            String estado = EstadoPedidobll.getEstado(idEstado); // Obtém o estado do pedido
            return new SimpleStringProperty(estado);
        });


        tableView.setItems(FXCollections.observableArrayList(Veiculobll.listarVeiculosPendentes()));

    }
    @FXML
    private void BackButton(MouseEvent event) {
        try {
            // Carregando a cena anterior
            // Certifique-se de ajustar o caminho do arquivo FXML conforme necessário
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("painel-responsavel-registos.fxml"));
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
    private void setRegistosCondutoresButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registos-condutores.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 871, 663);
            Stage stage = (Stage) pesquisarButton.getScene().getWindow();
            stage.setTitle("Registos de Condutores");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void pesquisarButton(){
        if(matriculaField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insira uma matrícula!");
            alert.setHeaderText(null);
            alert.setContentText("É necessário inserir uma matrícula para filtrar a pesquisa!.");
            alert.showAndWait();
        }
        else{
            nomeColumn.setCellValueFactory(cellData -> {
                String nome = Condutorbll.getWithId(cellData.getValue().getIdCondutor()).getNome();
                return new SimpleStringProperty(nome);
            });
            marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
            modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            matriculaColumn.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            anoFabricoColumn.setCellValueFactory(new PropertyValueFactory<>("anoFabrico"));
            estadoPedidoColumn.setCellValueFactory(cellData -> {
                int idEstado = cellData.getValue().getIdEstado();
                String estado = EstadoPedidobll.getEstado(idEstado); // Obtém o estado do pedido
                return new SimpleStringProperty(estado);
            });

            tableView.setItems(FXCollections.observableArrayList(Veiculobll.listarVeiculosPendentesMatricula(matriculaField.getText())));
        }
    }

    @FXML
    private void atualizarEstado(MouseEvent event) {
        // Verifica se um condutor e um novo estado foram selecionados
        if (tableView.getSelectionModel().getSelectedItem() != null && novoEstadoComboBox.getValue() != null) {
            // Obtém o condutor selecionado na tabela
            VeiculoEntity veiculoSelecionado = tableView.getSelectionModel().getSelectedItem();

            // Obtém o novo estado selecionado na ComboBox
            String novoEstado = (String) novoEstadoComboBox.getValue();

            // Obtém o ID do novo estado
            int idNovoEstado = EstadoPedidobll.getIdEstado(novoEstado);

            // Atualiza o estado do condutor selecionado
            veiculoSelecionado.setIdEstado(idNovoEstado);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Estado Atualizado");
            alert.setHeaderText(null);
            alert.setContentText("Estado atualizado com sucesso!.");
            alert.showAndWait();

            // Atualiza a exibição da tabela
            tableView.refresh(); // Isso atualizará a exibição da tabela para refletir as alterações feitas no objeto do condutor
            Veiculobll.atualizar(veiculoSelecionado);
        } else {
            // Caso o usuário não tenha selecionado nenhum condutor ou nenhum novo estado
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Necessária");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um veículo e um novo estado.");
            alert.showAndWait();
        }
    }

    @FXML
    private void terminarSessao() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Terminar Sessão");
        alert.setHeaderText(null);
        alert.setContentText("Deseja terminar sessão?");

        ButtonType confirmarButton = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(confirmarButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == confirmarButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("iniciar-sessao.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root, 600, 400);
                Stage stage = (Stage) voltarButton.getScene().getWindow();
                stage.setTitle("Iniciar Sessão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(result.isPresent() && result.get()== cancelButton){
            alert.close();
        }
    }


}
