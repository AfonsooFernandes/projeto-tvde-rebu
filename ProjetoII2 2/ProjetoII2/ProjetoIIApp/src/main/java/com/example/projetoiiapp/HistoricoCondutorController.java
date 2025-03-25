package com.example.projetoiiapp;

import baseDados.bll.*;

import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.ViagemEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.beans.property.SimpleObjectProperty;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class HistoricoCondutorController {
    private CondutorEntity condutor;
    @FXML
    private Button voltarButton;
    @FXML
    private Button conduzirButton;
    @FXML
    private DatePicker datePickerInicio;
    @FXML
    private DatePicker datePickerFim;
    @FXML
    private TableView<ViagemEntity> tableView;
    @FXML
    private Button ajudaButton;

    @FXML
    private TableColumn<ViagemEntity, String> passageiroColumn;
    @FXML
    private TableColumn<ViagemEntity, String> origemColumn;
    @FXML
    private TableColumn<ViagemEntity, String> destinoColumn;
    @FXML
    private TableColumn<ViagemEntity, BigDecimal> lucroColumn;
    @FXML
    private TableColumn<ViagemEntity, String> estadoViagemColumn;
    @FXML
    private TableColumn<ViagemEntity, String> avaliacaoAoPassageiroColumn;
    @FXML
    private TableColumn<ViagemEntity, String> avaliacaoDoPassageiroColumn;
    @FXML
    private TableColumn<ViagemEntity, Date> dataIniColumn;
    @FXML
    private TableColumn<ViagemEntity, Date> dataFimColumn;

    @FXML
    private TableColumn<ViagemEntity, String> marcaColumn;
    @FXML
    private TableColumn<ViagemEntity, String> modeloColumn;
    @FXML
    private TableColumn<ViagemEntity, String> matriculaColumn;

    public void preencherTabela() {
        List<ViagemEntity> viagens = Viagembll.listarViagensCondutor(condutor.getIdCondutor());
        carregarDados(viagens);
    }

    @FXML
    private void setAjudaButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ajuda-condutor.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            AjudaCondutorController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            Stage stage = (Stage) ajudaButton.getScene().getWindow();
            stage.setTitle("Rebu - Ajuda");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-condutor.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            MenuCondutorController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void conduzir() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("escolher-veiculo.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            EscolherVeiculoController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            Stage stage = (Stage) conduzirButton.getScene().getWindow();
            stage.setTitle("Escolher Veiculo");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void registarVeiculo(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registar-veiculo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            RegistarVeiculoController controller = fxmlLoader.getController();
            controller.setCondutor(condutor); // Passa o CondutorEntity para o construtor do EscolherVeiculoController
            controller.preencherCombobox();
            Stage stage = (Stage) conduzirButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            carregarDados(Viagembll.listarViagensCondutorPorData(condutor.getIdCondutor(), dateInicio, dateFim));
        }
    }


    @FXML
    private void carregarDados(List<ViagemEntity> viagens){
        // Limpa os itens existentes na tabela
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
        origemColumn.setCellValueFactory(new PropertyValueFactory<>("origem"));
        destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));
        lucroColumn.setCellValueFactory(cellData -> {
            int idViagem = cellData.getValue().getIdViagem();
            BigDecimal lucro = Viagembll.getViagem(idViagem).getValor().multiply(Viagembll.getViagem(idViagem).getPercent_condutor());
            return new SimpleObjectProperty<>(lucro);
        });
        dataFimColumn.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        dataIniColumn.setCellValueFactory(new PropertyValueFactory<>("dataIni"));
        estadoViagemColumn.setCellValueFactory(new PropertyValueFactory<>("idEstadoViagem"));
        estadoViagemColumn.setCellValueFactory(cellData -> {
            int idEstadoViagem = cellData.getValue().getIdEstadoViagem();
            String estadoViagem = EstadoViagembll.getEstado(idEstadoViagem);
            return new SimpleStringProperty(estadoViagem);
        });
        avaliacaoAoPassageiroColumn.setCellValueFactory(cellData -> {
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

        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("idVeiculo"));
        marcaColumn.setCellValueFactory(cellData -> {
            int idVeiculo = cellData.getValue().getIdVeiculo();
            return new SimpleStringProperty(Veiculobll.getWithId(idVeiculo).getMarca());
        });
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("idVeiculo"));
        modeloColumn.setCellValueFactory(cellData -> {
            int idVeiculo = cellData.getValue().getIdVeiculo();
            return new SimpleStringProperty(Veiculobll.getWithId(idVeiculo).getModelo());
        });
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("idVeiculo"));
        matriculaColumn.setCellValueFactory(cellData -> {
            int idVeiculo = cellData.getValue().getIdVeiculo();
            return new SimpleStringProperty(Veiculobll.getWithId(idVeiculo).getMatricula());
        });
    }

    protected void setCondutor(CondutorEntity condutor){
        this.condutor = condutor;
    }

}
