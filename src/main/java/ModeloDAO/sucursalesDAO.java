package ModeloDAO;

import Config.Conexion;
import Modelo.sucursales;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sucursalesDAO {
    private static final Logger LOGGER = Logger.getLogger(sucursalesDAO.class.getName());
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
        String sql = "SELECT sucursales.id_sucursales, sucursales.nombre, sucursales.telefono, sucursales.direccion, usuario.id_usuario, usuario.nombre as nombreUsuario\n" +
                "FROM sucursales\n" +
                "INNER JOIN usuario ON sucursales.id_usuario = usuario.id_usuario;\n";
        List<sucursales> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                sucursales sucursal = new sucursales();
                sucursal.setId(rs.getInt("id_sucursales"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setTelefono(rs.getString("telefono"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setIdUsuario(rs.getInt("id_usuario"));
                sucursal.setUsuario(rs.getString("nombreUsuario"));
                lista.add(sucursal);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar la sucursal", e);
        }
        return lista;
    }
    public List listarUsuario(){
        String sql = "SELECT id_usuario, CONCAT(nombre, ' ' ,apellido) AS nombreCompleto FROM usuario WHERE id_tipoUsuario = 3";
        List<sucursales> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                sucursales sucursal = new sucursales();
                sucursal.setIdUsuario(rs.getInt("id_usuario"));
                sucursal.setUsuario(rs.getString("nombreCompleto"));
                lista.add(sucursal);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar la sucursal", e);
        }
        return lista;
    }
    public List buscar(String nombre){
        String sql = "SELECT sucursales.id_sucursales, sucursales.nombre, sucursales.telefono, sucursales.direccion, usuario.id_usuario, usuario.nombre as nombreUsuario\n" +
                "FROM sucursales\n" +
                "INNER JOIN usuario ON sucursales.id_usuario = usuario.id_usuario\n" +
                "WHERE sucursales.nombre LIKE ?\n";
        //String sql = "SELECT id_venta, fecha_venta FROM ventas WHERE rl_nombre LIKE ?";
        List<sucursales> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            rs = ps.executeQuery();

            while(rs.next()){
                sucursales sucursal = new sucursales();
                sucursal.setId(rs.getInt("id_sucursales"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setTelefono(rs.getString("telefono"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setIdUsuario(rs.getInt("id_usuario"));
                sucursal.setUsuario(rs.getString("nombreUsuario"));
                lista.add(sucursal);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al agregar la sucursal", e);
        }
        return lista;
    }

    public int agregar(sucursales s){
        String sql = "INSERT INTO sucursales(nombre, telefono, direccion, id_usuario) VALUES(? , ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getTelefono());
            ps.setString(3, s.getDireccion());
            ps.setInt(4, s.getIdUsuario());
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al agregar la sucursal", e);
        }
        return re;
    }

    public int modificar(sucursales s){
        String sql = "UPDATE sucursales SET nombre = ?, telefono = ?, direccion = ?, id_usuario = ? WHERE id_sucursales = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getTelefono());
            ps.setString(3, s.getDireccion());
            ps.setInt(4, s.getIdUsuario());
            ps.setInt(5, s.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la sucursal", e);
        }
        return re;
    }

    public void delete(int id){
        String sql = "DELETE FROM sucursales WHERE id_sucursales = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la sucursal", e);
        }
    }


}
