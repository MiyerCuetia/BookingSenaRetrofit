/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bookingsena.models;

public class TipolugarVO {

    private Integer tluId;
    private String tluNombre;

    public TipolugarVO() {
    }

    public TipolugarVO(Integer tluId, String tluNombre) {
        this.tluId = tluId;
        this.tluNombre = tluNombre;
    }

    public Integer getTluId() {
        return tluId;
    }

    public void setTluId(Integer tluId) {
        this.tluId = tluId;
    }

    public String getTluNombre() {
        return tluNombre;
    }

    public void setTluNombre(String tluNombre) {
        this.tluNombre = tluNombre;
    }
}
