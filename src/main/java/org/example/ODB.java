package org.example;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                Date geburtstag = ergebnisse.getDate("geburtstag");
                String geschlecht = ergebnisse.getString("geschlecht");
                String stadt = ergebnisse.getString("stadt");
                String strasse = ergebnisse.getString("strasse");
                //int pnr = ergebnisse.getInt("personal_nr");


                ausgabe += "" + name + ", " + vorname + ", " + geburtstag.toString() + ", " + geschlecht + ", " + stadt + ", " + strasse + "\n";
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

    public void insertData(String name, String vorname, Date geburtstag, String geschlecht, String stadt,String strasse){
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

    public void updateData(String name, String vorname, Date geburtstag, String geschlecht, String stadt,String strasse){

    }

    public void close(){
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
