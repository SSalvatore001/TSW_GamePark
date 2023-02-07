package model.dao;

import java.sql.*;
import java.util.*;

import model.*;

public class UtenteDAO {
    public void addUtente(Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO utente (nome, cognome, email, password) VALUES (?, ?, ?, ?)");
            statement.setString(1, utente.getNome());
            statement.setString(2, utente.getCognome());
            statement.setString(3, utente.getEmail());
            statement.setString(4, utente.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public Utente getUtenteByEmailAndPassword(String email, String password) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM utente WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Utente utente = new Utente();
                utente.setId(resultSet.getInt("id"));
                utente.setNome(resultSet.getString("nome"));
                utente.setCognome(resultSet.getString("cognome"));
                utente.setEmail(resultSet.getString("email"));
                utente.setPassword(resultSet.getString("password"));
                return utente;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUtente(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con
                    .prepareStatement("delete from utente where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUtente(int id, Utente utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con
                    .prepareStatement("update utente set nome=?, cognome=?, email=?, password=?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getCognome());
            preparedStatement.setString(3, utente.getEmail());
            preparedStatement.setString(4, utente.getPassword());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List < Utente > getAllUtenti() {
        List < Utente > utenti = new ArrayList < Utente > ();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con.
                    prepareStatement("select * from utente");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Utente utente = new Utente();
                utente.setId(rs.getInt("id"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                utenti.add(utente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    public Utente getUtenteById(int id) {
        Utente utente = new Utente();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con.
                    prepareStatement("select * from utente where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                utente.setId(rs.getInt("id"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utente;
    }

    public Utente getUtenteByEmail(String email) {
        Utente utente = new Utente();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement preparedStatement = con.
                    prepareStatement("select * from utente where email=?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                utente.setId(rs.getInt("id"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utente;

    }

    public Utente getUtenteByEmailPassword(String email,String password){
        try (Connection con=ConPool.getConnection()){
            PreparedStatement ps=con.prepareStatement("SELECT * FROM Utente where email=? AND password=?");
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Utente u = new Utente();
                u.setId(rs.getInt(1));
                u.setNome(rs.getString(2));
                u.setCognome(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setPassword(rs.getString(5));
                return u;
            }
            con.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}