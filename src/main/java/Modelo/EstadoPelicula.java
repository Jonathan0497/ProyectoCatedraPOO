package Modelo;

public class EstadoPelicula {
    private Integer idEstadoPelicula;
    private String estadoPelicula;

    // Constructor vacío
    public EstadoPelicula() {
    }

    // Constructor con parámetros
    public EstadoPelicula(Integer idEstadoPelicula, String estadoPelicula) {
        this.idEstadoPelicula = idEstadoPelicula;
        this.estadoPelicula = estadoPelicula;
    }

    // Getter y Setter para idEstadoPelicula
    public Integer getIdEstadoPelicula() {
        return idEstadoPelicula;
    }

    public void setIdEstadoPelicula(Integer idEstadoPelicula) {
        this.idEstadoPelicula = idEstadoPelicula;
    }

    // Getter y Setter para estadoPelicula
    public String getEstadoPelicula() {
        return estadoPelicula;
    }

    public void setEstadoPelicula(String estadoPelicula) {
        this.estadoPelicula = estadoPelicula;
    }

    // (Opcional) Método toString()
    @Override
    public String toString() {
        return "EstadoPelicula{" +
                "idEstadoPelicula=" + idEstadoPelicula +
                ", estadoPelicula='" + estadoPelicula + '\'' +
                '}';
    }
}
