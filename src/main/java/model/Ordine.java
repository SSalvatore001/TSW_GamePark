package model;

import java.sql.Date;

public class Ordine {
    private int id;
    private Date data;
    private double importoTotale;
    private int idUtente;

    public Ordine() {

    }

    public Ordine(int id, Date data, double importoTotale, int idUtente) {
        this.id = id;
        this.data = data;
        this.importoTotale = importoTotale;
        this.idUtente = idUtente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getImportoTotale() {
        return importoTotale;
    }

    public void setImportoTotale(double importoTotale) {
        this.importoTotale = importoTotale;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
}
