/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.bookingsena.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservaVO {

    @SerializedName("resId")
    @Expose
    private Integer resId;

    @SerializedName("resFechaRegistro")
    @Expose
    private String resFechaRegistro;
    @SerializedName("resFechaLlegada")
    @Expose
    private String resFechaLlegada;
    @SerializedName("resFechaSalida")
    @Expose
    private String resFechaSalida;
    @SerializedName("resFechaChecking")
    @Expose
    private String resFechaChecking;
    @SerializedName("resFechaCheckout")
    @Expose
    private String resFechaCheckout;
    @SerializedName("resEstado")
    @Expose
    private String resEstado;
    @SerializedName("resPago")
    @Expose
    private Double resPago;
    @SerializedName("fkAlojamiento")
    @Expose
    private Integer fkAlojamiento;

    @SerializedName("fkCliente")
    @Expose
    private Integer fkCliente;
    @SerializedName("fkUsuarioChecking")
    @Expose
    private Integer fkUsuarioChecking;
    @SerializedName("fkUsuarioCheckout")
    @Expose
    private Integer fkUsuarioCheckout;

    public ReservaVO() {
    }

    public ReservaVO(Integer resId, String resFechaRegistro, String resFechaLlegada, String resFechaSalida, String resFechaChecking, String resFechaCheckout, String resEstado, Double resPago, Integer fkAlojamiento, Integer fkCliente, Integer fkUsuarioChecking, Integer fkUsuarioCheckout) {
        this.resId = resId;
        this.resFechaRegistro = resFechaRegistro;
        this.resFechaLlegada = resFechaLlegada;
        this.resFechaSalida = resFechaSalida;
        this.resFechaChecking = resFechaChecking;
        this.resFechaCheckout = resFechaCheckout;
        this.resEstado = resEstado;
        this.resPago = resPago;
        this.fkAlojamiento = fkAlojamiento;
        this.fkCliente = fkCliente;
        this.fkUsuarioChecking = fkUsuarioChecking;
        this.fkUsuarioCheckout = fkUsuarioCheckout;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResFechaRegistro() {
        return resFechaRegistro;
    }

    public void setResFechaRegistro(String resFechaRegistro) {
        this.resFechaRegistro = resFechaRegistro;
    }

    public String getResFechaLlegada() {
        return resFechaLlegada;
    }

    public void setResFechaLlegada(String resFechaLlegada) {
        this.resFechaLlegada = resFechaLlegada;
    }

    public String getResFechaSalida() {
        return resFechaSalida;
    }

    public void setResFechaSalida(String resFechaSalida) {
        this.resFechaSalida = resFechaSalida;
    }

    public String getResFechaChecking() {
        return resFechaChecking;
    }

    public void setResFechaChecking(String resFechaChecking) {
        this.resFechaChecking = resFechaChecking;
    }

    public String getResFechaCheckout() {
        return resFechaCheckout;
    }

    public void setResFechaCheckout(String resFechaCheckout) {
        this.resFechaCheckout = resFechaCheckout;
    }

    public String getResEstado() {
        return resEstado;
    }

    public void setResEstado(String resEstado) {
        this.resEstado = resEstado;
    }

    public Double getResPago() {
        return resPago;
    }

    public void setResPago(Double resPago) {
        this.resPago = resPago;
    }

    public Integer getFkAlojamiento() {
        return fkAlojamiento;
    }

    public void setFkAlojamiento(Integer fkAlojamiento) {
        this.fkAlojamiento = fkAlojamiento;
    }

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Integer getFkUsuarioChecking() {
        return fkUsuarioChecking;
    }

    public void setFkUsuarioChecking(Integer fkUsuarioChecking) {
        this.fkUsuarioChecking = fkUsuarioChecking;
    }

    public Integer getFkUsuarioCheckout() {
        return fkUsuarioCheckout;
    }

    public void setFkUsuarioCheckout(Integer fkUsuarioCheckout) {
        this.fkUsuarioCheckout = fkUsuarioCheckout;
    }
}
