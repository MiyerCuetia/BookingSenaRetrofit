

package co.edu.sena.bookingsena.models;

public class TipoalojamientoVO {

    private Integer talId;
    private String talNombre;

    public TipoalojamientoVO() {
    }

    public TipoalojamientoVO(Integer talId, String talNombre) {
        this.talId = talId;
        this.talNombre = talNombre;
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
