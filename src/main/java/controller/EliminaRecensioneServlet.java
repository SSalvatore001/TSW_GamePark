package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import model.dao.*;

import java.io.IOException;
@WebServlet("/EliminaRecensione")
public class EliminaRecensioneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        Utente utente = (Utente) session.getAttribute("Utente");
        int idRecensione= Integer.parseInt(req.getParameter("idRecensione"));
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        recensioneDAO.deleteRecensione(idRecensione);//Elimina recensione dal DB in base al tipo di classe passata e idRecensione.

        if(utente.getId()==1){resp.sendRedirect("MostraPannelloAdmin");}//reindirizza alla pagina pannello admin
        if(utente.getId()>=2){req.getRequestDispatcher("/WEB-INF/results/paginaUtente.jsp");}
    }
}
