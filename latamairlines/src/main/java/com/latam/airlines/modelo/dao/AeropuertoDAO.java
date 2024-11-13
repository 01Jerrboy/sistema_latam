package com.latam.airlines.modelo.dao;

import com.latam.airlines.config.ConexionDB;
import com.latam.airlines.modelo.Aeropuerto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AeropuertoDAO {

    private final Connection connection;

    public AeropuertoDAO() {
        this.connection = ConexionDB.getInstance().getConnection();
    }

    public boolean guardar(Aeropuerto aeropuerto) {
        String query = "INSERT INTO aeropuerto (nombre, codigo) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, aeropuerto.getNombre());
            ps.setString(2, aeropuerto.getCodigo());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Aeropuerto aeropuerto) {
        String query = "UPDATE aeropuerto SET nombre = ?, codigo = ? WHERE id_aeropuerto = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, aeropuerto.getNombre());
            ps.setString(2, aeropuerto.getCodigo());
            ps.setInt(3, aeropuerto.getIdAeropuerto());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) throws Exception {
        String query = "DELETE FROM aeropuerto WHERE id_aeropuerto = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("No se puede eliminar el aeropuerto porque está referenciado en otros registros.");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Aeropuerto buscarPorId(int id) {
        String query = "SELECT * FROM aeropuerto WHERE id_aeropuerto = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Aeropuerto aeropuerto = new Aeropuerto();
                    aeropuerto.setIdAeropuerto(rs.getInt("id_aeropuerto"));
                    aeropuerto.setNombre(rs.getString("nombre"));
                    aeropuerto.setCodigo(rs.getString("codigo"));
                    return aeropuerto;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Aeropuerto> listarTodos() {
        List<Aeropuerto> lista = new ArrayList<>();
        String query = "SELECT * FROM aeropuerto ORDER BY nombre ASC";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Aeropuerto aeropuerto = new Aeropuerto();
                aeropuerto.setIdAeropuerto(rs.getInt("id_aeropuerto"));
                aeropuerto.setNombre(rs.getString("nombre"));
                aeropuerto.setCodigo(rs.getString("codigo"));
                lista.add(aeropuerto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
