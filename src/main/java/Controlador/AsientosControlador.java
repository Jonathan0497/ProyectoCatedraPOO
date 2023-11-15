package Controlador;

import Modelo.Asientos;
import ModeloDAO.asientosDAO;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import Modelo.Reserva;

@WebServlet(name = "asientosControlador", urlPatterns = {"/asientosControlador"})
public class AsientosControlador extends HttpServlet {

    Asientos asiento = new Asientos();
    asientosDAO asientosDAO = new asientosDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String requestedWithHeader = request.getHeader("X-Requested-With");
        switch (accion) {
            case "enlistarAsientos":
                Integer IDSala = Integer.parseInt(request.getParameter("idSala"));
                Integer IDMultimedia = Integer.parseInt(request.getParameter("idMultimedia"));

                Reserva.setIdSala(IDSala);
                Reserva.setIdMultimedia(IDMultimedia);

                List listaAsientos = asientosDAO.listarPorSala(IDSala);
                List listaTiposDePrecio = asientosDAO.obtenerTodosLosPrecios();

                request.setAttribute("listaAsientos", listaAsientos);
                request.setAttribute("listaTiposPrecios", listaTiposDePrecio);

                request.getRequestDispatcher("asientos.jsp").forward(request, response);
                break;
            case "agregarAsiento":
                asientosDAO.agregarAsiento(Integer.parseInt(request.getParameter("idAsiento")));
                response.sendRedirect("asientosControlador?accion=enlistarAsientos&idSala="+ Reserva.getIdSala()+"&idMultimedia="+ Reserva.getIdMultimedia());
                break;
            case "comprarAsientos":
                BigDecimal totalPagado = asientosDAO.crearTickets();
                request.setAttribute("totalPagado", totalPagado);
                request.getRequestDispatcher("compraExitosa.jsp").forward(request, response);
                break;
            case "eliminarSeleccion":
                Reserva.deseleccionarAsiento(Integer.parseInt(request.getParameter("idAsiento")));
                response.sendRedirect("asientosControlador?accion=enlistarAsientos&idSala="+ Reserva.getIdSala()+"&idMultimedia="+ Reserva.getIdMultimedia());
                break;
            case "seleccionarPrecio":
                Reserva.setIdPrecio(Integer.parseInt(request.getParameter("idPrecio")));
                response.sendRedirect("asientosControlador?accion=enlistarAsientos&idSala="+ Reserva.getIdSala()+"&idMultimedia="+ Reserva.getIdMultimedia());
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
