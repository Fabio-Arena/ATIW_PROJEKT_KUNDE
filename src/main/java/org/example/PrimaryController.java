package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button neukunde_registrieren;

    @FXML
    private Button kundendaten_lesen;

    @FXML
    void Kundendaten(ActionEvent event) throws IOException {
        App.setRoot("bestehenderkunde");
    }

    @FXML
    void Neukunderegistrieren(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }





}
