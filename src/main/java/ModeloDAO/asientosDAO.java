package ModeloDAO;

import Config.Conexion;
import Modelo.Asientos;
import Modelo.Reserva;
import Modelo.TiposPrecio;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class asientosDAO {

    private static final Logger LOGGER = Logger.getLogger(salasDAO.class.getName());
    Conexion cn;
    {
        try {
            cn = new Conexion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int re;

    public List<Asientos> listarPorSala(int idSala) {
        String sql = "SELECT a.id_asiento, a.numero_asiento,\n" +
                "       CASE WHEN t.id_ticket IS NOT NULL THEN 'ocupado' ELSE 'disponible' END as estado\n" +
                "FROM asientos a\n" +
                "LEFT JOIN ticket t ON a.id_asiento = t.id_asiento\n" +
                "WHERE a.id_salas = ?";
        List<Asientos> lista = new ArrayList<>();

        try {
            Connection con = cn.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idSala);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Asientos asiento = new Asientos();
                asiento.setIdAsiento(rs.getInt("id_asiento"));
                asiento.setNumeroAsiento(rs.getString("numero_asiento"));
                asiento.setEstado(rs.getString("estado"));
                asiento.setEstaSeleccionado(Reserva.existeAsiento(rs.getInt("id_asiento")));
                lista.add(asiento);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


    public List<TiposPrecio> obtenerTodosLosPrecios() {
        String sql = "SELECT p.id_precio, p.precio, p.id_formato, p.id_edadCliente, e.descripcion as nombre_edad " +
                "FROM precio p " +
                "JOIN edad_cliente e ON p.id_edadCliente = e.id_edadCliente";

        List<TiposPrecio> listaPrecios = new ArrayList<>();

        try {
            Connection con = cn.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TiposPrecio precio = new TiposPrecio();
                precio.setIdPrecio(rs.getInt("id_precio"));
                precio.setPrecio(rs.getDouble("precio"));
                precio.setIdFormato(rs.getInt("id_formato"));
                precio.setIdEdadCliente(rs.getInt("id_edadCliente"));
                precio.setNombreEdad(rs.getString("nombre_edad"));
                listaPrecios.add(precio);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPrecios;
    }


    public BigDecimal crearTickets() {
        BigDecimal totalTicketsPagados = BigDecimal.ZERO;

        try  {
            Connection con = cn.getConnection();
            String sql = "INSERT INTO ticket (fecha_emision, id_multimedia, id_asiento, id_usuario, id_precio) VALUES (NOW(), ?, ?, ?, ?)";

                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, Reserva.getIdMultimedia());
                ps.setInt(3, Reserva.getIdUsuario());
                ps.setInt(4, Reserva.getIdPrecio());

                for (Integer idAsiento : Reserva.getIdAsientos()) {
                    ps.setInt(2, idAsiento);
                    ps.executeUpdate();

                    // Obtener las claves generadas (puede haber varias si se insertaron varios registros)
                    try {
                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        while (generatedKeys.next()) {
                            int idTicket = generatedKeys.getInt(1);

                            // Sumar al total el precio asociado a este ticket
                            totalTicketsPagados = totalTicketsPagados.add(obtenerTotalPrecioPorTicket(idTicket));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }


            // Reiniciar valores después de completar todas las inserciones
            Reserva.reiniciarValores();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return totalTicketsPagados;
    }

    private BigDecimal obtenerTotalPrecioPorTicket(int idTicket) {
        String sql = "SELECT precio FROM ticket " +
                "JOIN precio ON ticket.id_precio = precio.id_precio " +
                "WHERE ticket.id_ticket = ?";
        BigDecimal precio = BigDecimal.ZERO;

        try  {
            Connection con = cn.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idTicket);
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    precio = rs.getBigDecimal("precio");
                }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return precio;
    }


    public void agregarAsiento(Integer idAsiento) {
        Reserva.agregarIdAsiento(idAsiento);
    }
}
