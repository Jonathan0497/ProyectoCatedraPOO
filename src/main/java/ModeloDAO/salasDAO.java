package ModeloDAO;

import Config.Conexion;
import Modelo.salas;
import Modelo.sucursales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class salasDAO {
    private static final Logger LOGGER = Logger.getLogger(salasDAO.class.getName());
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
    int re;

    public List listar(){
        String sql = "SELECT salas.id_salas, salas.numero_sala, sucursales.id_sucursales, sucursales.nombre\n" +
                "FROM salas\n" +
                "INNER JOIN sucursales ON salas.id_sucursales = sucursales.id_sucursales;\n";
        List<salas> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                salas sala = new salas();
                sala.setId(rs.getInt("id_salas"));
                sala.setNumeroSala(rs.getInt("numero_sala"));
                sala.setIdSucursal(rs.getInt("id_sucursales"));
                sala.setSucursal(rs.getString("nombre"));
                lista.add(sala);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    public List listarSucursal(){
        String sql = "SELECT id_sucursales, nombre FROM sucursales";
        List<sucursales> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                sucursales sucursal = new sucursales();
                sucursal.setId(rs.getInt("id_sucursales"));
                sucursal.setNombre(rs.getString("nombre"));
                lista.add(sucursal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List buscar(String nombre){
        String sql = "SELECT salas.id_salas, salas.numero_sala, sucursales.id_sucursales, sucursales.nombre\n" +
                "FROM salas\n" +
                "INNER JOIN sucursales ON salas.id_sucursales = sucursales.id_sucursales\n" +
                "WHERE sucursales.nombre LIKE ?\n";
        //String sql = "SELECT id_venta, fecha_venta FROM ventas WHERE rl_nombre LIKE ?";
        List<salas> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while(rs.next()){
                salas sala = new salas();
                sala.setId(rs.getInt("id_salas"));
                sala.setNumeroSala(rs.getInt("numero_sala"));
                sala.setIdSucursal(rs.getInt("id_sucursales"));
                sala.setSucursal(rs.getString("nombre"));
                lista.add(sala);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int agregar(salas s){
        String sql = "INSERT INTO salas (numero_sala, id_sucursales) VALUES (?, ?)";
        String sql2 = "CALL AgregarAsientosUltimaSala();";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, s.getNumeroSala());
            ps.setInt(2, s.getIdSucursal());
            ps.executeUpdate();

        } catch (Exception e) {
            // Consider logging the exception
        }
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql2);
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al agregar la sala", e);
        }

        return re;
    }

    public int modificar(salas s){
        String sql = "UPDATE salas SET numero_sala = ?, id_sucursales = ? WHERE id_salas = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, s.getNumeroSala());
            ps.setInt(2, s.getIdSucursal());
            ps.setInt(3, s.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la sala", e);
        }
        return re;
    }


    public void delete(int id){
        String sqlCall = "CALL EliminarAsientos(?);";
        String sqlDelete = "DELETE FROM salas WHERE id_salas = ?;";
        try {
            ps = con.prepareStatement(sqlCall);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            // Eliminar la sala
            ps = con.prepareStatement(sqlDelete);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la sala", e);
        }
    }
}
