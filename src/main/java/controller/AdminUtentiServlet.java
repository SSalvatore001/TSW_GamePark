package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import model.dao.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe permette di settare come attributo nella request la lista di tutti gli utenti
 * presenti nel database, tramite il facadeDAO.
 * Una volta settata la lista degli utenti,
 * richiede il dispatcher alla pagina adminGestioneUtenti.
 * @author Francesco Di Domenico
 */

@WebServlet("/AdminUtentiServlet")
public class AdminUtentiServlet extends HttpServlet {

    /**
     * Il metodo permette di ottenere dal database tramite il facadeDAO
     * la lista degli utenti, setta la lista come parametro nella request
     * e rimanda alla pagina dell admin per gestire gli utenti tramite parametro req.
     * @param req : oggetto di richiesta HTTP
     * @param resp : oggetto di risposta HTTP
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UtenteDAO utenteDAO=new UtenteDAO();
        List<Utente> listutenti= utenteDAO.getAllUtenti();
        req.setAttribute("listutenti",listutenti);
        req.getRequestDispatcher("/WEB-INF/results/adminGestioneUtenti.jsp").forward(
                req, resp);

    }
}
