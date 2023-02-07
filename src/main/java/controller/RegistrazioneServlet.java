package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.*;
import model.dao.*;

import java.io.IOException;

@WebServlet(name = "RegistrazioneServlet", value = "/registrazione")
public class RegistrazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Parametri presi dal form
        String email = request.getParameter("email"), nome = request.getParameter("nome"),
        cognome = request.getParameter("cognome"), username = request.getParameter("username"),
                pass = request.getParameter("password");


        //Verifiche

        UtenteDAO utenteDAO = new UtenteDAO();
        CarrelloDAO carrelloDAO = new CarrelloDAO();

        if(utenteDAO.getUtenteByEmail(email)!=null){
            String errore = "Email già utilizzata!";
            request.setAttribute("msg", errore);
            request.getRequestDispatcher("/WEB-INF/results/registrazione.jsp").forward(
                    request, response);
            return;
        }

        Utente u = new Utente(nome,cognome,email,pass);
        utenteDAO.addUtente(u);
        Carrello carrello = new Carrello(u.getId(),0);
        carrelloDAO.doSave(carrello);

        request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(
                request, response);

    }
}
