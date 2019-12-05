/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bookingsena.models;

public class DepartamentoVO {

    private Integer depId;
    private String depNombre;
    private Integer fkPais;

    public DepartamentoVO() {
    }

    public DepartamentoVO(Integer depId, String depNombre, Integer fkPais) {
        this.depId = depId;
        this.depNombre = depNombre;
        this.fkPais = fkPais;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getDepNombre() {
        return depNombre;
    }

    public void setDepNombre(String depNombre) {
        this.depNombre = depNombre;
    }

    public Integer getFkPais() {
        return fkPais;
    }

    public void setFkPais(Integer fkPais) {
        this.fkPais = fkPais;
    }
}
