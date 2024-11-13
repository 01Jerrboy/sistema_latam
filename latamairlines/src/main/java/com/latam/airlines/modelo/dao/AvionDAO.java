package com.latam.airlines.modelo.dao;

import com.latam.airlines.config.ConexionDB;
import com.latam.airlines.modelo.Avion;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class AvionDAO {

    private final Connection connection;

    public AvionDAO() {
        this.connection = ConexionDB.getInstance().getConnection();
    }

    public boolean guardar(Avion avion) {
        String query = "INSERT INTO avion (modelo, capacidad, fabricante, año_fabricacion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, avion.getModelo());
            ps.setInt(2, avion.getCapacidad());
            ps.setString(3, avion.getFabricante());
            ps.setInt(4, avion.getAnnioFabricacion());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Avion avion) {
        String query = "UPDATE avion SET modelo = ?, capacidad = ?, fabricante = ?, año_fabricacion = ? WHERE id_avion = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, avion.getModelo());
            ps.setInt(2, avion.getCapacidad());
            ps.setString(3, avion.getFabricante());
            ps.setInt(4, avion.getAnnioFabricacion());
            ps.setInt(5, avion.getIdAvion());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idAvion) {
        String query = "DELETE FROM avion WHERE id_avion = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idAvion);
            return ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("No se puede eliminar el avión porque está referenciado en otros registros.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Avion buscarPorId(int idAvion) {
        String query = "SELECT * FROM avion WHERE id_avion = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idAvion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Avion avion = new Avion();
                    avion.setIdAvion(rs.getInt("id_avion"));
                    avion.setModelo(rs.getString("modelo"));
                    avion.setCapacidad(rs.getInt("capacidad"));
                    avion.setFabricante(rs.getString("fabricante"));
                    avion.setAnnioFabricacion(rs.getInt("año_fabricacion"));
                    return avion;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Avion> listarTodos() {
        List<Avion> lista = new ArrayList<>();
        String query = "SELECT * FROM avion ORDER BY modelo ASC";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Avion avion = new Avion();
                avion.setIdAvion(rs.getInt("id_avion"));
                avion.setModelo(rs.getString("modelo"));
                avion.setCapacidad(rs.getInt("capacidad"));
                avion.setFabricante(rs.getString("fabricante"));
                avion.setAnnioFabricacion(rs.getInt("año_fabricacion"));
                lista.add(avion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
