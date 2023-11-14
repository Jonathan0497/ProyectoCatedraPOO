package Controlador;

import Config.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "RegistrarPersona", urlPatterns = {"/RegistrarPersona"})
public class RegistrarPersona extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreR = request.getParameter("nombreUsuario");
        System.out.println(nombreR);
        String apellidoR = request.getParameter("apellidoUsuario");
        System.out.println(apellidoR);
        String correoR = request.getParameter("correoUsuario");
        System.out.println(correoR);
        String telefonoR = request.getParameter("telefonoUsuario");
        System.out.println(telefonoR);
        String duiR = request.getParameter("duiUsuario");
        System.out.println(duiR);
        String claveR = request.getParameter("claveUsuario");
        System.out.println(claveR);

        try {
            Conexion con = new Conexion();
            String query = "INSERT INTO usuario(nombre, apellido, clave, dui, correo, telefono) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.getConnection().prepareStatement(query);
            pstmt.setString(1, nombreR);
            pstmt.setString(2, apellidoR);
            pstmt.setString(3, claveR);
            pstmt.setString(4, duiR);
            pstmt.setString(5, correoR);
            pstmt.setString(6, telefonoR);
            pstmt.executeUpdate();

            pstmt.close();
            con.cerrarConexion();

            response.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=Error al crear el usuario");
        }
    }
}
