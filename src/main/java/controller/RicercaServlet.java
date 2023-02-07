package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import model.dao.*;

@WebServlet("/search-servlet")
public class RicercaServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String researchText = request.getParameter("search-text");

        ProdottoDAO prodottoDAO = new ProdottoDAO();
        List<Prodotto> cardsList = prodottoDAO.getAllProdotti();
        List<Prodotto> matches = new ArrayList<>();

        for (Prodotto prodotto : cardsList){
            String formattedCardName = prodotto.getNome().toLowerCase().trim();
            String formattedResearchText = researchText.toLowerCase().trim();

            if(formattedCardName.contains(formattedResearchText))
                matches.add(prodotto);
        }

        if (matches.isEmpty()){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error/ricerca-senza-risultati.jsp");
            dispatcher.forward(request, response);
        }

        request.setAttribute("card-matches", matches);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/ricerca-risultati.jsp");
        dispatcher.forward(request, response);
    }

}
