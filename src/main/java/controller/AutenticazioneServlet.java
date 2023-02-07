package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

import model.*;
import model.dao.*;


@WebServlet(name = "AutenticazioneServlet", value = "/login")
public class AutenticazioneServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("email");
        String pass = request.getParameter("password");
        UtenteDAO utenteDAO =  new UtenteDAO();
        CarrelloDAO carrelloDAO = new CarrelloDAO();

        Autenticazione a = new Autenticazione(utenteDAO);

        Utente validate  = a.verifyLogin(username, pass);


        if(validate != null) {
            request.getSession().setAttribute("Utente", validate);
            Carrello carrello = carrelloDAO.getCarrelloByIdUtente(validate.getId());
            if(carrello==null) {
                carrello=new Carrello(validate.getId(),0 );
                carrelloDAO.doSave(carrello);
            }
            request.getSession().setAttribute("Carrello",carrello);
            request.getRequestDispatcher("index.jsp").forward(
                    request, response);
        }else {
            String errore = "Credenziali errate!";
            request.setAttribute("msg", errore);
            request.getRequestDispatcher("/WEB-INF/results/login.jsp").forward(
                    request, response);
        }

    }
}
