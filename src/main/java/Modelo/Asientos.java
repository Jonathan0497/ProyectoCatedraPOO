package Modelo;

public class Asientos {

    private int idAsiento;
    private String numeroAsiento;
    private int idSalas;
    private String estado;

    private Boolean estaSeleccionado;
    public Asientos() {
    }

    public Asientos(int idAsiento, String numeroAsiento, int idSalas, String estado,Boolean estaSeleccionado) {
        this.idAsiento = idAsiento;
        this.numeroAsiento = numeroAsiento;
        this.idSalas = idSalas;
        this.estado = estado;
        this.estaSeleccionado = estaSeleccionado;
    }
    // Getters
    public int getIdAsiento() {
        return idAsiento;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public int getIdSalas() {
        return idSalas;
    }

    public boolean getEstaSeleccionado(){
        return estaSeleccionado;
    }

    public void setEstaSeleccionado(Boolean estaSeleccionado){
            this.estaSeleccionado = estaSeleccionado;
    }

    // Setters
    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }

    public void setNumeroAsiento(String numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public void setIdSalas(int idSalas) {
        this.idSalas = idSalas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

