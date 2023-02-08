package model.dao;
import model.*;

import java.sql.*;
import java.util.*;

public class CarrelloDAO {
    private static final String INSERT_CARRELLO_QUERY = "INSERT INTO Carrello (idutente,totale) VALUES (?, ?)";
    private static final String SELECT_CARRELLO_BY_ID_QUERY = "SELECT * FROM Carrello WHERE id = ?";
    private static final String SELECT_CARRELLO_BY_ID_UTENTE_QUERY = "SELECT * FROM Carrello WHERE idUtente = ?";
    private static final String SELECT_ALL_CARRELLI_QUERY = "SELECT * FROM Carrello";
    private static final String UPDATE_CARRELLO_QUERY = "UPDATE Carrello SET id=?, idUtente=? WHERE id=? ";
    private static final String DELETE_CARRELLO_QUERY = "DELETE FROM Carrello WHERE id = ?";
    private static final String INSERT_INTO_CARRELLOCONTIENEPRODOTTO_QUERY = "INSERT INTO CARRELLOCONTIENEPRODOTTO (id, idProdotto) VALUES (?, ?)";
    private static final String DELETE_FROM_CARRELLOCONTIENEPRODOTTO_QUERY = "DELETE FROM CARRELLOCONTIENEPRODOTTO WHERE id = ? and idProdotto = ?";
    private static final String DELETE_PRODOTTI_FROM_CARRELLO_QUERY = "DELETE FROM CARRELLOCONTIENEPRODOTTO WHERE id = ?";
    private static final String SELECT_TOTALE_BY_ID_CARRELLO_QUERY = "SELECT SUM(Offerta.prezzo) as prezzo FROM CARRELLOCONTIENEPRODOTTO JOIN Prodotto ON CARRELLOCONTIENEPRODOTTO.idProdotto = Prodotto.id WHERE CARRELLOCONTIENEPRODOTTO.id = ?;";
    private static final String UPDATE_TOTALE_CARRELLO_QUERY = "UPDATE Carrello SET totale = ? WHERE id = ?";

    public void doSave(Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_CARRELLO_QUERY);
            statement.setInt(1, carrello.getIdUtente());
            statement.setDouble(2, carrello.getTotale());
            if (statement.executeUpdate() != 1) {
                throw new RuntimeException("Errore durante l'inserimento del carrello");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Carrello doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_CARRELLO_BY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Carrello carrello = new Carrello();
                carrello.setIdCarrello(resultSet.getInt(1));
                carrello.setIdUtente(resultSet.getInt(2));
                carrello.setTotale(resultSet.getDouble(3));
                return carrello;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Carrello getCarrelloByIdUtente(int idUtente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_CARRELLO_BY_ID_UTENTE_QUERY);
            statement.setInt(1, idUtente);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Carrello carrello = new Carrello();
                carrello.setIdCarrello(resultSet.getInt(1));
                carrello.setIdUtente(resultSet.getInt(2));
                carrello.setTotale(resultSet.getInt(3));
                return carrello;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List < Carrello > doRetrieveAll() {
        List < Carrello > carrelli = new ArrayList < > ();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_ALL_CARRELLI_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idUtente = resultSet.getInt("idUtente");
                double totale = resultSet.getDouble("totale");
                carrelli.add(new Carrello(id, idUtente, totale));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carrelli;
    }

    public void doUpdate(int id, Carrello carrello) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(UPDATE_CARRELLO_QUERY);
            statement.setInt(1, carrello.getIdUtente());
            statement.setInt(2, id);
            statement.setDouble(3, carrello.getTotale());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aggiungiProdotto(Prodotto prodotto, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(INSERT_INTO_CARRELLOCONTIENEPRODOTTO_QUERY);
            statement.setInt(1, id);
            statement.setInt(2, prodotto.getId());
            statement.executeUpdate();
            double totale = calcolaTotale(id);
            statement = con.prepareStatement(UPDATE_TOTALE_CARRELLO_QUERY);
            statement.setDouble(1, totale);
            statement.setInt(2, id);
            statement.executeUpdate();
            Carrello carrello = new Carrello();
            carrello.setIdCarrello(id);
            carrello.setTotale(totale);
            doUpdate(id, carrello);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rimuoviProdotto(Prodotto prodotto, int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_FROM_CARRELLOCONTIENEPRODOTTO_QUERY);
            statement.setInt(1, id);
            statement.setInt(2, prodotto.getId());
            statement.executeUpdate();
            double totale = calcolaTotale(id);
            statement = con.prepareStatement(UPDATE_TOTALE_CARRELLO_QUERY);
            statement.setDouble(1, totale);
            statement.setInt(2, id);
            statement.executeUpdate();
            Carrello carrello = new Carrello();
            carrello.setIdCarrello(id);
            carrello.setTotale(totale);
            doUpdate(id, carrello);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calcolaTotale(int id) {
        double totale = 0.0;
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(SELECT_TOTALE_BY_ID_CARRELLO_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                totale += resultSet.getDouble("prezzo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totale;
    }

    public void svuotaCarrello(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_PRODOTTI_FROM_CARRELLO_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement = con.prepareStatement(UPDATE_TOTALE_CARRELLO_QUERY);
            statement.setDouble(1, 0.0);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement(DELETE_CARRELLO_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}