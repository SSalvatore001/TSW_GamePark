package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class RecensioneDAO {


    public void addRecensione(Recensione recensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con
                    .prepareStatement("insert into recensione(testo,valutazione,id_utente,id_prodotto) values (?, ?, ?, ?, ? )");
            preparedStatement.setString(1, recensione.getTesto());
            preparedStatement.setInt(2, recensione.getValutazione());
            preparedStatement.setInt(3, recensione.getIdUtente());
            preparedStatement.setInt(4, recensione.getIdProdotto());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecensione(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con
                    .prepareStatement("delete from recensione where idRecensione=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecensione(Recensione recensione) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con
                    .prepareStatement("update recensione set testo=?, valutazione=?,  id_utente=?, id_prodotto=?" +
                            "where idRecensione=?");
            preparedStatement.setString(1, recensione.getTesto());
            preparedStatement.setInt(2, recensione.getValutazione());
            preparedStatement.setInt(3, recensione.getIdUtente());
            preparedStatement.setInt(4, recensione.getIdProdotto());
            preparedStatement.setInt(5, recensione.getIdRecensione());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List < Recensione > getAllRecensioni() {
        List < Recensione > recensioni = new ArrayList < Recensione > ();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con.
                    prepareStatement("select * from recensione");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setIdRecensione(rs.getInt("id"));
                recensione.setTesto(rs.getString("testo"));
                recensione.setValutazione(rs.getInt("valutazione"));
                recensione.setIdUtente(rs.getInt("id_utente"));
                recensione.setIdProdotto(rs.getInt("id_prodotto"));
                recensioni.add(recensione);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recensioni;
    }

    public Recensione getRecensioneById(int id) {
        Recensione recensione = new Recensione();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con.
                    prepareStatement("select * from recensione where idRecensione=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                recensione.setIdRecensione(rs.getInt("id"));
                recensione.setTesto(rs.getString("testo"));
                recensione.setValutazione(rs.getInt("voto"));
                recensione.setIdUtente(rs.getInt("id_utente"));
                recensione.setIdProdotto(rs.getInt("id_prodotto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recensione;
    }

    public List<Recensione> getRecensioneByIdUtente(int idUtente){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Recensione where id_utente=?");
            ps.setInt(1,idUtente);
            ResultSet rs=ps.executeQuery();
            List<Recensione> recensioni=new ArrayList<>();
            if(rs.next()){
                Recensione r=new Recensione();
                r.setIdRecensione(rs.getInt(1));
                r.setValutazione(rs.getInt(2));
                r.setTesto(rs.getString(3));
                r.setIdUtente(rs.getInt(4));
                r.setIdProdotto(rs.getInt(5));
                recensioni.add(r);
            }
            con.close();
            return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recensione> getRecensioniByIdProdotto(int idProdotto) {
        List<Recensione> recensioni = new ArrayList<Recensione>();
        try (Connection connection=ConPool.getConnection()){
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from recensione where id_prodotto=?");
            preparedStatement.setInt(1, idProdotto);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setIdRecensione(rs.getInt("id"));
                recensione.setIdUtente(rs.getInt("id_utente"));
                recensione.setIdProdotto(rs.getInt("id_prodotto"));
                recensione.setTesto(rs.getString("descrizione"));
                recensioni.add(recensione);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recensioni;
    }

}