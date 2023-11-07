package Modelo;

public class salas {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }



    public salas() {

    }

    public salas(Integer id, Integer numeroSala, Integer idSucursal) {
        this.id = id;
        this.numeroSala = numeroSala;
        this.idSucursal = idSucursal;
    }

    private Integer id;
    private Integer numeroSala;
    private Integer idSucursal;

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String sucursal) {
        Sucursal = sucursal;
    }

    private String Sucursal;
}
