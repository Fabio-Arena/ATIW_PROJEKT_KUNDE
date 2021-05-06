package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Kunde {
    private SimpleStringProperty name;
    private String vorname;
    private String geb;
    private String gesch;
    private String stadt;
    private String strasse;

    public Kunde(String name, String vorname,String geb, String gesch,String stadt,String strasse){
        this.name=new SimpleStringProperty(name);
        this.vorname=vorname;
        this.geb=geb;
        this.gesch=gesch;
        this.stadt=stadt;
        this.strasse=strasse;
    }

    public String getName() {
        return name.getName();
    }

    public StringProperty nameProperty(){
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getGeb() {
        return geb;
    }

    public String getGesch() {
        return gesch;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setGeb(String geb) {
        this.geb = geb;
    }

    public void setGesch(String gesch) {
        this.gesch = gesch;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public String getStrasse() {
        return strasse;
    }
}
