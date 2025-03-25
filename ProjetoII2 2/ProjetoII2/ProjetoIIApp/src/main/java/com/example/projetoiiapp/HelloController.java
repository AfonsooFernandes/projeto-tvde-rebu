package com.example.projetoiiapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    @FXML
    private Button conduzirButton;
    @FXML
    private Button viajarButton;
    @FXML
    private Button iniciarSessaoButton;


    @FXML
    protected void setConduzirButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registar-condutor.fxml"));
            Parent root = fxmlLoader.load();

            // Define a cena
            Scene scene = new Scene(root, 600, 400);

            // Obtém a referência do controlador da cena carregada
            RegistarCondutorController controller = fxmlLoader.getController();



            // Obtém o palco e define a cena
            Stage stage = (Stage) conduzirButton.getScene().getWindow();
            stage.setTitle("Registo de Condutor");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    protected void setViajarButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registar-passageiro.fxml"));
            Parent root = fxmlLoader.load();

            // Define a cena
            Scene scene = new Scene(root, 600, 400);

            // Obtém a referência do controlador da cena carregada
            RegistarPassageiroController controller = fxmlLoader.getController();



            // Obtém o palco e define a cena
            Stage stage = (Stage) viajarButton.getScene().getWindow();
            stage.setTitle("Registo de Passageiro");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void setIniciarSessaoButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("iniciar-sessao.fxml"));
            Parent root = fxmlLoader.load();

            // Define a cena
            Scene scene = new Scene(root, 600, 400);

            // Obtém a referência do controlador da cena carregada
            IniciarSessaoController controller = fxmlLoader.getController();



            // Obtém o palco e define a cena
            Stage stage = (Stage) iniciarSessaoButton.getScene().getWindow();
            stage.setTitle("Iniciar Sessão");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

