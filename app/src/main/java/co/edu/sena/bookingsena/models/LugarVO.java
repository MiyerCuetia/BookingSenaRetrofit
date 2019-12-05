package co.edu.sena.bookingsena.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LugarVO  {

    private Integer lugId;
    private String lugNombre;
    private String lugDireccion;
    private String lugTelefono;
    private String lugCorreo;
    private String lugLatitud;
    private String lugLongitud;
    private String lugDescripcion;
    private Integer fkMunicipio;
    private Integer fkTipoLugar;


    public LugarVO() {
    }

    public LugarVO(Integer lugId, String lugNombre, String lugDireccion, String lugTelefono, String lugCorreo, String lugLatitud, String lugLongitud, String lugDescripcion, Integer fkMunicipio, Integer fkTipoLugar) {
        this.lugId = lugId;
        this.lugNombre = lugNombre;
        this.lugDireccion = lugDireccion;
        this.lugTelefono = lugTelefono;
        this.lugCorreo = lugCorreo;
        this.lugLatitud = lugLatitud;
        this.lugLongitud = lugLongitud;
        this.lugDescripcion = lugDescripcion;
        this.fkMunicipio = fkMunicipio;
        this.fkTipoLugar = fkTipoLugar;
    }

    public Integer getLugId() {
        return lugId;
    }

    public void setLugId(Integer lugId) {
        this.lugId = lugId;
    }

    public String getLugNombre() {
        return lugNombre;
    }

    public void setLugNombre(String lugNombre) {
        this.lugNombre = lugNombre;
    }

    public String getLugDireccion() {
        return lugDireccion;
    }

    public void setLugDireccion(String lugDireccion) {
        this.lugDireccion = lugDireccion;
    }

    public String getLugTelefono() {
        return lugTelefono;
    }

    public void setLugTelefono(String lugTelefono) {
        this.lugTelefono = lugTelefono;
    }

    public String getLugCorreo() {
        return lugCorreo;
    }

    public void setLugCorreo(String lugCorreo) {
        this.lugCorreo = lugCorreo;
    }

    public String getLugLatitud() {
        return lugLatitud;
    }

    public void setLugLatitud(String lugLatitud) {
        this.lugLatitud = lugLatitud;
    }

    public String getLugLongitud() {
        return lugLongitud;
    }

    public void setLugLongitud(String lugLongitud) {
        this.lugLongitud = lugLongitud;
    }

    public String getLugDescripcion() {
        return lugDescripcion;
    }

    public void setLugDescripcion(String lugDescripcion) {
        this.lugDescripcion = lugDescripcion;
    }

    public Integer getFkMunicipio() {
        return fkMunicipio;
    }

    public void setFkMunicipio(Integer fkMunicipio) {
        this.fkMunicipio = fkMunicipio;
    }

    public Integer getFkTipoLugar() {
        return fkTipoLugar;
    }

    public void setFkTipoLugar(Integer fkTipoLugar) {
        this.fkTipoLugar = fkTipoLugar;
    }
}
