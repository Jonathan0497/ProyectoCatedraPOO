package Modelo;

public class peliculas {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(String anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero; // Cambio de 'genero = genero;' a 'this.genero = genero;'
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado; // Asegúrate de que el campo de la clase se esté utilizando
    }
    private Integer id;
    private String nombrePelicula;
    private String descripcion;
    private String anioLanzamiento;
    private Integer idGenero;
    private String genero;
    private String duracion;
    private Integer idEstado;
    private String estado;

    public peliculas(Integer id, String nombrePelicula, String descripcion, String anioLanzamiento, Integer idGenero, String genero, String duracion, Integer idEstado, String estado) {
        this.id = id;
        this.nombrePelicula = nombrePelicula;
        this.descripcion = descripcion;
        this.anioLanzamiento = anioLanzamiento;
        this.idGenero = idGenero;
        this.genero = genero;
        this.duracion = duracion;
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public peliculas() {
    }
}
