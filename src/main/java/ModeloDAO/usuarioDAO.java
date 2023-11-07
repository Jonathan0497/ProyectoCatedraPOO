package ModeloDAO;

import Config.Conexion;
import Modelo.sucursales;
import Modelo.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuarioDAO {
    private static final Logger LOGGER = Logger.getLogger(usuarioDAO.class.getName());
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

    public List listar() {
        String sql = "SELECT u.id_usuario, u.nombre, u.apellido, u.correo, u.dui, u.telefono,e.id_estadoUsuario, e.estadoUsuario, t.id_tipoUsuario, t.tipoUsuario\n" +
                "FROM usuario u\n" +
                "INNER JOIN estado_usuario e ON u.id_estadoUsuario = e.id_estadoUsuario\n" +
                "INNER JOIN tipo_usuario t ON u.id_tipoUsuario = t.id_tipoUsuario;\n";
        List<usuario> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                usuario usu = new usuario();
                usu.setId(rs.getInt("id_usuario"));
                usu.setNombreUsuario(rs.getString("nombre"));
                usu.setApellidoUsuario(rs.getString("apellido"));
                usu.setCorreoUsuario(rs.getString("correo"));
                usu.setDuiUsuario(rs.getString("dui"));
                usu.setTelefonoUsuario(rs.getString("telefono"));
                usu.setIdEstado(rs.getInt("id_estadoUsuario"));
                usu.setNombreEstado(rs.getString("estadoUsuario"));
                usu.setIdTipo(rs.getInt("id_tipoUsuario"));
                usu.setNombreTipo(rs.getString("tipoUsuario"));
                lista.add(usu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List buscar(String nombre) {
        String sql = "SELECT u.id_usuario, u.nombre, u.apellido, u.correo, u.dui, u.telefono,e.id_estadoUsuario, e.estadoUsuario, t.id_tipoUsuario, t.tipoUsuario\n" +
                "FROM usuario u\n" +
                "INNER JOIN estado_usuario e ON u.id_estadoUsuario = e.id_estadoUsuario\n" +
                "INNER JOIN tipo_usuario t ON u.id_tipoUsuario = t.id_tipoUsuario\n" +
                "WHERE u.nombre LIKE ? OR u.apellido LIKE ? OR u.dui LIKE ? OR u.correo LIKE ?";
        List<usuario> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + nombre + "%");
            ps.setString(3, "%" + nombre + "%");
            ps.setString(4, "%" + nombre + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                usuario usu = new usuario();
                usu.setId(rs.getInt("id_usuario"));
                usu.setNombreUsuario(rs.getString("nombre"));
                usu.setApellidoUsuario(rs.getString("apellido"));
                usu.setCorreoUsuario(rs.getString("correo"));
                usu.setDuiUsuario(rs.getString("dui"));
                usu.setTelefonoUsuario(rs.getString("telefono"));
                usu.setIdEstado(rs.getInt("id_estadoUsuario"));
                usu.setNombreEstado(rs.getString("estadoUsuario"));
                usu.setIdTipo(rs.getInt("id_tipoUsuario"));
                usu.setNombreTipo(rs.getString("tipoUsuario"));
                lista.add(usu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List listarEstado(){
        String sql = "SELECT id_estadoUsuario, estadoUsuario FROM estado_usuario";
        List<usuario> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                usuario usu = new usuario();
                usu.setIdEstado(rs.getInt("id_estadoUsuario"));
                usu.setNombreEstado(rs.getString("estadoUsuario"));
                lista.add(usu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List listarTipo(){
        String sql = "SELECT id_tipoUsuario, tipoUsuario FROM tipo_usuario";
        List<usuario> lista = new ArrayList<>();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                usuario usu = new usuario();
                usu.setIdTipo(rs.getInt("id_tipoUsuario"));
                usu.setNombreTipo(rs.getString("tipoUsuario"));
                lista.add(usu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public int agregar(usuario u){
        String sql = "INSERT INTO usuario(nombre, apellido, clave, dui, correo, telefono, id_estadoUsuario, id_tipoUsuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getApellidoUsuario());
            ps.setString(3, u.getClaveUsuario());
            ps.setString(4, u.getDuiUsuario());
            ps.setString(5, u.getCorreoUsuario());
            ps.setString(6, u.getTelefonoUsuario());
            ps.setInt(7, u.getIdEstado());
            ps.setInt(8, u.getIdTipo());
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al agregar la usuario", e);
        }
        return re;
    }

    public int modificar(usuario u){
        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, dui = ?, correo = ?, telefono = ?, id_estadoUsuario = ?, id_tipoUsuario = ? WHERE id_usuario = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getApellidoUsuario());
            ps.setString(3, u.getDuiUsuario());
            ps.setString(4, u.getCorreoUsuario());
            ps.setString(5, u.getTelefonoUsuario());
            ps.setInt(6, u.getIdEstado());
            ps.setInt(7, u.getIdTipo());
            ps.setInt(8, u.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al modificar la usuario", e);
        }
        return re;
    }

    public void delete(int id){
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al eliminar la usuario", e);
        }
    }
}
