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


public class RegistosCondutoresController implements Initializable {

    @FXML
    private ComboBox novoEstadoComboBox;
    @FXML
    private Button voltarButton;
    @FXML
    private TextField pesquisarField;
    @FXML
    private Button pesquisarButton;

    @FXML
    private TableView<CondutorEntity> tableView;

    @FXML
    private TableColumn<CondutorEntity, String> estadoPedidoColumn;
    @FXML
    private TableColumn<CondutorEntity, String> nomeColumn;
    @FXML
    private TableColumn<CondutorEntity, String> nCartaConducaoColumn;
    @FXML
    private TableColumn<CondutorEntity, String> nCertificadoTVDEColumn;
    @FXML
    private TableColumn<CondutorEntity, String> nCartaoCidadaoColumn;
    @FXML
    private TableColumn<CondutorEntity, String> generoColumn;
    @FXML
    private TableColumn<CondutorEntity, String> nacionalidadeColumn;

    @FXML
    private TableColumn<CondutorEntity, Integer> idadeColumn;

    @FXML
    private Button menuUserButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> itemsEstado = FXCollections.observableArrayList(EstadoPedidobll.listar());
        novoEstadoComboBox.setItems(itemsEstado);
        preencher();
    }

    @FXML
    public void preencher(){
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nCartaConducaoColumn.setCellValueFactory(cellData -> {
            String nConducao = cellData.getValue().getnConducao();
            return new SimpleStringProperty(nConducao != null ? nConducao : "");
        });
        nCertificadoTVDEColumn.setCellValueFactory(cellData -> {
            String nCertificadoTvde = cellData.getValue().getnCertificadoTvde();
            return new SimpleStringProperty(nCertificadoTvde != null ? nCertificadoTvde : "");
        });
        nCartaoCidadaoColumn.setCellValueFactory(cellData -> {
            String nCc = cellData.getValue().getnCc();
            return new SimpleStringProperty(nCc != null ? nCc : "");
        });
        generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        nacionalidadeColumn.setCellValueFactory(new PropertyValueFactory<>("idNacionalidade"));
        nacionalidadeColumn.setCellValueFactory(cellData -> {
            int idNacionalidade = cellData.getValue().getIdNacionalidade();
            String nacionalidade = Nacionalidadebll.getNacionalidade(idNacionalidade); // Obtém a nacionalidade
            return new SimpleStringProperty(nacionalidade);
        });
        estadoPedidoColumn.setCellValueFactory(cellData -> {
            int idEstado = cellData.getValue().getIdEstado();
            String estado = EstadoPedidobll.getEstado(idEstado); // Obtém o estado do pedido
            return new SimpleStringProperty(estado);
        });

        idadeColumn.setCellValueFactory(new PropertyValueFactory<>("idade"));

        tableView.setItems(FXCollections.observableArrayList(Condutorbll.listar()));
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
    private void registosVeiculos(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registos-veiculos.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 871, 663);
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Registos Veiculos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void pesquisarButton(){
        if(pesquisarField.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insira um Nº Cartao de Cidadao!");
            alert.setHeaderText(null);
            alert.setContentText("É necessário inserir um Nº Cartao de Cidadao para filtrar a pesquisa!.");
            alert.showAndWait();
        }
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nCartaConducaoColumn.setCellValueFactory(cellData -> {
            String nConducao = cellData.getValue().getnConducao();
            return new SimpleStringProperty(nConducao != null ? nConducao : "");
        });
        nCertificadoTVDEColumn.setCellValueFactory(cellData -> {
            String nCertificadoTvde = cellData.getValue().getnCertificadoTvde();
            return new SimpleStringProperty(nCertificadoTvde != null ? nCertificadoTvde : "");
        });
        nCartaoCidadaoColumn.setCellValueFactory(cellData -> {
            String nCc = cellData.getValue().getnCc();
            return new SimpleStringProperty(nCc != null ? nCc : "");
        });
        generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        nacionalidadeColumn.setCellValueFactory(new PropertyValueFactory<>("idNacionalidade"));
        nacionalidadeColumn.setCellValueFactory(cellData -> {
            int idNacionalidade = cellData.getValue().getIdNacionalidade();
            String nacionalidade = Nacionalidadebll.getNacionalidade(idNacionalidade); // Obtém a nacionalidade
            return new SimpleStringProperty(nacionalidade);
        });
        estadoPedidoColumn.setCellValueFactory(cellData -> {
            int idEstado = cellData.getValue().getIdEstado();
            String estado = EstadoPedidobll.getEstado(idEstado); // Obtém o estado do pedido
            return new SimpleStringProperty(estado);
        });

        idadeColumn.setCellValueFactory(new PropertyValueFactory<>("idade"));

        tableView.setItems(FXCollections.observableArrayList(Condutorbll.listarPrefix(pesquisarField.getText())));
    }

    @FXML
    private void atualizarEstado(MouseEvent event) {
        // Verifica se um condutor e um novo estado foram selecionados
        if (tableView.getSelectionModel().getSelectedItem() != null && novoEstadoComboBox.getValue() != null) {
            // Obtém o condutor selecionado na tabela
            CondutorEntity condutorSelecionado = tableView.getSelectionModel().getSelectedItem();

            // Obtém o novo estado selecionado na ComboBox
            String novoEstado = (String) novoEstadoComboBox.getValue();

            // Obtém o ID do novo estado
            int idNovoEstado = EstadoPedidobll.getIdEstado(novoEstado);

            // Atualiza o estado do condutor selecionado
            condutorSelecionado.setIdEstado(idNovoEstado);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Estado Atualizado");
            alert.setHeaderText(null);
            alert.setContentText("Estado atualizado com sucesso!.");
            alert.showAndWait();

            // Atualiza a exibição da tabela
            tableView.refresh(); // Isso atualizará a exibição da tabela para refletir as alterações feitas no objeto do condutor
            Condutorbll.atualizar(condutorSelecionado);
        } else {
            // Caso o usuário não tenha selecionado nenhum condutor ou nenhum novo estado
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Necessária");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um condutor e um novo estado.");
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
