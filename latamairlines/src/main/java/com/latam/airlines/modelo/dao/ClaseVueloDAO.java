package com.latam.airlines.modelo.dao;

import com.latam.airlines.config.ConexionDB;
import com.latam.airlines.modelo.ClaseVuelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaseVueloDAO {

    private final Connection connection;

    public ClaseVueloDAO() {
        this.connection = ConexionDB.getInstance().getConnection();
    }

    public List<ClaseVuelo> listarTodos() {
        List<ClaseVuelo> lista = new ArrayList<>();
        String query = "SELECT * FROM clase_vuelo ORDER BY descripcion ASC";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ClaseVuelo obj = new ClaseVuelo();
                obj.setIdClaseVuelo(rs.getInt("id_clase_vuelo"));
                obj.setDescripcion(rs.getString("descripcion"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
