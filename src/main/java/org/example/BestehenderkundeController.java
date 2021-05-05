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
import java.util.ResourceBundle;

public class BestehenderkundeController implements Initializable {



    @FXML
    private TableView kundendaten = new TableView();

    @FXML
    private TableColumn<String, String> spalte_name = new TableColumn<>("Name");


    @FXML
    private TableColumn<String, String> spalte_vorname = new TableColumn<>("Vorname");

    @FXML
    private TableColumn<String, String> spalte_geburtstag = new TableColumn<>("Geburtstag");

    @FXML
    private TableColumn<String, String> spalte_geschlecht = new TableColumn<>("Geschlecht");

    @FXML
    private TableColumn<String, String> spalte_stadt = new TableColumn<>("Stadt");

    @FXML
    private TableColumn<String, String> spalte_strasse = new TableColumn<>("Strasse");

    private ODB db = new ODB("SUS_FS191_MASTER","m","oracle.s-atiw.de","1521","atiwora");

    public ObservableList<String> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        kundendaten.getColumns().clear();

        spalte_name.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        spalte_vorname.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        spalte_geburtstag.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        spalte_geschlecht.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        spalte_stadt.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        spalte_strasse.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        kundendaten.getColumns().addAll(spalte_name, spalte_vorname, spalte_geburtstag, spalte_geschlecht, spalte_stadt, spalte_strasse);


        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@(DESCRIPTION="
                + "(ADDRESS=(PROTOCOL=tcp)(HOST=" + "oracle.s-atiw.de" + ")(PORT=" + "1521" + "))"
                + "(CONNECT_DATA=(SERVICE_NAME=" + "atiwora" + ")))", "SUS_FS191_MASTER", "m")) {
            String sql = "SELECT * FROM Kunde";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);
            ArrayList<String> name=new ArrayList<>();
            ArrayList<String> vorname=new ArrayList<>();
            ArrayList<String> geburts=new ArrayList<>();
            ArrayList<String> gesch=new ArrayList<>();
            ArrayList<String> stadt =new ArrayList<>();
            ArrayList<String> strasse=new ArrayList<>();

            while (result.next()) {

                name.add(result.getString("name"));
                vorname.add(result.getString("vorname"));
                geburts.add(result.getString("geburtstag"));
                gesch.add(result.getString("geschlecht"));
                stadt.add(result.getString("stadt"));
                strasse.add(result.getString("strasse"));
                data = FXCollections.observableArrayList(result.getString("name"),result.getString("vorname"),result.getString("geburtstag"),result.getString("geschlecht"),result.getString("stadt"),result.getString("strasse"));


                kundendaten.getItems().add(data.get(0));
                kundendaten.getItems().add(data.get(1));
                kundendaten.getItems().add(data.get(2));
                kundendaten.getItems().add(data.get(3));
                kundendaten.getItems().add(data.get(4));
                kundendaten.getItems().add(data.get(5));

            }
            for(int i=0;i<name.size();i++){
                //data = FXCollections.observableArrayList(name.get(i),vorname.get(i),geburts.get(i),gesch.get(i),stadt.get(i),strasse.get(i));
                //kundendaten.setItems(data);
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Datenbank fehler");
            e.printStackTrace();

        }


    }
}
