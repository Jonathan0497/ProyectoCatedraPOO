package Controlador;

import Modelo.multimedia;
import Modelo.peliculas;
import ModeloDAO.PeliculasDAO;
import ModeloDAO.multimediaDAO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "principalControlador", urlPatterns = {"/principalControlador"})
public class principalControlador extends HttpServlet {
    multimediaDAO multiDAO = new multimediaDAO();
    multimedia multi = new multimedia();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String requestedWithHeader = request.getHeader("X-Requested-With");

        switch (accion) {
            case "listar":
                List<multimedia> lista = multiDAO.ListarPrincipal();
                request.setAttribute("lista", lista);
                List listaPelicula = multiDAO.listarPelicula();
                request.setAttribute("listaPelicula", listaPelicula);
                List listaSucursal = multiDAO.listarSucursal();
                request.setAttribute("listaSucursal", listaSucursal);
                request.getRequestDispatcher("principal.jsp").forward(request, response);
                break;
            case "buscar":
                int nombreBusqueda = Integer.parseInt(request.getParameter("idPelicula"));
                int buscarSucursal = Integer.parseInt(request.getParameter("idSucursal"));

                List listaBusqueda = multiDAO.buscarPrincipal(nombreBusqueda, buscarSucursal);

                Gson gson = new Gson();
                String json = gson.toJson(listaBusqueda);


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
