package ModeloDAO;

import Config.Conexion;
import Modelo.peliculas;
import Modelo.Genero;
import Modelo.EstadoPelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeliculasDAO {
    private static final Logger LOGGER = Logger.getLogger(PeliculasDAO.class.getName());
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

    public List<peliculas> listar() {
        List<peliculas> lista = new ArrayList<>();
        String sql = "SELECT p.id_pelicula, p.nombre_pelicula, p.descripcion, p.anio_lanzamiento, " +
                "p.id_genero, p.duracion, p.id_estadoPelicula, g.genero, e.estadoPelicula\n" +
                "FROM peliculas p\n " +
                "INNER JOIN genero g ON p.id_genero = g.id_genero\n" +
                "INNER JOIN estado_pelicula e ON p.id_estadoPelicula = e.id_estadoPelicula\n;";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                peliculas peli = new peliculas();
                peli.setId(rs.getInt("id_pelicula"));
                peli.setNombrePelicula(rs.getString("nombre_pelicula"));
                peli.setDescripcion(rs.getString("descripcion"));
                peli.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
                peli.setIdGenero(rs.getInt("id_genero"));
                peli.setDuracion(rs.getString("duracion"));
                peli.setIdEstado(rs.getInt("id_estadoPelicula"));
                peli.setGenero(rs.getString("genero"));
                peli.setEstado(rs.getString("estadoPelicula"));
                lista.add(peli);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar las películas", e);
        }
        return lista;
    }

    public List<Genero> listarGeneros() {
        List<Genero> listaGeneros = new ArrayList<>();
        String sql = "SELECT * FROM genero";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("id_genero"));
                genero.setGenero(rs.getString("genero"));
                listaGeneros.add(genero);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar géneros", e);
        }
        return listaGeneros;
    }

    public List<EstadoPelicula> listarEstados() {
        List<EstadoPelicula> listaEstados = new ArrayList<>();
        String sql = "SELECT * FROM estado_pelicula";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                EstadoPelicula estado = new EstadoPelicula();
                estado.setIdEstadoPelicula(rs.getInt("id_estadoPelicula"));
                estado.setEstadoPelicula(rs.getString("estadoPelicula"));
                listaEstados.add(estado);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al listar estados de películas", e);
        }
        return listaEstados;
    }


    public List<peliculas> buscar(String nombre) {
        List<peliculas> lista = new ArrayList<>();
        String sql = "SELECT p.id_pelicula, p.nombre_pelicula, p.descripcion, p.anio_lanzamiento, p.id_genero, p.duracion, p.id_estadoPelicula, g.genero, e.estadoPelicula\n" +
                "FROM peliculas p\n " +
                "INNER JOIN genero g ON p.id_genero = g.id_genero\n" +
                "INNER JOIN estado_pelicula e ON p.id_estadoPelicula = e.id_estadoPelicula\n" +
                "WHERE p.nombre_pelicula LIKE ?;";


        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                peliculas peli = new peliculas();
                peli.setId(rs.getInt("id_pelicula"));
                peli.setNombrePelicula(rs.getString("nombre_pelicula"));
                peli.setDescripcion(rs.getString("descripcion"));
                peli.setAnioLanzamiento(rs.getString("anio_lanzamiento"));
                peli.setIdGenero(rs.getInt("id_genero"));
                peli.setDuracion(rs.getString("duracion"));
                peli.setIdEstado(rs.getInt("id_estadoPelicula"));
                peli.setGenero(rs.getString("genero"));
                peli.setEstado(rs.getString("estadoPelicula"));
                lista.add(peli);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al buscar películas", e);
        }
        return lista;
    }

    public int agregar(peliculas peli) {
        String sql = "INSERT INTO peliculas(nombre_pelicula, descripcion, anio_lanzamiento, id_genero, duracion, id_estadoPelicula) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, peli.getNombrePelicula());
            ps.setString(2, peli.getDescripcion());
            ps.setString(3, peli.getAnioLanzamiento());
            ps.setInt(4, peli.getIdGenero());
            ps.setString(5, peli.getDuracion());
            ps.setInt(6, peli.getIdEstado());
            re = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar película", e);
        }
        return re;
    }

    public int modificar(peliculas peli) {
        String sql = "UPDATE peliculas SET nombre_pelicula = ?, descripcion = ?, anio_lanzamiento = ?, id_genero = ?, duracion = ?, id_estadoPelicula = ? WHERE id_pelicula = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, peli.getNombrePelicula());
            ps.setString(2, peli.getDescripcion());
            ps.setString(3, peli.getAnioLanzamiento());
            ps.setInt(4, peli.getIdGenero());
            ps.setString(5, peli.getDuracion());
            ps.setInt(6, peli.getIdEstado());
            ps.setInt(7, peli.getId());
            re = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al modificar película", e);
        }
        return re;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM peliculas WHERE id_pelicula = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar película", e);
        }
    }
}
