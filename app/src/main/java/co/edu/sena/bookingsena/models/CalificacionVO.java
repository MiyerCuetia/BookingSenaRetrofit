/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bookingsena.models;

public class CalificacionVO {

    private Integer calId;
    private String calValoracion;
    private String calComentario;
    private Integer fkAlojamiento;
    private Integer fkUsuario;

    public CalificacionVO() {
    }

    public CalificacionVO(Integer calId, String calValoracion, String calComentario, Integer fkAlojamiento, Integer fkUsuario) {
        this.calId = calId;
        this.calValoracion = calValoracion;
        this.calComentario = calComentario;
        this.fkAlojamiento = fkAlojamiento;
        this.fkUsuario = fkUsuario;
    }

    public Integer getCalId() {
        return calId;
    }

    public void setCalId(Integer calId) {
        this.calId = calId;
    }

    public String getCalValoracion() {
        return calValoracion;
    }

    public void setCalValoracion(String calValoracion) {
        this.calValoracion = calValoracion;
    }

    public String getCalComentario() {
        return calComentario;
    }

    public void setCalComentario(String calComentario) {
        this.calComentario = calComentario;
    }

    public Integer getFkAlojamiento() {
        return fkAlojamiento;
    }

    public void setFkAlojamiento(Integer fkAlojamiento) {
        this.fkAlojamiento = fkAlojamiento;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
