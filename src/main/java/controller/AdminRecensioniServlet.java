package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import model.dao.RecensioneDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AdminRecensioniServlet")
public class AdminRecensioniServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        int idUtente = Integer.parseInt(req.getParameter("idUtente"));
        List<Recensione> listrecensioni= recensioneDAO.getRecensioneByIdUtente(idUtente);
        req.setAttribute("listrecensioni",listrecensioni);
        req.getRequestDispatcher("/WEB-INF/results/adminGestioneRecensioni.jsp").forward(
                req, resp);
    }
}
