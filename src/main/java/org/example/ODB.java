package org.example;

import oracle.jdbc.pool.OracleDataSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class ODB {
    private String user;
    private String passwd;
    private String host;
    private String port;
    private String service;
    private String connectionString;
    Connection con;

    public ODB(String user, String passwd, String host, String port, String service) {
        this.user = user;
        this.passwd = passwd;
        this.host = host;
        this.port = port;
        this.service = service;
        this.connectionString ="jdbc:oracle:thin:@(DESCRIPTION="
                + "(ADDRESS=(PROTOCOL=tcp)(HOST=" + host + ")(PORT=" + port + "))"
                + "(CONNECT_DATA=(SERVICE_NAME=" + service + ")))";
        con=null;
    }

    public void connect(){
        try{
            OracleDataSource dataSource = new OracleDataSource();

            dataSource.setURL(connectionString);
            dataSource.setUser(user);
            dataSource.setPassword(passwd);

            con = dataSource.getConnection();

            System.out.println("Verbindung steht!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getList(String sql){
        String ausgabe="";
        try {
            Statement befehl = con.createStatement();

            ResultSet ergebnisse = befehl.executeQuery(sql);

            while(ergebnisse.next()) {
                String name = ergebnisse.getString("name");
                String vorname = ergebnisse.getString("vorname");
                String geburtstag = ergebnisse.getString("geburtstag");
                String geschlecht = ergebnisse.getString("geschlecht");
                String stadt = ergebnisse.getString("stadt");
                String strasse = ergebnisse.getString("strasse");
                //int pnr = ergebnisse.getInt("personal_nr");


                ausgabe += "" + name + ", " + vorname + ", " + geburtstag + ", " + geschlecht + ", " + stadt + ", " + strasse + "\n";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ausgabe;
    }

    public void createTable(String name){
        Statement befehl = null;
        try {
            befehl = con.createStatement();

            //befehl.execute("DROP TABLE "+ name);
            befehl.execute("CREATE TABLE "+ name + " ( name varchar2(50), vorname varchar2(50))");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertData(String name, String vorname, String geburtstag, String geschlecht, String stadt,String strasse){
        Statement befehl = null;
        try {
            befehl = con.createStatement();

            befehl.execute("INSERT INTO Kunde VALUES ('"+ name + "', '" + vorname + "', '"+geburtstag+"', '"+geschlecht+"', '"+stadt+"', '"+strasse +"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteData(int kundenid){

    }

    public void updateData(String name, String vorname, String geburtstag, String geschlecht, String stadt,String strasse){

    }

    public void close(){
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void sqlToCSV(){
        try (Connection connection = DriverManager.getConnection(connectionString, user, passwd)) {
            String sql = "SELECT * FROM Kunde";

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Kunde.csv"));

            // write header line containing column names
            fileWriter.write("Name,Vorname,Geburtstag,Geschlecht,Stadt,Strasse");
            while(result.next()){
                String name=result.getString("name");
                String vorname=result.getString("vorname");
                String geburtstag=result.getString("geburtstag");
                String geschlecht=result.getString("geschlecht");
                String stadt=result.getString("stadt");
                String strasse=result.getString("strasse");

                String line = String.format("%s,%s,%s,%s,%s,%s",
                        name, vorname, geburtstag, geschlecht, stadt,strasse);
                fileWriter.newLine();
                fileWriter.write(line);
            }
            statement.close();
            fileWriter.close();
        }catch (SQLException e){
            System.out.println("Datenbank fehler");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("File IO fehler");
        }
    }
}
