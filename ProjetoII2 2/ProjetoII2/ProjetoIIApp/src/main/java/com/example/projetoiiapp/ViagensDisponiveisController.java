package com.example.projetoiiapp;

import baseDados.bll.*;
import baseDados.entity.CondutorEntity;
import baseDados.entity.PassageiroEntity;
import baseDados.entity.VeiculoEntity;
import baseDados.entity.ViagemEntity;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ViagensDisponiveisController {
    private CondutorEntity condutor;
    private VeiculoEntity veiculo;
    @FXML
    private Button aceitarButton;
    @FXML
    private Button voltarButton;
    @FXML
    private Button historicoButton;
    @FXML
    private Button ajudaButton;
    @FXML
    private TableView<ViagemEntity> tableView;

    @FXML
    private TableColumn<ViagemEntity, String> nomeColumn;
    @FXML
    private TableColumn<ViagemEntity, String> generoColumn;
    @FXML
    private TableColumn<ViagemEntity, String> nacionalidadeColumn;
    @FXML
    private TableColumn<ViagemEntity, BigDecimal> lucroColumn;
    @FXML
    private TableColumn<ViagemEntity, Integer> idadeColumn;
    @FXML
    private TableColumn<ViagemEntity, String> origemColumn;
    @FXML
    private TableColumn<ViagemEntity, String> destinoColumn;
    @FXML
    private TableColumn<ViagemEntity, String> distanciaColumn;
    @FXML
    private TableColumn<ViagemEntity, String> tipoVeiculoColumn;

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
    public void preencherTabela() {

        List<ViagemEntity> viagens = Viagembll.listarViagensPendentesTipoVeiculo(veiculo.getIdTipoVeiculo());
        // Preenche a tabela com os dados das viagens
        tableView.getItems().addAll(viagens);

        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("idPassageiro"));
        nomeColumn.setCellValueFactory(cellData -> {
            int idPassageiro = cellData.getValue().getIdPassageiro();
            String nomePassageiro = Passageirobll.listarWithId(idPassageiro).getNome();
            return new SimpleStringProperty(nomePassageiro);
        });
        nacionalidadeColumn.setCellValueFactory(new PropertyValueFactory<>("idPassageiro"));
        nacionalidadeColumn.setCellValueFactory(cellData -> {
            int idPassageiro = cellData.getValue().getIdPassageiro();
            String nacionalidade = Nacionalidadebll.getNacionalidade(Passageirobll.listarWithId(idPassageiro).getIdNacionalidade());
            return new SimpleStringProperty(nacionalidade);
        });
        idadeColumn.setCellValueFactory(new PropertyValueFactory<>("idPassageiro"));
        idadeColumn.setCellValueFactory(cellData -> {
            int idPassageiro = cellData.getValue().getIdPassageiro();
            int idade = Passageirobll.listarWithId(idPassageiro).getIdade();
            return new SimpleIntegerProperty(idade).asObject();
        });
        generoColumn.setCellValueFactory(new PropertyValueFactory<>("idPassageiro"));
        generoColumn.setCellValueFactory(cellData -> {
            int idPassageiro = cellData.getValue().getIdPassageiro();
            Character genero = Passageirobll.listarWithId(idPassageiro).getGenero();
            return new SimpleStringProperty(genero.toString());
        });
        distanciaColumn.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        origemColumn.setCellValueFactory(new PropertyValueFactory<>("origem"));
        destinoColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));
        lucroColumn.setCellValueFactory(new PropertyValueFactory<>("idViagem"));
        lucroColumn.setCellValueFactory(cellData -> {
            BigDecimal lucro = cellData.getValue().getValor().multiply(cellData.getValue().getPercent_condutor());
            return new SimpleObjectProperty<>(lucro);
        });
        tipoVeiculoColumn.setCellValueFactory(new PropertyValueFactory<>("idTipoVeiculo"));
        tipoVeiculoColumn.setCellValueFactory(cellData -> {
            int idTipoVeiculo = cellData.getValue().getIdTipoVeiculo();
            String TipoVeiculo = TipoVeiculobll.getTipoVeiculo(idTipoVeiculo);
            return new SimpleStringProperty(TipoVeiculo);
        });

    }

    @FXML
    private void BackButton(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("escolher-veiculo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            EscolherVeiculoController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            controller.preencherCombobox();
            Stage stage = (Stage) voltarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void setHistoricoButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("historico-condutor.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 1387, 663);
            HistoricoCondutorController controller = fxmlLoader.getController();
            controller.setCondutor(condutor);
            controller.preencherTabela();
            Stage stage = (Stage) historicoButton.getScene().getWindow();
            stage.setTitle("Histórico de viagens");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aceitar(){
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            ViagemEntity viagemSelecionada = tableView.getSelectionModel().getSelectedItem();

            viagemSelecionada.setIdVeiculo(veiculo.getIdVeiculo());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Viagem Aceite");
            alert.setHeaderText(null);
            alert.showAndWait();
            tableView.refresh();

            viagemSelecionada.setIdEstadoViagem(4);
            Viagembll.atualizar(viagemSelecionada);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("aguardar-resposta-passageiro.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                AguardarRespostaPassageiroController controller = fxmlLoader.getController();
                controller.setCondutor(condutor);
                controller.setPassageiro(Passageirobll.listarWithId(viagemSelecionada.getIdPassageiro()));
                controller.setVeiculo(veiculo);
                controller.setViagem(viagemSelecionada);
                Stage stage = (Stage) tableView.getScene().getWindow();
                stage.setTitle("Rebu - A sua viagem sempre à mão");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleção Necessária");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione uma viagem.");
            alert.showAndWait();
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
            Stage stage = (Stage) aceitarButton.getScene().getWindow();
            stage.setTitle("Rebu - A sua viagem sempre à mão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCondutor(CondutorEntity condutor) {
        this.condutor = condutor;
    }

    public void setVeiculo(VeiculoEntity veiculo) {
        this.veiculo = veiculo;
    }
}
