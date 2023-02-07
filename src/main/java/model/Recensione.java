package model;

/**
 * La classe modella il concetto di Recensione
 * @author Salvatore Sautariello
 */

public class Recensione {
    private int idRecensione;
    private int valutazione;
    private String testo;
    private int id_utente;
    private int id_prodotto;

    public Recensione(int idRecensione,int valutazione,String testo,int id_utente,int id_prodotto){
        this.idRecensione=idRecensione;
        this.valutazione=valutazione;
        this.testo = testo;
        this.id_utente = id_utente;
        this.id_prodotto = id_prodotto;
    }

    public Recensione(int valutazione,String testo,int id_utente,int id_prodotto){
        this.valutazione=valutazione;
        this.testo=testo;
        this.id_utente = id_utente;
        this.id_prodotto = id_prodotto;
    }
    public int getIdUtente() {
        return id_utente;
    }

    public void setIdUtente(int id_utente) {
        this.id_utente = id_utente;
    }

    public int getIdProdotto() {
        return id_prodotto;
    }

    public void setIdProdotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public Recensione() {
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}