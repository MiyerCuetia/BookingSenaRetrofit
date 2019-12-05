/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bookingsena.models;
public class PaisVO {

    private Integer paiId;
    private String paiNombre;
    private String paiCodigo;

    public PaisVO() {
    }

    public PaisVO(Integer paiId, String paiNombre, String paiCodigo) {
        this.paiId = paiId;
        this.paiNombre = paiNombre;
        this.paiCodigo = paiCodigo;
    }

    public Integer getPaiId() {
        return paiId;
    }

    public void setPaiId(Integer paiId) {
        this.paiId = paiId;
    }

    public String getPaiNombre() {
        return paiNombre;
    }

    public void setPaiNombre(String paiNombre) {
        this.paiNombre = paiNombre;
    }

    public String getPaiCodigo() {
        return paiCodigo;
    }

    public void setPaiCodigo(String paiCodigo) {
        this.paiCodigo = paiCodigo;
    }
}
