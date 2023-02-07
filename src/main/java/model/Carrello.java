package model;

public class Carrello {
    private int id;
    private int id_utente;
    private double totale;

    public Carrello(int id, int id_utente, double totale) {
        this.id = id;
        this.id_utente = id_utente;
        this.totale = totale;
    }

    //Costruttore per registrazione
    public Carrello(int id_utente, double totale) {
        this.id_utente = id_utente;
        this.totale = totale;
    }
    public Carrello() {

    }
    public int getIdCarrello() {
        return id;
    }

    public void setIdCarrello(int id) {
        this.id= id;
    }

    public int getIdUtente() {
        return id_utente;
    }

    public void setIdUtente(int id_utente) {
        this.id_utente = id_utente;
    }

    public double getTotale() {
        return totale;
    }
    public void setTotale(double totale) {
        this.totale = totale;
    }

}