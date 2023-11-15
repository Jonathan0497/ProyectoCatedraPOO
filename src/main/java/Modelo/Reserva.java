package Modelo;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private static List<Integer> idAsientos = new ArrayList<>();
    private static Integer idUsuario;
    private static Integer idMultimedia;
    private static Integer idPrecio = 1;

    private static Integer idSala;

    // Método para reiniciar los valores a null
    public static void reiniciarValores() {
        idAsientos.clear();
        idMultimedia = null;
        idPrecio = 1;
        idSala = null;
    }


    public static int getIdSala(){
        return idSala;
    }
    public static void setIdSala(Integer idSala){
        Reserva.idSala = idSala;
    }
    public static List<Integer> getIdAsientos() {
        return idAsientos;
    }

    public static void agregarIdAsiento(Integer idAsiento) {
        idAsientos.add(idAsiento);
    }
    public static void deseleccionarAsiento(Integer idAsiento) {
        idAsientos.remove(idAsiento);
    }

    public static boolean existeAsiento(Integer idAsiento) {
        return idAsientos.contains(idAsiento);
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        Reserva.idUsuario = idUsuario;
    }

    public static int getIdMultimedia() {
        return idMultimedia;
    }

    public static void setIdMultimedia(int idMultimedia) {
        Reserva.idMultimedia = idMultimedia;
    }

    public static int getIdPrecio() {
        return idPrecio;
    }

    public static void setIdPrecio(int idPrecio) {
        Reserva.idPrecio = idPrecio;
    }
}
