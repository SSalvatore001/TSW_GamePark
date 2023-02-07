package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import model.dao.*;

import java.io.IOException;

@WebServlet("/AggiungiRecensione")
public class AggiungiRecensione extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RecensioneDAO recensioneDAO=new RecensioneDAO();
        String testo=req.getParameter("text");
        String strValutazione=req.getParameter("rate");
        int idUtente= Integer.parseInt(req.getParameter("idUtente"));
        int idOrdine= Integer.parseInt(req.getParameter("idOrdine"));
        int valutazione =Integer.parseInt(strValutazione);
        Recensione r=new Recensione(valutazione,testo,idUtente,idOrdine);
        recensioneDAO.addRecensione(r);
        resp.sendRedirect("index.jsp");
    }

}