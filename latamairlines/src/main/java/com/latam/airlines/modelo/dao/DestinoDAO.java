package com.latam.airlines.modelo.dao;

import com.latam.airlines.config.ConexionDB;
import com.latam.airlines.modelo.Destino;
import com.latam.airlines.modelo.Pais;
import com.latam.airlines.modelo.Aeropuerto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinoDAO {

    private final Connection connection;

    public DestinoDAO() {
        this.connection = ConexionDB.getInstance().getConnection();
    }

    public boolean guardar(Destino destino) {
        String query = "INSERT INTO destino (ciudad, id_aeropuerto, id_pais) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, destino.getCiudad());
            ps.setInt(2, destino.getAeropuerto().getIdAeropuerto());
            ps.setInt(3, destino.getPais().getIdPais());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Destino destino) {
        String query = "UPDATE destino SET ciudad = ?, id_aeropuerto = ?, id_pais = ? WHERE id_destino = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, destino.getCiudad());
            ps.setInt(2, destino.getAeropuerto().getIdAeropuerto());
            ps.setInt(3, destino.getPais().getIdPais());
            ps.setInt(4, destino.getIdDestino());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idDestino) {
        String query = "DELETE FROM destino WHERE id_destino = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idDestino);
            return ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("No se puede eliminar el destino porque está referenciado en otros registros.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Destino buscarPorId(int idDestino) {
        String query = "SELECT d.*, p.nombre_pais, a.nombre AS nombre_aeropuerto FROM destino d "
                + "INNER JOIN pais p ON d.id_pais = p.id_pais "
                + "INNER JOIN aeropuerto a ON d.id_aeropuerto = a.id_aeropuerto "
                + "WHERE d.id_destino = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idDestino);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Destino destino = new Destino();
                    Pais pais = new Pais();
                    Aeropuerto aeropuerto = new Aeropuerto();

                    destino.setIdDestino(rs.getInt("id_destino"));
                    destino.setCiudad(rs.getString("ciudad"));

                    pais.setIdPais(rs.getInt("id_pais"));
                    pais.setNombre(rs.getString("nombre_pais"));
                    destino.setPais(pais);

                    aeropuerto.setIdAeropuerto(rs.getInt("id_aeropuerto"));
                    aeropuerto.setNombre(rs.getString("nombre_aeropuerto"));
                    destino.setAeropuerto(aeropuerto);

                    return destino;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Destino> listarTodos() {
        List<Destino> lista = new ArrayList<>();
        String query = "SELECT d.*, p.nombre_pais, a.nombre AS nombre_aeropuerto FROM destino d "
                + "INNER JOIN pais p ON d.id_pais = p.id_pais "
                + "INNER JOIN aeropuerto a ON d.id_aeropuerto = a.id_aeropuerto "
                + "ORDER BY d.ciudad ASC";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Destino destino = new Destino();
                Pais pais = new Pais();
                Aeropuerto aeropuerto = new Aeropuerto();

                destino.setIdDestino(rs.getInt("id_destino"));
                destino.setCiudad(rs.getString("ciudad"));

                pais.setIdPais(rs.getInt("id_pais"));
                pais.setNombre(rs.getString("nombre_pais"));
                destino.setPais(pais);

                aeropuerto.setIdAeropuerto(rs.getInt("id_aeropuerto"));
                aeropuerto.setNombre(rs.getString("nombre_aeropuerto"));
                destino.setAeropuerto(aeropuerto);

                lista.add(destino);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
      public List<Destino> listarTodosOrdAscPais() {
        List<Destino> lista = new ArrayList<>();
        String query = "SELECT d.*, p.nombre_pais, a.nombre AS nombre_aeropuerto FROM destino d "
                + "INNER JOIN pais p ON d.id_pais = p.id_pais "
                + "INNER JOIN aeropuerto a ON d.id_aeropuerto = a.id_aeropuerto "
                + "ORDER BY p.nombre_pais ASC, d.ciudad ASC";
        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Destino destino = new Destino();
                Pais pais = new Pais();
                Aeropuerto aeropuerto = new Aeropuerto();

                destino.setIdDestino(rs.getInt("id_destino"));
                destino.setCiudad(rs.getString("ciudad"));

                pais.setIdPais(rs.getInt("id_pais"));
                pais.setNombre(rs.getString("nombre_pais"));
                destino.setPais(pais);

                aeropuerto.setIdAeropuerto(rs.getInt("id_aeropuerto"));
                aeropuerto.setNombre(rs.getString("nombre_aeropuerto"));
                destino.setAeropuerto(aeropuerto);

                lista.add(destino);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
