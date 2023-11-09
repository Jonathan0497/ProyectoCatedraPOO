package Controlador;

import Config.Conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "GeneraSession", urlPatterns = {"/GeneraSession"})
public class GeneraSession extends HttpServlet {
    protected void doPost(final HttpServletRequest request, final HttpServletResponse
            response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(final HttpServletRequest request, final HttpServletResponse
            response)
            throws ServletException, IOException {
        final HttpSession sessionActual = request.getSession(true);
        final String operacion = request.getParameter("operacion");
        if ("salir".equals(operacion)) {
            sessionActual.setAttribute("USER", null);
            sessionActual.setAttribute("NAME", null);
            response.sendRedirect("index.jsp");
        } else if ("logueo".equals(operacion)) {
            final String usuario = request.getParameter("usuario");
            final String password = request.getParameter("password");
            try {
                Conexion con = new Conexion();
                String query = "SELECT nombre, id_usuario FROM usuario WHERE correo = ? AND clave = ? LIMIT 1";
                PreparedStatement pstmt = con.getConnection().prepareStatement(query);
                pstmt.setString(1, usuario);
                pstmt.setString(2, password);  // Suponiendo que hashPassword es una función que devuelve la versión hasheada de la contraseña
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {  // Si hay una coincidencia
                    sessionActual.setAttribute("USER", usuario);
                    sessionActual.setAttribute("NAME", rs.getString(1));
                    sessionActual.setAttribute("ID", rs.getInt(2));
                    response.sendRedirect("principalControlador?accion=listar");
                } else {
                    response.sendRedirect("index.jsp");
                }

                rs.close();
                pstmt.close();
                con.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            } finally {

            }

        }
    }
}