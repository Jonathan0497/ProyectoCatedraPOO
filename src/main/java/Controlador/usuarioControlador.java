package Controlador;

import Modelo.sucursales;
import Modelo.usuario;
import ModeloDAO.sucursalesDAO;
import ModeloDAO.usuarioDAO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "usuarioControlador", urlPatterns = {"/usuarioControlador"})
public class usuarioControlador extends HttpServlet {
    usuario usu = new usuario();
    usuarioDAO usuDAO = new usuarioDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String requestedWithHeader = request.getHeader("X-Requested-With");

        switch (accion) {
            case "listar":
                List lista = usuDAO.listar();
                request.setAttribute("lista", lista);
                List listaEstado = usuDAO.listarEstado();
                request.setAttribute("listaEstado", listaEstado);
                List listaTipo = usuDAO.listarTipo();
                request.setAttribute("listaTipo", listaTipo);
                request.getRequestDispatcher("usuario.jsp").forward(request, response);
                break;
            case "agregar":
                String nombre = request.getParameter("nombreUsuario");
                String apellido = request.getParameter("apellidoUsuario");
                String correo = request.getParameter("correoUsuario");
                String telefono = request.getParameter("telefonoUsuario");
                String dui = request.getParameter("duiUsuario");
                String clave = request.getParameter("claveUsuario");
                int idEstado = Integer.parseInt(request.getParameter("idEstado"));
                int idTipo = Integer.parseInt(request.getParameter("idTipo"));

                usu.setNombreUsuario(nombre);
                usu.setApellidoUsuario(apellido);
                usu.setCorreoUsuario(correo);
                usu.setDuiUsuario(dui);
                usu.setTelefonoUsuario(telefono);
                usu.setClaveUsuario(clave);
                usu.setIdEstado(idEstado);
                usu.setIdTipo(idTipo);

                usuDAO.agregar(usu);
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la venta en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String usuJson = gson.toJson(usu);
                    response.getWriter().write(usuJson);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("usuarioControlador?accion=listar");
                }

                break;
            case "modificar":
                int id = Integer.parseInt(request.getParameter("idUsuario"));
                String nombreModi = request.getParameter("nombreUsuario");
                String apellidoModi = request.getParameter("apellidoUsuario");
                String correoModi = request.getParameter("correoUsuario");
                String telefonoModi = request.getParameter("telefonoUsuario");
                String duiModi = request.getParameter("duiUsuario");
                int idEstadoModi = Integer.parseInt(request.getParameter("idEstado"));
                int idTipoModi = Integer.parseInt(request.getParameter("idTipo"));

                usu.setId(id);
                usu.setNombreUsuario(nombreModi);
                usu.setApellidoUsuario(apellidoModi);
                usu.setCorreoUsuario(correoModi);
                usu.setDuiUsuario(duiModi);
                usu.setTelefonoUsuario(telefonoModi);
                usu.setIdEstado(idEstadoModi);
                usu.setIdTipo(idTipoModi);

                usuDAO.modificar(usu);
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la venta en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String usuJson = gson.toJson(usu);
                    response.getWriter().write(usuJson);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("usuarioControlador?accion=listar");
                }
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                usuDAO.delete(idEliminar);
                response.sendRedirect("usuarioControlador?accion=listar");
                break;
            case "buscar":
                String nombreBusqueda = request.getParameter("buscarUsuario");
                List listaBusqueda = usuDAO.buscar(nombreBusqueda);
                // Convertir la lista a JSON
                Gson gson = new Gson();
                String json = gson.toJson(listaBusqueda);

                // Establecer el tipo de contenido a JSON y devolver la respuesta
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
