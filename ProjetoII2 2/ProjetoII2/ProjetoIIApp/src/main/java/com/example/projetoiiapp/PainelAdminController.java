package com.example.projetoiiapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PainelAdminController {

    @FXML
    private Button gerirStaffButton;
    @FXML
    private Button feedBacksViagensButton;

    @FXML
    public void setGerirStaffButton(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gerir-staff.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            GerirStaffController controller= fxmlLoader.getController();
            controller.carregarDados();
            Stage stage = (Stage) gerirStaffButton.getScene().getWindow();
            stage.setTitle("Gerir Staff");
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
            Stage stage = (Stage) feedBacksViagensButton.getScene().getWindow();
            stage.setTitle("FeedBacks Viagens");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
