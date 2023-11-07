package Controlador;

import Modelo.salas;
import ModeloDAO.salasDAO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(name = "salasControlador", urlPatterns = {"/salasControlador"})
public class salasControlador extends HttpServlet {
    salas sala = new salas();
    salasDAO salaDAO = new salasDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String requestedWithHeader = request.getHeader("X-Requested-With");

        switch (accion) {
            case "listar":
                List lista = salaDAO.listar();
                request.setAttribute("lista", lista);
                List listaSucursal = salaDAO.listarSucursal();
                request.setAttribute("listaSucursal", listaSucursal);
                request.getRequestDispatcher("salas.jsp").forward(request, response);
                break;
            case "agregar":
                int numeroSala = Integer.parseInt(request.getParameter("numeroSala"));
                int sucursal = Integer.parseInt(request.getParameter("sucursal"));

                sala.setNumeroSala(numeroSala);
                sala.setIdSucursal(sucursal);

                salaDAO.agregar(sala);
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la venta en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String salaJson = gson.toJson(sala);
                    response.getWriter().write(salaJson);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("salasControlador?accion=listar");
                }
                break;
            case "modificar":
                int id = Integer.parseInt(request.getParameter("idSala"));
                int numeroSalaModi = Integer.parseInt(request.getParameter("numeroSala"));
                int sucursalModi = Integer.parseInt(request.getParameter("sucursal"));

                sala.setId(id);
                sala.setNumeroSala(numeroSalaModi);
                sala.setIdSucursal(sucursalModi);

                salaDAO.modificar(sala);
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la venta en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String salaJson = gson.toJson(sala);
                    response.getWriter().write(salaJson);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("salasControlador?accion=listar");
                }
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                salaDAO.delete(idEliminar);
                response.sendRedirect("salasControlador?accion=listar");
                break;
            case "buscar":
                String nombreBusqueda = request.getParameter("buscarSala");
                List listaBusqueda = salaDAO.buscar(nombreBusqueda);
                // Convertir la lista a JSON
                Gson gson = new Gson();
                String json = gson.toJson(listaBusqueda);

                // Establecer el tipo de contenido a JSON y devolver la respuesta
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            default:
                response.sendRedirect("salas.jsp");
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
