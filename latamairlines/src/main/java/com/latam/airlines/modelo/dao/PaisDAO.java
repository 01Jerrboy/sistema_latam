package com.latam.airlines.modelo.dao;

import com.latam.airlines.config.ConexionDB;
import com.latam.airlines.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisDAO {

    private final Connection connection;

    public PaisDAO() {
        this.connection = ConexionDB.getInstance().getConnection();
    }

    public List<Pais> ListarTodos() {
        List<Pais> lista = new ArrayList<>();
        String query = "SELECT * FROM pais order by nombre_pais asc";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pais obj = new Pais();
                obj.setIdPais(rs.getInt("id_pais"));
                obj.setNombre(rs.getString("nombre_pais"));
                lista.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
