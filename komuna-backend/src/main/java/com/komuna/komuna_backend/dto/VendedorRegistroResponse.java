package com.komuna.komuna_backend.dto;

public class VendedorRegistroResponse {
    private Long id;
    private String nombreTienda;
    private String correoElectronico;
    private String codigoUnico; 
    private String mensaje;

    public VendedorRegistroResponse(Long id, String nombreTienda, String correoElectronico, String codigoUnico, String mensaje) {
        this.id = id;
        this.nombreTienda = nombreTienda;
        this.correoElectronico = correoElectronico;
        this.codigoUnico = codigoUnico;
        this.mensaje = mensaje;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreTienda() { return nombreTienda; }
    public void setNombreTienda(String nombreTienda) { this.nombreTienda = nombreTienda; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getCodigoUnico() { return codigoUnico; }
    public void setCodigoUnico(String codigoUnico) { this.codigoUnico = codigoUnico; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}