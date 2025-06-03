package com.komuna.komuna_backend.dto;

import com.komuna.komuna_backend.entity.Tienda;

public class TiendaPublicView {
    private Long id;
    private String nombreTienda;
    private String tipoNegocio;
    private String fotosLocal;
    private Integer cupoPersonas;
    private String horarioAtencion;
    private String ubicacionExacta;
    private String cartaMenuPrecios;
    private String enlacesRedesSociales;

    public TiendaPublicView(Tienda tienda) {
        this.id = tienda.getId();
        this.nombreTienda = tienda.getNombreTienda();
        this.tipoNegocio = tienda.getTipoNegocio();
        this.fotosLocal = tienda.getFotosLocal();
        this.cupoPersonas = tienda.getCupoPersonas();
        this.horarioAtencion = tienda.getHorarioAtencion();
        this.ubicacionExacta = tienda.getUbicacionExacta();
        this.cartaMenuPrecios = tienda.getCartaMenuPrecios();
        this.enlacesRedesSociales = tienda.getEnlacesRedesSociales();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreTienda() { return nombreTienda; }
    public void setNombreTienda(String nombreTienda) { this.nombreTienda = nombreTienda; }
    public String getTipoNegocio() { return tipoNegocio; }
    public void setTipoNegocio(String tipoNegocio) { this.tipoNegocio = tipoNegocio; }
    public String getFotosLocal() { return fotosLocal; }
    public void setFotosLocal(String fotosLocal) { this.fotosLocal = fotosLocal; }
    public Integer getCupoPersonas() { return cupoPersonas; }
    public void setCupoPersonas(Integer cupoPersonas) { this.cupoPersonas = cupoPersonas; }
    public String getHorarioAtencion() { return horarioAtencion; }
    public void setHorarioAtencion(String horarioAtencion) { this.horarioAtencion = horarioAtencion; }
    public String getUbicacionExacta() { return ubicacionExacta; }
    public void setUbicacionExacta(String ubicacionExacta) { this.ubicacionExacta = ubicacionExacta; }
    public String getCartaMenuPrecios() { return cartaMenuPrecios; }
    public void setCartaMenuPrecios(String cartaMenuPrecios) { this.cartaMenuPrecios = cartaMenuPrecios; }
    public String getEnlacesRedesSociales() { return enlacesRedesSociales; }
    public void setEnlacesRedesSociales(String enlacesRedesSociales) { this.enlacesRedesSociales = enlacesRedesSociales; }
}