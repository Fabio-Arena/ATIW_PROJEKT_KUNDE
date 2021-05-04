package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SecondaryController {


    @FXML
    private ImageView Logo;


    @FXML
    private TextField textVorname;

    @FXML
    private TextField textNachname;

    @FXML
    private DatePicker datepickerDatum;

    @FXML
    private RadioButton radiobuttonM;

    @FXML
    private RadioButton radiobuttonW;

    @FXML
    private TextField textStadt;

    @FXML
    private TextField textStraße;

    @FXML
    private Button buttonSpeichern;

    @FXML
    void M(ActionEvent event) {

        if (radiobuttonM.isSelected() == true){
            radiobuttonW.setSelected(false);
        }
        else{
            radiobuttonW.setSelected(true);
        }

    }

    @FXML
    void W(ActionEvent event) {

        if (radiobuttonW.isSelected() == true){
            radiobuttonM.setSelected(false);
        }
        else{
            radiobuttonM.setSelected(true);
        }

    }

    @FXML
    void Speichern(ActionEvent event) {

        String geschl;
        if (radiobuttonM.isSelected() == true){
            geschl = "M";
        }
        else{
            geschl = "W";
        }

        ODB db= new ODB("SUS_FS191_MASTER","m","oracle.s-atiw.de","1521","atiwora");
        db.connect();




        db.insertData(textNachname.getText(), textVorname.getText(), datepickerDatum.getValue().toString(), geschl , textStadt.getText(), textStraße.getText());





        db.close();

    }



}

