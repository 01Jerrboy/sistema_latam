package com.latam.airlines.modelo.dao;

import com.latam.airlines.config.ConexionDB;
import com.latam.airlines.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VueloDAO {

    private final Connection connection;

    public VueloDAO() {
        this.connection = ConexionDB.getInstance().getConnection();
    }

    public boolean guardar(Vuelo vuelo) {
        String query = "INSERT INTO vuelo (id_aeropuerto, id_clase_vuelo, id_avion, id_destino_origen, "
                + "id_destino_regreso, fecha_salida, hora_salida, fecha_regreso, hora_regreso, tiempo_vuelo, "
                + "tipo_vuelo, costo_vuelo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, vuelo.getAeropuerto().getIdAeropuerto());
            ps.setInt(2, vuelo.getClaseVuelo().getIdClaseVuelo());
            ps.setInt(3, vuelo.getAvion().getIdAvion());
            ps.setInt(4, vuelo.getDestinoOrigen().getIdDestino());

            if (vuelo.getDestinoRegreso() != null) {
                ps.setInt(5, vuelo.getDestinoRegreso().getIdDestino());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setString(6, vuelo.getFechaSalida());
            ps.setString(7, vuelo.getHoraSalida());

            if (vuelo.getDestinoRegreso() != null) {
                ps.setString(8, vuelo.getFechaRegreso());
                ps.setString(9, vuelo.getHoraRegreso());
            } else {
                ps.setNull(8, Types.DATE);
                ps.setNull(9, Types.TIME);
            }

            ps.setString(10, vuelo.getTiempoVuelo());
            ps.setString(11, vuelo.getTipoVuelo());
            ps.setDouble(12, vuelo.getCostoVuelo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(Vuelo vuelo) {
        String query = "UPDATE vuelo SET id_aeropuerto = ?, id_clase_vuelo = ?, id_avion = ?, "
                + "id_destino_origen = ?, id_destino_regreso = ?, fecha_salida = ?, hora_salida = ?, "
                + "fecha_regreso = ?, hora_regreso = ?, tiempo_vuelo = ?, tipo_vuelo = ?, costo_vuelo = ? "
                + "WHERE id_vuelo = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, vuelo.getAeropuerto().getIdAeropuerto());
            ps.setInt(2, vuelo.getClaseVuelo().getIdClaseVuelo());
            ps.setInt(3, vuelo.getAvion().getIdAvion());
            ps.setInt(4, vuelo.getDestinoOrigen().getIdDestino());

            if (vuelo.getDestinoRegreso() != null) {
                ps.setInt(5, vuelo.getDestinoRegreso().getIdDestino());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            ps.setString(6, vuelo.getFechaSalida());
            ps.setString(7, vuelo.getHoraSalida());

            if (vuelo.getDestinoRegreso() != null) {
                ps.setString(8, vuelo.getFechaRegreso());
                ps.setString(9, vuelo.getHoraRegreso());
            } else {
                ps.setNull(8, Types.DATE);
                ps.setNull(9, Types.TIME);
            }

            ps.setString(10, vuelo.getTiempoVuelo());
            ps.setString(11, vuelo.getTipoVuelo());
            ps.setDouble(12, vuelo.getCostoVuelo());
            ps.setInt(13, vuelo.getIdVuelo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idVuelo) {
        String query = "DELETE FROM vuelo WHERE id_vuelo = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idVuelo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Vuelo buscarPorId(int idVuelo) {
        Vuelo vuelo = null;
        String query = "SELECT v.id_vuelo, v.fecha_salida, v.hora_salida, v.fecha_regreso, v.hora_regreso, "
                + "v.tiempo_vuelo, v.tipo_vuelo, v.costo_vuelo, "
                + "a.nombre AS aeropuerto_nombre, "
                + "cv.descripcion AS clase_vuelo_nombre, "
                + "av.modelo AS avion_modelo, "
                + "do.ciudad AS destino_origen_nombre, "
                + "dr.ciudad AS destino_regreso_nombre, "
                + "p.nombre_pais AS destino_origen_pais, "
                + "pr.nombre_pais AS destino_regreso_pais, "
                + "v.id_avion, "
                + " do.id_destino AS id_destino_origen, "
                + "v.id_clase_vuelo "
                + "FROM vuelo v "
                + "JOIN aeropuerto a ON v.id_aeropuerto = a.id_aeropuerto "
                + "JOIN clase_vuelo cv ON v.id_clase_vuelo = cv.id_clase_vuelo "
                + "JOIN avion av ON v.id_avion = av.id_avion "
                + "JOIN destino do ON v.id_destino_origen = do.id_destino "
                + "JOIN pais p ON do.id_pais = p.id_pais "
                + "LEFT JOIN destino dr ON v.id_destino_regreso = dr.id_destino "
                + "LEFT JOIN pais pr ON dr.id_pais = pr.id_pais"
                + " WHERE v.id_vuelo = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, idVuelo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    vuelo = new Vuelo();
                    vuelo.setIdVuelo(rs.getInt("id_vuelo"));

                    Aeropuerto aeropuerto = new Aeropuerto();
                    aeropuerto.setNombre(rs.getString("aeropuerto_nombre"));
                    vuelo.setAeropuerto(aeropuerto);

                    ClaseVuelo claseVuelo = new ClaseVuelo();
                    claseVuelo.setIdClaseVuelo(rs.getInt("id_clase_vuelo"));
                    claseVuelo.setDescripcion(rs.getString("clase_vuelo_nombre"));
                    vuelo.setClaseVuelo(claseVuelo);

                    Avion avion = new Avion();
                    avion.setIdAvion(rs.getInt("id_avion"));
                    avion.setModelo(rs.getString("avion_modelo"));
                    vuelo.setAvion(avion);

                    Destino destinoOrigen = new Destino();
                    destinoOrigen.setIdDestino(rs.getInt("id_destino_origen"));
                    destinoOrigen.setCiudad(rs.getString("destino_origen_nombre"));
                    destinoOrigen.setPais(new Pais(rs.getString("destino_origen_pais")));
                    vuelo.setDestinoOrigen(destinoOrigen);

                    if (rs.getString("destino_regreso_nombre") != null) {
                        Destino destinoRegreso = new Destino();
                        destinoRegreso.setCiudad(rs.getString("destino_regreso_nombre"));
                        destinoRegreso.setPais(new Pais(rs.getString("destino_regreso_pais")));
                        vuelo.setDestinoRegreso(destinoRegreso);
                    }

                    vuelo.setFechaSalida(rs.getString("fecha_salida"));
                    vuelo.setHoraSalida(rs.getString("hora_salida"));
                    vuelo.setFechaRegreso(rs.getString("fecha_regreso"));
                    vuelo.setHoraRegreso(rs.getString("hora_regreso"));
                    vuelo.setTiempoVuelo(rs.getString("tiempo_vuelo"));
                    vuelo.setTipoVuelo(rs.getString("tipo_vuelo"));
                    vuelo.setCostoVuelo(rs.getDouble("costo_vuelo"));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vuelo;
    }

    public List<Vuelo> listarTodos() {
        List<Vuelo> lista = new ArrayList<>();
        String query = "SELECT v.id_vuelo, v.fecha_salida, v.hora_salida, v.fecha_regreso, v.hora_regreso, "
                + "v.tiempo_vuelo, v.tipo_vuelo, v.costo_vuelo, "
                + "a.nombre AS aeropuerto_nombre, "
                + "cv.descripcion AS clase_vuelo_nombre, "
                + "av.modelo AS avion_modelo, "
                + "do.ciudad AS destino_origen_nombre, "
                + "dr.ciudad AS destino_regreso_nombre, "
                + "p.nombre_pais AS destino_origen_pais, "
                + "pr.nombre_pais AS destino_regreso_pais "
                + "FROM vuelo v "
                + "JOIN aeropuerto a ON v.id_aeropuerto = a.id_aeropuerto "
                + "JOIN clase_vuelo cv ON v.id_clase_vuelo = cv.id_clase_vuelo "
                + "JOIN avion av ON v.id_avion = av.id_avion "
                + "JOIN destino do ON v.id_destino_origen = do.id_destino "
                + "JOIN pais p ON do.id_pais = p.id_pais "
                + "LEFT JOIN destino dr ON v.id_destino_regreso = dr.id_destino "
                + "LEFT JOIN pais pr ON dr.id_pais = pr.id_pais "
                + "ORDER BY v.fecha_salida ASC";

        try (PreparedStatement ps = connection.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdVuelo(rs.getInt("id_vuelo"));

                Aeropuerto aeropuerto = new Aeropuerto();
                aeropuerto.setNombre(rs.getString("aeropuerto_nombre"));
                vuelo.setAeropuerto(aeropuerto);

                ClaseVuelo claseVuelo = new ClaseVuelo();
                claseVuelo.setDescripcion(rs.getString("clase_vuelo_nombre"));
                vuelo.setClaseVuelo(claseVuelo);

                Avion avion = new Avion();
                avion.setModelo(rs.getString("avion_modelo"));
                vuelo.setAvion(avion);

                Destino destinoOrigen = new Destino();
                destinoOrigen.setCiudad(rs.getString("destino_origen_nombre"));
                destinoOrigen.setPais(new Pais(rs.getString("destino_origen_pais")));
                vuelo.setDestinoOrigen(destinoOrigen);

                if (rs.getString("destino_regreso_nombre") != null) {
                    Destino destinoRegreso = new Destino();
                    destinoRegreso.setCiudad(rs.getString("destino_regreso_nombre"));
                    destinoRegreso.setPais(new Pais(rs.getString("destino_regreso_pais")));
                    vuelo.setDestinoRegreso(destinoRegreso);
                }

                vuelo.setFechaSalida(rs.getString("fecha_salida"));
                vuelo.setHoraSalida(rs.getString("hora_salida"));
                vuelo.setFechaRegreso(rs.getString("fecha_regreso"));
                vuelo.setHoraRegreso(rs.getString("hora_regreso"));
                vuelo.setTiempoVuelo(rs.getString("tiempo_vuelo"));
                vuelo.setTipoVuelo(rs.getString("tipo_vuelo"));
                vuelo.setCostoVuelo(rs.getDouble("costo_vuelo"));

                lista.add(vuelo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
