package model;
import java.util.ArrayList;

public class Carrello {
    private int id;
    private int idUtente;
    private ArrayList<Prodotto> listaProdotti;
    private double prezzoTotale;

    public void aggiungiProdotto(Prodotto prodotto) {
        this.listaProdotti.add(prodotto);
    }

    public void rimuoviProdotto(Prodotto prodotto) {
        this.listaProdotti.remove(prodotto);
    }

    public void modificaQuantità(Prodotto prodotto, int quantità) {
        int index = this.listaProdotti.indexOf(prodotto);
        this.listaProdotti.get(index).setQuantità(quantità);
    }

    public double totale() {
        double totale = 0;
        for (Prodotto prodotto : this.listaProdotti) {
            totale += prodotto.getPrezzo() * prodotto.getQuantità();
        }
        this.prezzoTotale = totale;
        return this.prezzoTotale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public ArrayList<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    public void setListaProdotti(ArrayList<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }
}