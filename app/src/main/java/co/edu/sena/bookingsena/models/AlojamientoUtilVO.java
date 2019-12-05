package co.edu.sena.bookingsena.models;

public class AlojamientoUtilVO {
    private Integer aloId;
    private String aloCodigo;
    private int aloCapacidad;
    private String aloPrecio;
    private String aloDescripcion;

    private Integer lugId;
    private String lugNombre;
    private String lugDireccion;
    private String lugTelefono;
    private String lugCorreo;
    private String lugLatitud;
    private String lugLongitud;
    private String lugDescripcion;

    private Integer munId;
    private String munNombre;
    private String munCodigo;

    private Integer depId;
    private String depNombre;

    private Integer paiId;
    private String paiNombre;
    private String paiCodigo;

    private Integer tluId;
    private String tluNombre;

    private Integer talId;
    private String talNombre;

    public AlojamientoUtilVO() {
    }

    public AlojamientoUtilVO(Integer aloId, String aloCodigo, int aloCapacidad, String aloPrecio, String aloDescripcion, Integer lugId, String lugNombre, String lugDireccion, String lugTelefono, String lugCorreo, String lugLatitud, String lugLongitud, String lugDescripcion, Integer munId, String munNombre, String munCodigo, Integer depId, String depNombre, Integer paiId, String paiNombre, String paiCodigo, Integer tluId, String tluNombre, Integer talId, String talNombre) {
        this.aloId = aloId;
        this.aloCodigo = aloCodigo;
        this.aloCapacidad = aloCapacidad;
        this.aloPrecio = aloPrecio;
        this.aloDescripcion = aloDescripcion;
        this.lugId = lugId;
        this.lugNombre = lugNombre;
        this.lugDireccion = lugDireccion;
        this.lugTelefono = lugTelefono;
        this.lugCorreo = lugCorreo;
        this.lugLatitud = lugLatitud;
        this.lugLongitud = lugLongitud;
        this.lugDescripcion = lugDescripcion;
        this.munId = munId;
        this.munNombre = munNombre;
        this.munCodigo = munCodigo;
        this.depId = depId;
        this.depNombre = depNombre;
        this.paiId = paiId;
        this.paiNombre = paiNombre;
        this.paiCodigo = paiCodigo;
        this.tluId = tluId;
        this.tluNombre = tluNombre;
        this.talId = talId;
        this.talNombre = talNombre;
    }

    public Integer getAloId() {
        return aloId;
    }

    public void setAloId(Integer aloId) {
        this.aloId = aloId;
    }

    public String getAloCodigo() {
        return aloCodigo;
    }

    public void setAloCodigo(String aloCodigo) {
        this.aloCodigo = aloCodigo;
    }

    public int getAloCapacidad() {
        return aloCapacidad;
    }

    public void setAloCapacidad(int aloCapacidad) {
        this.aloCapacidad = aloCapacidad;
    }

    public String getAloPrecio() {
        return aloPrecio;
    }

    public void setAloPrecio(String aloPrecio) {
        this.aloPrecio = aloPrecio;
    }

    public String getAloDescripcion() {
        return aloDescripcion;
    }

    public void setAloDescripcion(String aloDescripcion) {
        this.aloDescripcion = aloDescripcion;
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

    public Integer getMunId() {
        return munId;
    }

    public void setMunId(Integer munId) {
        this.munId = munId;
    }

    public String getMunNombre() {
        return munNombre;
    }

    public void setMunNombre(String munNombre) {
        this.munNombre = munNombre;
    }

    public String getMunCodigo() {
        return munCodigo;
    }

    public void setMunCodigo(String munCodigo) {
        this.munCodigo = munCodigo;
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

    public Integer getTalId() {
        return talId;
    }

    public void setTalId(Integer talId) {
        this.talId = talId;
    }

    public String getTalNombre() {
        return talNombre;
    }

    public void setTalNombre(String talNombre) {
        this.talNombre = talNombre;
    }
}
