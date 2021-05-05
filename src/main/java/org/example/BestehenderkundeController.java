package org.example;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BestehenderkundeController implements Initializable {



    @FXML
    private TableView kundendaten = new TableView();

    @FXML
    private TableColumn spalte_name = new TableColumn("Name");


    @FXML
    private TableColumn spalte_vorname = new TableColumn("Vorname");

    @FXML
    private TableColumn spalte_geburtstag = new TableColumn("Geburtstag");

    @FXML
    private TableColumn spalte_geschlecht = new TableColumn("Geschlecht");

    @FXML
    private TableColumn spalte_stadt = new TableColumn<>("Stadt");

    @FXML
    private TableColumn spalte_strasse = new TableColumn("Strasse");

    private ODB db = new ODB("SUS_FS191_MASTER","m","oracle.s-atiw.de","1521","atiwora");

    private  ObservableList<Kunde> data;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        kundendaten.getColumns().clear();

        spalte_name.setCellValueFactory(new PropertyValueFactory<Kunde, String>("name"));
        spalte_vorname.setCellValueFactory(new PropertyValueFactory<Kunde, String>("vorname"));
        spalte_geburtstag.setCellValueFactory(new PropertyValueFactory<Kunde, String>("geburtstag"));
        spalte_geschlecht.setCellValueFactory(new PropertyValueFactory<Kunde, String>("geschlecht"));
        spalte_stadt.setCellValueFactory(new PropertyValueFactory<Kunde, String>("stadt"));
        spalte_strasse.setCellValueFactory(new PropertyValueFactory<Kunde, String>("strasse"));
        kundendaten.getColumns().addAll(spalte_name, spalte_vorname, spalte_geburtstag, spalte_geschlecht, spalte_stadt, spalte_strasse);


        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION="
                + "(ADDRESS=(PROTOCOL=tcp)(HOST=" + "oracle.s-atiw.de" + ")(PORT=" + "1521" + "))"
                + "(CONNECT_DATA=(SERVICE_NAME=" + "atiwora" + ")))", "SUS_FS191_MASTER", "m")) {
            String sql = "SELECT * FROM Kunde";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);




            while (result.next()) {


                data = FXCollections.observableArrayList(new Kunde(result.getString("name"),result.getString("vorname"),result.getString("geburtstag"),result.getString("geschlecht"),result.getString("stadt"),result.getString("strasse")));


                kundendaten.getItems().add(data);
                //kundendaten.setItems(data);


            }


            statement.close();
        } catch (SQLException e) {
            System.out.println("Datenbank fehler");
            e.printStackTrace();

        }


    }
}
