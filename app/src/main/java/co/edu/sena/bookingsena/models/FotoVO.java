/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bookingsena.models;

public class FotoVO {


    private Integer fotId;
    private String fotRuta;
    private String fotLabel;
    private String fotDescripcion;
    private Integer fkAlojamiento;
    private Integer fkLugar;

    public FotoVO() {
    }

    public FotoVO(Integer fotId, String fotRuta, String fotLabel, String fotDescripcion, Integer fkAlojamiento, Integer fkLugar) {
        this.fotId = fotId;
        this.fotRuta = fotRuta;
        this.fotLabel = fotLabel;
        this.fotDescripcion = fotDescripcion;
        this.fkAlojamiento = fkAlojamiento;
        this.fkLugar = fkLugar;
    }

    public Integer getFotId() {
        return fotId;
    }

    public void setFotId(Integer fotId) {
        this.fotId = fotId;
    }

    public String getFotRuta() {
        return fotRuta;
    }

    public void setFotRuta(String fotRuta) {
        this.fotRuta = fotRuta;
    }

    public String getFotLabel() {
        return fotLabel;
    }

    public void setFotLabel(String fotLabel) {
        this.fotLabel = fotLabel;
    }

    public String getFotDescripcion() {
        return fotDescripcion;
    }

    public void setFotDescripcion(String fotDescripcion) {
        this.fotDescripcion = fotDescripcion;
    }

    public Integer getFkAlojamiento() {
        return fkAlojamiento;
    }

    public void setFkAlojamiento(Integer fkAlojamiento) {
        this.fkAlojamiento = fkAlojamiento;
    }

    public Integer getFkLugar() {
        return fkLugar;
    }

    public void setFkLugar(Integer fkLugar) {
        this.fkLugar = fkLugar;
    }
}
