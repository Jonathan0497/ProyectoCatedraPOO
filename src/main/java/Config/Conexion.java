package Config;
import java.sql.*;
public class Conexion {
    private Connection conexion = null;
    private Statement s = null;
    private ResultSet rs = null;
    //Contructor
    public Conexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/primecinema",
                    "root", "");
            s = conexion.createStatement();
        } catch (ClassNotFoundException e1) {
            System.out.println("ERROR:No encuentro el driver de la BD: " +
                    e1.getMessage());
        }
    }
    public ResultSet getRs() {
        return rs;
    }
    //SQL realizada
    public void setRs(String consulta) {
        try {
            this.rs = s.executeQuery(consulta);
        } catch (SQLException e2) {
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
        }
    }
    public void setQuery(String query) throws SQLException {
        this.s.executeUpdate(query);
    }
    public void cerrarConexion() throws SQLException {
        conexion.close();
    }

    public Connection getConnection() {
        return conexion;
    }
}
