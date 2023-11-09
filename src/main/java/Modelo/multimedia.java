package Modelo;

import java.util.Date;

public class multimedia {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    public String getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(String numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public Integer getIdFormato() {
        return idFormato;
    }

    public void setIdFormato(Integer idFormato) {
        this.idFormato = idFormato;
    }

    public String getNombreFormato() {
        return nombreFormato;
    }

    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }

    public multimedia() {
    }

    public multimedia(Integer id, String horaInicio, String horaFinal, Integer idSala, String numeroSala, Integer idPelicula, String nombrePelicula, Integer idFormato, String nombreFormato, String duracionPelicula, Date fechaEmision) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.idSala = idSala;
        this.numeroSala = numeroSala;
        this.idPelicula = idPelicula;
        this.nombrePelicula = nombrePelicula;
        this.idFormato = idFormato;
        this.nombreFormato = nombreFormato;
        this.duracionPelicula = duracionPelicula;
        this.fechaEmision = fechaEmision;
    }

    private Integer id;
    private String horaInicio;
    private String horaFinal;
    private Integer idSala;
    private String numeroSala;
    private Integer idPelicula;
    private String nombrePelicula;
    private Integer idFormato;
    private String nombreFormato;
    private String duracionPelicula;
    private Date fechaEmision;
    private Integer idSucursal;
    private String nombreSucursal;
    private String descripcionPelicula;

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getDescripcionPelicula() {
        return descripcionPelicula;
    }

    public void setDescripcionPelicula(String descripcionPelicula) {
        this.descripcionPelicula = descripcionPelicula;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDuracionPelicula() {
        return duracionPelicula;
    }

    public void setDuracionPelicula(String duracionPelicula) {
        this.duracionPelicula = duracionPelicula;
    }
}
