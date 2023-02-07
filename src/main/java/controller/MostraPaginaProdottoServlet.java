package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import model.dao.*;
import model.*;



@WebServlet("/mostra-pagina-prodotto-servlet")
public class MostraPaginaProdottoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_prodotto = Integer.parseInt(request.getParameter("id-prodotto"));

        RecensioneDAO recensioneDAO = new RecensioneDAO();

        List<Recensione> lista_recensioni = recensioneDAO.getRecensioniByIdProdotto(id_prodotto);
        request.setAttribute("lista-recensioni", lista_recensioni);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/results/prodotto.jsp");
        requestDispatcher.forward(request, response);
    }
}
