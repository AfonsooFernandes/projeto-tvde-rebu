package com.example.projetoiiapp;

import baseDados.bll.*;
import baseDados.entity.ViagemEntity;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class FeedbacksViagens{
    @FXML
    private Button voltarButton;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private DatePicker datePickerFim;
    @FXML
    private TableView<ViagemEntity> tableView;
    @FXML
    private TableColumn<ViagemEntity, String> condutorColumn;
    @FXML
    private TableColumn<ViagemEntity, String> passageiroColumn;
    @FXML
    private TableColumn<ViagemEntity, String> avaliacaoDoPassageiroColumn;
    @FXML
    private TableColumn<ViagemEntity, String> avaliacaoDoCondutorColumn;
    @FXML
    private TableColumn<ViagemEntity, String> comentarioDoPassageiroColumn;
    @FXML
    private TableColumn<ViagemEntity, String> comentarioDoCondutorColumn;
    @FXML
    private TableColumn<ViagemEntity, Date> dataInicioColumn;
    @FXML
    private TableColumn<ViagemEntity, Date> dataFimColumn;

    public void initialize(){
        preencherTabela();
    }

    @FXML
    public void preencherTabela() {
        List<ViagemEntity> viagens = Viagembll.listarViagensFinalizadas();
        carregarDados(viagens);
    }

    @FXML
    private void carregarDados(List<ViagemEntity> viagens){
        tableView.getItems().clear();

        // Adiciona os novos itens à tabela
        tableView.getItems().addAll(viagens);

        // Configura os valores das colunas para exibir os dados corretos das viagens
        passageiroColumn.setCellValueFactory(new PropertyValueFactory<>("idViagem"));
        passageiroColumn.setCellValueFactory(cellData -> {
            int idViagem = cellData.getValue().getIdViagem();
            int idPassageiro = Viagembll.getViagem(idViagem).getIdPassageiro();
            String nomePassageiro = Passageirobll.listarWithId(idPassageiro).getNome();
            return new SimpleStringProperty(nomePassageiro);
        });
        condutorColumn.setCellValueFactory(new PropertyValueFactory<>("idVeiculo"));
        condutorColumn.setCellValueFactory(cellData -> {
            int idVeiculo = cellData.getValue().getIdVeiculo();
            String nomeCondutor = Condutorbll.getWithId(Veiculobll.getWithId(idVeiculo).getIdCondutor()).getNome();
            return new SimpleStringProperty(nomeCondutor);
        });
        avaliacaoDoCondutorColumn.setCellValueFactory(cellData -> {
            int idViagem = cellData.getValue().getIdViagem();
            String avaliacao = Feedbackbll.listarFeedbackDoCondutor(idViagem);
            if (avaliacao == null) {
                avaliacao = "Sem feedback";
            }
            return new SimpleStringProperty(avaliacao);
        });
        avaliacaoDoPassageiroColumn.setCellValueFactory(cellData -> {
            int idViagem = cellData.getValue().getIdViagem();
            String avaliacao = Feedbackbll.listarFeedbackAoCondutor(idViagem);
            if (avaliacao == null) {
                avaliacao = "Sem feedback";
            }
            return new SimpleStringProperty(avaliacao);
        });
        comentarioDoCondutorColumn.setCellValueFactory(cellData -> {
            int idViagem = cellData.getValue().getIdViagem();
            String descricao = Feedbackbll.listarFeedbackDoCondutor(idViagem);
            return new SimpleStringProperty(descricao);
        });
        comentarioDoPassageiroColumn.setCellValueFactory(cellData -> {
            int idViagem = cellData.getValue().getIdViagem();
            String descricao = Feedbackbll.listarFeedbackDoCondutor(idViagem);
            return new SimpleStringProperty(descricao);
        });
        dataFimColumn.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        dataInicioColumn.setCellValueFactory(new PropertyValueFactory<>("dataIni"));
    }
    @FXML
    public void pesquisar() {
        if (datePickerInicio.getValue() == null || datePickerFim.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione as datas de início e fim da pesquisa.");
            alert.showAndWait();
        } else {
            // As datas foram selecionadas, prossiga com a pesquisa
            Date dateInicio = Date.from(datePickerInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date dateFim = Date.from(datePickerFim.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            carregarDados(Viagembll.listarViagensFinalizadasPorData(dateInicio,dateFim));
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
    public void setGerirStaffButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gerir-staff.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            GerirStaffController controller= fxmlLoader.getController();
            controller.carregarDados();
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Gerir Staff");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
