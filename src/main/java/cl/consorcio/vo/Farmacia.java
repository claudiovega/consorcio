package cl.consorcio.vo;

import com.google.gson.annotations.SerializedName;

public class Farmacia {

    @SerializedName(value = "fecha")
    private String fecha;

    @SerializedName(value = "local_id")
    private String localId;

    @SerializedName(value = "local_nombre")
    private String localNombre;

    @SerializedName(value = "comuna_nombre")
    private String comunaNombre;

    @SerializedName(value = "localidad_nombre")
    private String localidadNombre;

    @SerializedName(value = "local_direccion")
    private String localDireccion;

    @SerializedName(value = "funcionamiento_hora_apertura")
    private String funcionamientoHoraApertura;

    @SerializedName(value = "funcionamiento_hora_cierre")
    private String funcionamientoHoraCierre;

    @SerializedName(value = "local_telefono")
    private String localTelefono;

    @SerializedName(value = "local_lat")
    private String localLat;

    @SerializedName(value = "local_lng")
    private String localLng;

    @SerializedName(value = "funcionamiento_dia")
    private String funcionamientoDia;

    @SerializedName(value = "fk_region")
    private String fkRegion;

    @SerializedName(value = "fk_comuna")
    private String fkComuna;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getLocalNombre() {
        return localNombre;
    }

    public void setLocalNombre(String localNombre) {
        this.localNombre = localNombre;
    }

    public String getComunaNombre() {
        return comunaNombre;
    }

    public void setComunaNombre(String comunaNombre) {
        this.comunaNombre = comunaNombre;
    }

    public String getLocalidadNombre() {
        return localidadNombre;
    }

    public void setLocalidadNombre(String localidadNombre) {
        this.localidadNombre = localidadNombre;
    }

    public String getLocalDireccion() {
        return localDireccion;
    }

    public void setLocalDireccion(String localDireccion) {
        this.localDireccion = localDireccion;
    }

    public String getFuncionamientoHoraApertura() {
        return funcionamientoHoraApertura;
    }

    public void setFuncionamientoHoraApertura(String funcionamientoHoraApertura) {
        this.funcionamientoHoraApertura = funcionamientoHoraApertura;
    }

    public String getFuncionamientoHoraCierre() {
        return funcionamientoHoraCierre;
    }

    public void setFuncionamientoHoraCierre(String funcionamientoHoraCierre) {
        this.funcionamientoHoraCierre = funcionamientoHoraCierre;
    }

    public String getLocalTelefono() {
        return localTelefono;
    }

    public void setLocalTelefono(String localTelefono) {
        this.localTelefono = localTelefono;
    }

    public String getLocalLat() {
        return localLat;
    }

    public void setLocalLat(String localLat) {
        this.localLat = localLat;
    }

    public String getLocalLng() {
        return localLng;
    }

    public void setLocalLng(String localLng) {
        this.localLng = localLng;
    }

    public String getFuncionamientoDia() {
        return funcionamientoDia;
    }

    public void setFuncionamientoDia(String funcionamientoDia) {
        this.funcionamientoDia = funcionamientoDia;
    }

    public String getFkRegion() {
        return fkRegion;
    }

    public void setFkRegion(String fkRegion) {
        this.fkRegion = fkRegion;
    }

    public String getFkComuna() {
        return fkComuna;
    }

    public void setFkComuna(String fkComuna) {
        this.fkComuna = fkComuna;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "fecha='" + fecha + '\'' +
                ", localId='" + localId + '\'' +
                ", localNombre='" + localNombre + '\'' +
                ", comunaNombre='" + comunaNombre + '\'' +
                ", localidadNombre='" + localidadNombre + '\'' +
                ", localDireccion='" + localDireccion + '\'' +
                ", funcionamientoHoraApertura='" + funcionamientoHoraApertura + '\'' +
                ", funcionamientoHoraCierre='" + funcionamientoHoraCierre + '\'' +
                ", localTelefono='" + localTelefono + '\'' +
                ", localLat='" + localLat + '\'' +
                ", localLng='" + localLng + '\'' +
                ", funcionamientoDia='" + funcionamientoDia + '\'' +
                ", fkRegion='" + fkRegion + '\'' +
                ", fkComuna='" + fkComuna + '\'' +
                '}'+"\n";
    }
}
