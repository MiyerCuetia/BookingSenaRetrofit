/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bookingsena.models;

public class RolUsuarioVO {

    private Integer ruId;
    private String rolEstado;
    private Integer fkRol;
    private Integer fkUsuario;

    public RolUsuarioVO() {
    }

    public RolUsuarioVO(Integer ruId, String rolEstado, Integer fkRol, Integer fkUsuario) {
        this.ruId = ruId;
        this.rolEstado = rolEstado;
        this.fkRol = fkRol;
        this.fkUsuario = fkUsuario;
    }

    public Integer getRuId() {
        return ruId;
    }

    public void setRuId(Integer ruId) {
        this.ruId = ruId;
    }

    public String getRolEstado() {
        return rolEstado;
    }

    public void setRolEstado(String rolEstado) {
        this.rolEstado = rolEstado;
    }

    public Integer getFkRol() {
        return fkRol;
    }

    public void setFkRol(Integer fkRol) {
        this.fkRol = fkRol;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
