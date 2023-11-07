package Controlador;
import Modelo.sucursales;
import ModeloDAO.sucursalesDAO;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "sucursalesControlador", urlPatterns = {"/sucursalesControlador"})
public class sucursalesControlador extends HttpServlet {
    sucursales sucursal = new sucursales();
    sucursalesDAO sucursalDAO = new sucursalesDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String requestedWithHeader = request.getHeader("X-Requested-With");

        switch (accion) {
            case "listar":
                List lista = sucursalDAO.listar();
                request.setAttribute("lista", lista);
                List listaUsuario = sucursalDAO.listarUsuario();
                request.setAttribute("listaUsuario", listaUsuario);
                request.getRequestDispatcher("sucursales.jsp").forward(request, response);
                break;
            case "agregar":
                String nombre = request.getParameter("nombreSucursal");
                String telefono = request.getParameter("telefonoSucursal");
                String direccion = request.getParameter("direccionSucursal");
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

                sucursal.setNombre(nombre);
                sucursal.setTelefono(telefono);
                sucursal.setDireccion(direccion);
                sucursal.setIdUsuario(idUsuario);

                sucursalDAO.agregar(sucursal);
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la venta en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String sucursalJson = gson.toJson(sucursal);
                    response.getWriter().write(sucursalJson);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("sucursalesControlador?accion=listar");
                }
                break;
            case "modificar":
                int id = Integer.parseInt(request.getParameter("idSucursal"));
                String nombreModi = request.getParameter("nombreSucursal");
                String telefonoModi = request.getParameter("telefonoSucursal");
                String direccionModi = request.getParameter("direccionSucursal");
                int idUsuarioModi = Integer.parseInt(request.getParameter("idUsuario"));

                sucursal.setId(id);
                sucursal.setNombre(nombreModi);
                sucursal.setTelefono(telefonoModi);
                sucursal.setDireccion(direccionModi);
                sucursal.setIdUsuario(idUsuarioModi);

                sucursalDAO.modificar(sucursal);

                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la venta en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String sucursalJson = gson.toJson(sucursal);
                    response.getWriter().write(sucursalJson);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("sucursalesControlador?accion=listar");
                }
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                sucursalDAO.delete(idEliminar);
                response.sendRedirect("sucursalesControlador?accion=listar");
                break;
            case "buscar":
                String nombreBusqueda = request.getParameter("buscarSucursal");
                List listaBusqueda = sucursalDAO.buscar(nombreBusqueda);
                // Convertir la lista a JSON
                Gson gson = new Gson();
                String json = gson.toJson(listaBusqueda);

                // Establecer el tipo de contenido a JSON y devolver la respuesta
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            default:
                response.sendRedirect("sucursales.jsp");
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
