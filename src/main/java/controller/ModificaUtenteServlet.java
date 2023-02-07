package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import model.*;
import model.dao.*;




@WebServlet("/ModificaUtenteServlet")
public class ModificaUtenteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        Utente utenteLoggato=(Utente) session.getAttribute("Utente");
       String newNome=req.getParameter("cambiaNome");
       String newCognome=req.getParameter("cambiaCognome");
       String newEmail=req.getParameter("cambiaEmail");
        String newPass=req.getParameter("cambiaPass");
       int idUtente=Integer.parseInt(req.getParameter("idUtente"));
       Utente utenteModificato=new Utente(idUtente,newNome,newCognome,newEmail,newPass);
        UtenteDAO utenteDAO=new UtenteDAO();
        utenteDAO.updateUtente(idUtente,utenteModificato);


        if(utenteLoggato.getId()==1){
       resp.sendRedirect("MostraPannelloAdmin");}
        else {
            session.setAttribute("Utente",utenteModificato);
            resp.sendRedirect("index.jsp");}
    }
}
