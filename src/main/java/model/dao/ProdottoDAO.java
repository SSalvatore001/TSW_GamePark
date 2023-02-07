package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class ProdottoDAO {
    public void addProdotto(Prodotto prodotto) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement preparedStatement = con
                    .prepareStatement("insert into prodotto(nome,descrizione,prezzo,quantità) values (?, ?, ?, ?)");
            // Parameters start with 1
            preparedStatement.setString(1, prodotto.getNome());
            preparedStatement.setString(2, prodotto.getDescrizione());
            preparedStatement.setDouble(3, prodotto.getPrezzo());
            preparedStatement.setInt(4, prodotto.getQuantità());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProdotto(int id) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement preparedStatement = con
                    .prepareStatement("delete from prodotto where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProdotto(Prodotto prodotto) {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement preparedStatement = con
                    .prepareStatement("update prodotto set nome=?, descrizione=?, prezzo=?, quantità=?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, prodotto.getNome());
            preparedStatement.setString(2, prodotto.getDescrizione());
            preparedStatement.setDouble(3, prodotto.getPrezzo());
            preparedStatement.setInt(4, prodotto.getQuantità());
            preparedStatement.setInt(5, prodotto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List < Prodotto > getAllProdotti() {
        List<Prodotto> prodotti = new ArrayList<Prodotto>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con.
                    prepareStatement("select * from prodotto");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setId(rs.getInt("id"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setPrezzo(rs.getDouble("prezzo"));
                prodotto.setQuantità(rs.getInt("quantità"));
                prodotti.add(prodotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prodotti;
    }

    public Prodotto getProdottoById(int id) {
        Prodotto prodotto = new Prodotto();
        try(Connection con = ConPool.getConnection()){
            PreparedStatement preparedStatement = con.
                    prepareStatement("select * from prodotto where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                prodotto.setId(rs.getInt("id"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setPrezzo(rs.getDouble("prezzo"));
                prodotto.setQuantità(rs.getInt("quantità"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodotto;
    }

}

