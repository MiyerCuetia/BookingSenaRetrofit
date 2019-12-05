/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bookingsena.models;

public class UsuarioVO {

    private Integer usuId;
    /*private String usuIdentificacion;
    private String usuNombres;
    private String usuGenero;
    private String usuCorreo;
    private String usuTelefono;
    private String usuAvatar;
    private Integer fkTipoIdentificacion;*/


    public UsuarioVO() {
    }

    public UsuarioVO(Integer usuId) {
        this.usuId = usuId;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }
    /*
    public UsuarioVO(Integer usuId, String usuIdentificacion, String usuNombres, String usuGenero, String usuCorreo, String usuTelefono, String usuAvatar, Integer fkTipoIdentificacion) {
        this.usuId = usuId;
        this.usuIdentificacion = usuIdentificacion;
        this.usuNombres = usuNombres;
        this.usuGenero = usuGenero;
        this.usuCorreo = usuCorreo;
        this.usuTelefono = usuTelefono;
        this.usuAvatar = usuAvatar;
        this.fkTipoIdentificacion = fkTipoIdentificacion;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuIdentificacion() {
        return usuIdentificacion;
    }

    public void setUsuIdentificacion(String usuIdentificacion) {
        this.usuIdentificacion = usuIdentificacion;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuGenero() {
        return usuGenero;
    }

    public void setUsuGenero(String usuGenero) {
        this.usuGenero = usuGenero;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuTelefono() {
        return usuTelefono;
    }

    public void setUsuTelefono(String usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    public String getUsuAvatar() {
        return usuAvatar;
    }

    public void setUsuAvatar(String usuAvatar) {
        this.usuAvatar = usuAvatar;
    }

    public Integer getFkTipoIdentificacion() {
        return fkTipoIdentificacion;
    }

    public void setFkTipoIdentificacion(Integer fkTipoIdentificacion) {
        this.fkTipoIdentificacion = fkTipoIdentificacion;
    }*/
}
