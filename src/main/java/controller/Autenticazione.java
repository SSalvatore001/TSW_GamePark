package controller;

import model.*;
import model.dao.UtenteDAO;

public class Autenticazione {
    UtenteDAO utenteDAO = new UtenteDAO();
    public Autenticazione(UtenteDAO utenteDAO) {
        this.utenteDAO = utenteDAO;

    }

    public Utente verifyLogin(String email, String pass){
        return utenteDAO.getUtenteByEmailPassword(email, pass);
    }
}
