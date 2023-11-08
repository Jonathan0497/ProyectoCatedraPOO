package Controlador;

import Modelo.peliculas;
import ModeloDAO.PeliculasDAO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "peliculasControlador", urlPatterns = {"/peliculasControlador"})
public class peliculasControlador extends HttpServlet {
    peliculas peli = new peliculas();
    PeliculasDAO peliDAO = new PeliculasDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String requestedWithHeader = request.getHeader("X-Requested-With");

        switch (accion) {
            case "listar":
                List<peliculas> lista = peliDAO.listar();
                request.setAttribute("lista", lista);
                // Suponiendo que existan los métodos para listar géneros y estados
                List listaGeneros = peliDAO.listarGeneros();
                request.setAttribute("listaGeneros", listaGeneros);
                List listaEstados = peliDAO.listarEstados();
                request.setAttribute("listaEstados", listaEstados);
                request.getRequestDispatcher("peliculas.jsp").forward(request, response);
                break;
            case "agregar":
                String nombrePelicula = request.getParameter("nombrePelicula");
                String descripcion = request.getParameter("descripcion");
                String anioLanzamiento = request.getParameter("anioLanzamiento");
                int idGenero = Integer.parseInt(request.getParameter("idGenero"));
                String duracion = request.getParameter("duracion");
                int idEstadoPelicula = Integer.parseInt(request.getParameter("idEstadoPelicula"));

                peli.setNombrePelicula(nombrePelicula);
                peli.setDescripcion(descripcion);
                peli.setAnioLanzamiento(anioLanzamiento);
                peli.setIdGenero(idGenero);
                peli.setDuracion(duracion);
                peli.setIdEstado(idEstadoPelicula);

                int resultado = peliDAO.agregar(peli);
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la película en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String peliculaJson = gson.toJson(peli);
                    response.getWriter().write(peliculaJson);
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("peliculasControlador?accion=listar");
                }
                break;
            case "modificar":
                int idPeliculaModi = Integer.parseInt(request.getParameter("idPelicula"));
                String nombrePeliculaModi = request.getParameter("nombrePelicula");
                String descripcionModi = request.getParameter("descripcion");
                String anioLanzamientoModi = request.getParameter("anioLanzamiento");
                int idGeneroModi = Integer.parseInt(request.getParameter("idGenero"));
                String duracionModi = request.getParameter("duracion");
                int idEstadoPeliculaModi = Integer.parseInt(request.getParameter("idEstadoPelicula"));

                peli.setId(idPeliculaModi);
                peli.setNombrePelicula(nombrePeliculaModi);
                peli.setDescripcion(descripcionModi);
                peli.setAnioLanzamiento(anioLanzamientoModi);
                peli.setIdGenero(idGeneroModi);
                peli.setDuracion(duracionModi);
                peli.setIdEstado(idEstadoPeliculaModi);

                int resultadoModificacion = peliDAO.modificar(peli);
                boolean modificado = resultadoModificacion > 0;
                if ("XMLHttpRequest".equals(requestedWithHeader)) {
                    // Es una petición AJAX, devuelve la película modificada en formato JSON
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    Gson gson = new Gson();
                    String peliculaJson = gson.toJson(peli);
                    response.getWriter().write(peliculaJson);
                    response.setStatus(modificado ? HttpServletResponse.SC_OK : HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } else {
                    // No es una petición AJAX, redirige como de costumbre
                    response.sendRedirect("peliculasControlador?accion=listar");
                }
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                peliDAO.eliminar(idEliminar);
                response.sendRedirect("peliculasControlador?accion=listar");
                break;
            case "buscar":
                String nombreBusqueda = request.getParameter("buscarPelicula");
                List listaBusqueda = peliDAO.buscar(nombreBusqueda);
                // Convertir la lista a JSON
                Gson gson = new Gson();
                String json = gson.toJson(listaBusqueda);

                // Establecer el tipo de contenido a JSON y devolver la respuesta
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
                break;
            default:
                response.sendRedirect("peliculas.jsp");
                break;
        }
    }

    // Este método refleja si la petición es AJAX o no y responde en consecuencia
    private void sendResponseAsJsonOrRedirect(peliculas peli, String requestedWithHeader, HttpServletResponse response, String redirectUrl) throws IOException {
        if ("XMLHttpRequest".equals(requestedWithHeader)) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            Gson gson = new Gson();
            String peliculaJson = gson.toJson(peli);
            response.getWriter().write(peliculaJson);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.sendRedirect(redirectUrl);
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
