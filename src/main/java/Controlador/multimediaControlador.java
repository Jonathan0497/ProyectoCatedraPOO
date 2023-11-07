package Controlador;

import Modelo.multimedia;
import ModeloDAO.multimediaDAO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "multimediaControlador", urlPatterns = {"/multimediaControlador"})
public class multimediaControlador extends HttpServlet {
    multimedia multi = new multimedia();
    multimediaDAO multiDAO = new multimediaDAO();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String requestedWithHeader = request.getHeader("X-Requested-With");

        switch (accion) {
            case "listar" :
                List lista = multiDAO.listar();
                request.setAttribute("lista", lista);
                List listaFormato = multiDAO.listarFormato();
                request.setAttribute("listaFormato", listaFormato);
                List listaSalas = multiDAO.listarSalas();
                request.setAttribute("listaSalas", listaSalas);
                List listaPelicula = multiDAO.listarPelicula();
                request.setAttribute("listaPelicula", listaPelicula);
                request.getRequestDispatcher("multimedia.jsp").forward(request, response);
                break;
            case "agregar" :
                String idPelicula = request.getParameter("idPelicula");
                String fechaEmisionstr = request.getParameter("fechaEmision");
                String horaInicio = request.getParameter("horaInicio");
                String horaFin = request.getParameter("horaFin");
                String idSala = request.getParameter("idSala");
                String idFormato = request.getParameter("idFormato");
                java.util.Date fechaEmision = null;

                try {
                    fechaEmision = formatter.parse(fechaEmisionstr);
                }catch (ParseException e) {
                    e.printStackTrace();
                }

                multi.setFechaEmision(fechaEmision);
                multi.setIdPelicula(Integer.parseInt(idPelicula));
                multi.setHoraInicio(horaInicio);
                multi.setHoraFinal(horaFin);
                multi.setIdSala(Integer.parseInt(idSala));
                multi.setIdFormato(Integer.parseInt(idFormato));

                multiDAO.agregar2(multi);
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la venta en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String multiJson = gson.toJson(multi);
                    response.getWriter().write(multiJson);// Establece el código de estado en 200
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("multimediaControlador?accion=listar");//Este es el error

                    return;
                }
                break;
            case "modificar" :
                int idMultimedia = Integer.parseInt(request.getParameter("idMultimedia"));
                String idPeliculaModi = request.getParameter("idPelicula");
                String fechaEmisionstrModi = request.getParameter("fechaEmision");
                String horaInicioModi = request.getParameter("horaInicio");
                String horaFinModi = request.getParameter("horaFin");
                String idSalaModi = request.getParameter("idSala");
                String idFormatoModi = request.getParameter("idFormato");
                java.util.Date fechaEmisionModi = null;

                try {
                    fechaEmisionModi = formatter.parse(fechaEmisionstrModi);
                }catch (ParseException e) {
                    e.printStackTrace();
                }

                multi.setFechaEmision(fechaEmisionModi);
                multi.setId(idMultimedia);
                multi.setIdPelicula(Integer.parseInt(idPeliculaModi));
                multi.setHoraInicio(horaInicioModi);
                multi.setHoraFinal(horaFinModi);
                multi.setIdSala(Integer.parseInt(idSalaModi));
                multi.setIdFormato(Integer.parseInt(idFormatoModi));

                multiDAO.modificar(multi);
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la venta en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String multiJson = gson.toJson(multi);
                    response.getWriter().write(multiJson);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("multimediaControlador?accion=listar");
                }
                break;
            case "eliminar" :
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                multiDAO.delete(idEliminar);
                response.sendRedirect("multimediaControlador?accion=listar");
                break;
            case "buscar" :
                String nombreBusqueda = request.getParameter("buscarMultimedia");
                List listaBusqueda = multiDAO.buscar(nombreBusqueda);
                // Convertir la lista a JSON
                Gson gson = new Gson();
                String json = gson.toJson(listaBusqueda);

                // Establecer el tipo de contenido a JSON y devolver la respuesta
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            default:
                response.sendRedirect("multimedia.jsp");
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
