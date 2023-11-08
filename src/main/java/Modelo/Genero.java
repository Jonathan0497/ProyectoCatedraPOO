package Modelo;

public class Genero {
    private Integer idGenero;
    private String genero;

    // Constructor vacío
    public Genero() {
    }

    // Constructor con parámetros
    public Genero(Integer idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    // Getter y Setter para idGenero
    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    // Getter y Setter para genero
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // (Opcional) Método toString()
    @Override
    public String toString() {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", genero='" + genero + '\'' +
                '}';
    }
}
