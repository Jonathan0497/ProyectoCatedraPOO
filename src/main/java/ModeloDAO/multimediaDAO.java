package ModeloDAO;

import Config.Conexion;
import Modelo.peliculas;
import Modelo.multimedia;
import Modelo.salas;
import Modelo.sucursales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class multimediaDAO {
    private static final Logger LOGGER = Logger.getLogger(multimediaDAO.class.getName());
    Conexion cn;

    {
        try {
            cn = new Conexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int re = 0;

    public List listar() {
        String sql = "SELECT multimedia.id_multimedia, multimedia.horaInicio, multimedia.horaFin, salas.id_salas, CONCAT(salas.numero_sala, ': ', sucursales.nombre) AS sala_sucursal, peliculas.id_pelicula, peliculas.nombre_pelicula, formato.id_formato, formato.formato, multimedia.Fecha_emision\n" +
                "FROM multimedia\n" +
                "INNER JOIN salas ON multimedia.id_salas = salas.id_salas\n" +
                "INNER JOIN sucursales ON salas.id_sucursales = sucursales.id_sucursales\n" +
                "INNER JOIN peliculas ON multimedia.id_pelicula = peliculas.id_pelicula\n" +
                "INNER JOIN formato ON multimedia.id_formato = formato.id_formato;";
        List<multimedia> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                multimedia multi = new multimedia();
                multi.setId(rs.getInt("id_multimedia"));
                multi.setHoraInicio(rs.getString("horaInicio"));
                multi.setHoraFinal(rs.getString("horaFin"));
                multi.setIdSala(rs.getInt("id_salas"));
                multi.setNumeroSala(rs.getString("sala_sucursal"));
                multi.setIdPelicula(rs.getInt("id_pelicula"));
                multi.setNombrePelicula(rs.getString("nombre_pelicula"));
                multi.setIdFormato(rs.getInt("id_formato"));
                multi.setNombreFormato(rs.getString("formato"));
                multi.setFechaEmision(rs.getDate("Fecha_emision"));
                System.out.println(multi.getId());
                lista.add(multi);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List listarFormato() {
        String sql = "SELECT id_formato, formato FROM formato";
        List<multimedia> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                multimedia multimedia = new multimedia();
                multimedia.setIdFormato(rs.getInt("id_formato"));
                multimedia.setNombreFormato(rs.getString("formato"));
                lista.add(multimedia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List listarSalas() {
        String sql = "SELECT id_salas, CONCAT(salas.numero_sala, ': ', sucursales.nombre) AS sala_sucursal FROM salas\n" +
                "INNER JOIN sucursales ON salas.id_sucursales = sucursales.id_sucursales";
        List<multimedia> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                multimedia multimedia = new multimedia();
                multimedia.setIdSala(rs.getInt("id_salas"));
                multimedia.setNumeroSala(rs.getString("sala_sucursal"));
                lista.add(multimedia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List listarPelicula() {
        String sql = "SELECT id_pelicula, nombre_pelicula, duracion FROM peliculas";
        List<multimedia> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                multimedia multimedia = new multimedia();
                multimedia.setIdPelicula(rs.getInt("id_pelicula"));
                multimedia.setNombrePelicula(rs.getString("nombre_pelicula"));
                multimedia.setDuracionPelicula(rs.getString("duracion"));
                lista.add(multimedia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List listarSucursal() {
        String sql = "SELECT id_sucursales, nombre FROM sucursales";
        List<multimedia> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                multimedia multimedia = new multimedia();
                multimedia.setIdSucursal(rs.getInt("id_sucursales"));
                multimedia.setNombreSucursal(rs.getString("nombre"));
                lista.add(multimedia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<multimedia> buscarPrincipal(Integer nombre, Integer sucursal) { // `sucursal` ahora es un objeto `Integer` para permitir nulos
        List<multimedia> lista = new ArrayList<>();
        String sql = "";
        if (nombre != null && sucursal != null) {
            sql = "SELECT p.nombre_pelicula, s.numero_sala, su.nombre as nombreSucursal, m.horaInicio, p.duracion, p.descripcion, m.Fecha_emision\n"
                    + "FROM multimedia m\n"
                    + "INNER JOIN peliculas p ON m.id_pelicula = p.id_pelicula\n"
                    + "INNER JOIN salas s ON m.id_salas = s.id_salas\n"
                    + "INNER JOIN sucursales su ON s.id_sucursales = su.id_sucursales\n"
                    + "WHERE p.id_pelicula = ? AND su.id_sucursales = ?;";
        } else if (nombre != null) {
            sql = "SELECT p.nombre_pelicula, s.numero_sala, su.nombre as nombreSucursal, m.horaInicio, p.duracion, p.descripcion, m.Fecha_emision\n"
                    + "FROM multimedia m "
                    + "INNER JOIN peliculas p ON m.id_pelicula = p.id_pelicula\n"
                    + "INNER JOIN salas s ON m.id_salas = s.id_salas\n"
                    + "INNER JOIN sucursales su ON s.id_sucursales = su.id_sucursales\n"
                    + "WHERE p.id_pelicula = ?;";
        } else if (sucursal != null) {
            sql = "SELECT p.nombre_pelicula, s.numero_sala, su.nombre as nombreSucursal, m.horaInicio, p.duracion, p.descripcion, m.Fecha_emision\n"
                    + "FROM multimedia m\n"
                    + "INNER JOIN peliculas p ON m.id_pelicula = p.id_pelicula\n"
                    + "INNER JOIN salas s ON m.id_salas = s.id_salas\n"
                    + "INNER JOIN sucursales su ON s.id_sucursales = su.id_sucursales\n"
                    + "WHERE su.id_sucursales = ?;";
        }

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            if (nombre != null) ps.setInt(1, nombre);
            if (sucursal != null) ps.setInt(nombre != null ? 2 : 1, sucursal);

            rs = ps.executeQuery();

            while (rs.next()) {
                multimedia multi = new multimedia();
                multi.setNombrePelicula(rs.getString("nombre_pelicula"));
                multi.setNumeroSala(rs.getString("numero_sala"));
                multi.setNombreSucursal(rs.getString("nombreSucursal"));
                multi.setHoraInicio(rs.getString("horaInicio"));
                multi.setDuracionPelicula(rs.getString("duracion"));
                multi.setDescripcionPelicula(rs.getString("descripcion"));
                multi.setFechaEmision(rs.getDate("Fecha_emision"));
                lista.add(multi);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar multimedia", e);
        }
        return lista;
    }


    public List<multimedia> ListarPrincipal() {
        List<multimedia> lista = new ArrayList<>();
        String sql = "SELECT m.id_multimedia,p.nombre_pelicula, s.id_salas, s.numero_sala, su.nombre as nombreSucursal, m.horaInicio, p.duracion, p.descripcion, m.Fecha_emision\n" +
                "FROM multimedia m\n" +
                "INNER JOIN peliculas p ON m.id_pelicula = p.id_pelicula\n" +
                "INNER JOIN salas s ON m.id_salas = s.id_salas\n" +
                "INNER JOIN sucursales su ON s.id_sucursales = su.id_sucursales;";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                multimedia multi = new multimedia();
                multi.setId(rs.getInt("id_multimedia"));
                multi.setNombrePelicula(rs.getString("nombre_pelicula"));
                multi.setNumeroSala(rs.getString("numero_sala"));
                multi.setIdSala(rs.getInt("id_salas"));
                multi.setNombreSucursal(rs.getString("nombreSucursal"));
                multi.setHoraInicio(rs.getString("horaInicio"));
                multi.setDuracionPelicula(rs.getString("duracion"));
                multi.setDescripcionPelicula(rs.getString("descripcion"));
                multi.setFechaEmision(rs.getDate("Fecha_emision"));
                lista.add(multi);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar las multimedia", e);
        }
        return lista;
    }
    public List buscar(String nombre) {
        String sql = "SELECT multimedia.id_multimedia, multimedia.horaInicio, multimedia.horaFin, salas.id_salas, CONCAT(salas.numero_sala, '; ', sucursales.nombre) AS sala_sucursal, peliculas.id_pelicula, peliculas.nombre_pelicula, formato.id_formato, formato.formato, multimedia.Fecha_emision\n" +
                "FROM multimedia\n" +
                "INNER JOIN salas ON multimedia.id_salas = salas.id_salas\n" +
                "INNER JOIN sucursales ON salas.id_sucursales = sucursales.id_sucursales\n" +
                "INNER JOIN peliculas ON multimedia.id_pelicula = peliculas.id_pelicula\n" +
                "INNER JOIN formato ON multimedia.id_formato = formato.id_formato\n" +
                "WHERE peliculas.nombre_pelicula LIKE ?";
        //String sql = "SELECT id_venta, fecha_venta FROM ventas WHERE rl_nombre LIKE ?";
        List<multimedia> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                multimedia multi = new multimedia();
                multi.setId(rs.getInt("id_multimedia"));
                multi.setHoraInicio(rs.getString("horaInicio"));
                multi.setHoraFinal(rs.getString("horaFin"));
                multi.setIdSala(rs.getInt("id_salas"));
                multi.setNumeroSala(rs.getString("sala_sucursal"));
                multi.setIdPelicula(rs.getInt("id_pelicula"));
                multi.setNombrePelicula(rs.getString("nombre_pelicula"));
                multi.setIdFormato(rs.getInt("id_formato"));
                multi.setNombreFormato(rs.getString("formato"));
                multi.setFechaEmision(rs.getDate("Fecha_emision"));
                lista.add(multi);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int agregar2(multimedia m){
        String sql = "INSERT INTO multimedia(horainicio, horafin, id_salas, id_pelicula, id_formato, Fecha_emision) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getHoraInicio());
            ps.setString(2, m.getHoraFinal());
            ps.setInt(3, m.getIdSala());
            ps.setInt(4, m.getIdPelicula());
            ps.setInt(5, m.getIdFormato());
            ps.setTimestamp(6, new Timestamp(m.getFechaEmision().getTime()));
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al agregar la multimedia", e);
        }
        return re;
    }

    public int modificar(multimedia m){
        String sql = "UPDATE multimedia SET horaInicio = ?, horaFin = ?, id_pelicula = ?, id_salas = ?, id_formato = ?, Fecha_emision = ? WHERE id_multimedia = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getHoraInicio());
            ps.setString(2, m.getHoraFinal());
            ps.setInt(3, m.getIdPelicula());
            ps.setInt(4, m.getIdSala());
            ps.setInt(5, m.getIdFormato());
            ps.setTimestamp(6, new Timestamp(m.getFechaEmision().getTime()));
            ps.setInt(7, m.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la multimedia", e);
        }
        return re;
    }
    public void delete(int id){
        String sql = "DELETE FROM multimedia WHERE id_multimedia = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la multimedia", e);
        }
    }
}
