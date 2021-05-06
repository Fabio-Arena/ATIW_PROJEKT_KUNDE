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
    private Button button_exportieren;

    @FXML
    void Kundendaten(ActionEvent event) throws IOException {
        App.setRoot("bestehenderkunde");
    }

    @FXML
    void Neukunderegistrieren(ActionEvent event) throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    void CSV(ActionEvent event) {

        ODB db= new ODB("SUS_FS191_MASTER","m","oracle.s-atiw.de","1521","atiwora");
        db.connect();

        db.sqlToCSV();

        db.close();


    }





}
