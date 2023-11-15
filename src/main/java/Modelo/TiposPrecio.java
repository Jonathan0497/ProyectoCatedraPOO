package Modelo;

public class TiposPrecio {

    private int idPrecio;
    private double precio;
    private int idFormato;
    private int idEdadCliente;

    private String nombreEdad;

    public int getIdPrecio() {
        return idPrecio;
    }

    public void setIdPrecio(int idPrecio) {
        this.idPrecio = idPrecio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdFormato() {
        return idFormato;
    }

    public void setIdFormato(int idFormato) {
        this.idFormato = idFormato;
    }

    public int getIdEdadCliente() {
        return idEdadCliente;
    }

    public void setIdEdadCliente(int idEdadCliente) {
        this.idEdadCliente = idEdadCliente;
    }

    public void setNombreEdad(String nombreEdad){
        this.nombreEdad = nombreEdad;
    }

    public String getNombreEdad() {
        return this.nombreEdad;
    }
}
